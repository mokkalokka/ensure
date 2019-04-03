package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.customer.Customer;
import models.customer.CustomerList;
import models.gui.OpenNewStage;
import models.gui.OpenScene;

import java.io.IOException;

import java.time.LocalDate;

public class customersController {
    //Alle fx elementene
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

    //Liste linket til tablet fra lista med alle customers
    private ObservableList<Customer> observableCustomerList = FXCollections.observableArrayList(CustomerList.getCustomerArrayList());

    @FXML
    private void btnRegister() {
        String pathToFXML = "/org/view/newCustomer.fxml";
        String stageTitle = "Registrer ny kunde";
        OpenNewStage openNewStage = new OpenNewStage();

        //Åpner vinduet i en ny popup og låser dette vinduet
        openNewStage.openNewStage(getCurrentStage(), pathToFXML, stageTitle);
    }

    //Finner nåværende stage ved hjelp av en fx:id for å kunne sette parent ved åpning av popup
    private Stage getCurrentStage(){
        return (Stage) txtSearch.getScene().getWindow();
    }


    public void doubleClicked(Customer clickedCustomer) {
        //TODO maa erstatte vindu, ikke aapne nytt
        //TODO feilhandering
        try {
            //Last inn ny fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/view/detailedCustomer.fxml"));
            Parent root = loader.load();

            //Finner kontrolleren til fxml fila og passerer den dobbelklikkede kunden til kontrolleren
            detailedCustomerController controller = loader.getController();
            controller.pickCustomer(clickedCustomer);

            //Visning av nye vindu
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            //Setter eieren til den det nye vinduet til å være det du kommer fra
            stage.initOwner(getCurrentStage());
            //Setter modality slik at det gamle vinduet blir låst frem til det nye blir lukket
            stage.initModality(Modality.WINDOW_MODAL);

            stage.show();


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

            //rad far listener paa museklikk
            aRow.setOnMouseClicked(mouseEvent -> {
                //Hivs raden har innhold og klikket var et dobbeltklikk
                if ((! aRow.isEmpty() && mouseEvent.getClickCount() == 2)) {
                    //Kall pa dobleCliked med Customerobkjetet til raden
                    doubleClicked(aRow.getItem());
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
