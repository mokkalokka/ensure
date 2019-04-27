package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.customer.Customer;
import models.gui.WindowHandler;
import models.insurance.InsuranceCompany;

import java.io.IOException;

import java.time.LocalDate;

public class customersController {

    private final InsuranceCompany INS_COMP = InsuranceCompany.getInstance();
    private final ObservableList observableCustomerList = INS_COMP.getCustomerList();

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private TableView<Customer> tblCustomer;
    @FXML
    private TableColumn<Customer, Integer> clmnInsuranceNr;
    @FXML
    private TableColumn<Customer, String> clmnSurname;
    @FXML
    private TableColumn<Customer, String> clmnFirstName;
    @FXML
    private TableColumn<Customer, LocalDate>  clmnCustomerSince;
    @FXML
    private TableColumn<Customer, String> clmnInvoiceAddress;


    @FXML
    private void btnRegister() {
        String pathToFXML = "/org/view/newCustomer.fxml";
        String stageTitle = "Registrer ny kunde";
        WindowHandler windowHandler = new WindowHandler();

        //Åpner vinduet i en ny popup og låser dette vinduet
        try {
            windowHandler.openNewStageAndLockCurrent(getCurrentStage(), pathToFXML, stageTitle);
        } catch(IOException e) {
            //TODO error vindu
        }
    }

    //Finner nåværende stage ved hjelp av en fx:id for å kunne sette parent ved åpning av popup
    private Stage getCurrentStage(){
        return (Stage) anchorPane.getScene().getWindow();
    }

    //Metode som kjores av mainapp, med en gang vinduet apnes
    //Denne kjores etter initialize. Denne funket ikke der fordi getWindow() returnerer null i initialize
    public void onWindowShow(WindowEvent event) {
        //Legger til en listener pa vinduet som refresher tablet nar vinduet far fokus
        anchorPane.getScene()
                .getWindow()
                .focusedProperty()
                .addListener((observableValue, onFocus, onUnfocus) -> tblCustomer.refresh());
    }

    private void removeCustomer(Customer clickedCustomer) {
        INS_COMP.removeCustomer(clickedCustomer);
        tblCustomer.refresh();
    }

    private void openDetailedCustomer(Customer clickedCustomer) {
        //TODO denne kan ikke forlopig implemnteres med windowhandler metoder
        try {
            //Last inn ny fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/view/detailedCustomer.fxml"));
            Parent root = loader.load();

            //Finner kontrolleren til fxml fila og passerer den dobbelklikkede kunden til kontrolleren
            detailedCustomerController controller = loader.getController();
            controller.pickCustomer(clickedCustomer);

            //Oppretter en ny stage
            Stage newStage = new Stage();

            //Legger til en eventListener pa den nye stagen som kjorer onWinodwShow i detailedCustomerController nar vinduet vises
            newStage.setOnShown(controller::onWindowShow);

            //Setter tittel
            String title = String.format("Viser kunde: %s %s %s",
                    clickedCustomer.getInsuranceNr(),
                    clickedCustomer.getFirstName(),
                    clickedCustomer.getLastName());

            newStage.setTitle(title);

            Scene newScene = new Scene(root);
            newStage.setScene(newScene);

            newStage.initOwner(getCurrentStage());
            //Låser det gamle vinduet til det nye lukkes
            newStage.initModality(Modality.WINDOW_MODAL);
            //Hviser vinduet
            newStage.show();


        } catch (IOException e) {
            System.err.println("FXML file not found!");
        }
    }


    public void initialize() {

        //Klikking paa kunder

        //Funksjon pa alle rader in tablet
        tblCustomer.setRowFactory(tableView -> {

            //Tom rad
            TableRow<Customer> aRow = new TableRow<>();

            //Alle rader får kontekstmeny og et menuitem
            ContextMenu rowMenu = new ContextMenu();
            MenuItem removeItem = new MenuItem("Slett kunde");
            MenuItem openItem = new MenuItem("Åpne detaljert visning");
            //Når slett kunde blir trykket gjør et kall på removeCustomer med kunden
            removeItem.setOnAction(e -> {
                removeCustomer(aRow.getItem());
            });

            openItem.setOnAction(e -> {
                openDetailedCustomer(aRow.getItem());
            });

            //Legger til removeitem og openItem listener på menyken
            rowMenu.getItems().add(openItem);
            rowMenu.getItems().add(removeItem);


            //Gjør så den ikke kjøres når rad er tom
            aRow.contextMenuProperty().bind(
                    Bindings.when(Bindings.isNotNull(aRow.itemProperty()))
                            .then(rowMenu)
                            .otherwise((ContextMenu)null));

            //rad far listener paa museklikk
            aRow.setOnMouseClicked(mouseEvent -> {
                //Hivs raden har innhold og klikket var et dobbeltklikk
                if ((! aRow.isEmpty() && mouseEvent.getClickCount() == 2)) {
                    //Kall pa dobleCliked med Customerobkjetet til raden
                    openDetailedCustomer(aRow.getItem());
                }
            });
            return aRow;
        });



        //Soek i kunder

        //ValueFactory på alle kolonnene
        clmnInsuranceNr.setCellValueFactory(new PropertyValueFactory<>("insuranceNr"));
        clmnSurname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        clmnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        clmnCustomerSince.setCellValueFactory(new PropertyValueFactory<>("customerSince"));
        clmnInvoiceAddress.setCellValueFactory(new PropertyValueFactory<>("invoiceAddress"));

        //Alle kunder til en filteret liste uten filter
        FilteredList<Customer> filteredData = new FilteredList<>(observableCustomerList, null);

        //Listener paa soekeboksen
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {

            //Hver gang innholden i sokeboksen endres kalles denne
            //Funskjon pa alle elementene i filterData som forventer en boolks verdi.
            //True: elementet forblir i lista
            //False: elmentet blir fjernet
            filteredData.setPredicate(aCustomer -> {

                //Hvis soekeboksen er tom, returner true
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //Hvis sokedata i et element innholder det i sokeboksen, returnerer true
                String lowerCaseFilter = newValue.toLowerCase();

                if (aCustomer.searchData().contains(lowerCaseFilter)) {
                    return true;
                }
                //Hvis sokedata ikke stemmer og boksen ikke er tom, returnerer false
                return false;
            });
        });

        //Putter ale elementete fra den na filtrerte lista i en javafx sortedlist
        SortedList<Customer> sortedData = new SortedList<>(filteredData);

        //Binder lista til javafx comparetorproperty, denne gir muligheten til sortering av tablet automatisk.
        sortedData.comparatorProperty().bind(tblCustomer.comparatorProperty());

        //Oppdaterer tablet
        tblCustomer.setItems(sortedData);

    }
}
