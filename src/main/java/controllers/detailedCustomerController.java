package controllers;

import com.jfoenix.controls.JFXTextField;
import controllers.insurance.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.customer.Customer;
import models.gui.WindowHandler;
import models.insurance.AccidentStatement;
import models.insurance.Insurance;
import models.insurance.boatInsurance.BoatInsurance;
import models.insurance.residenceInsurance.PrimaryResidenceInsurance;
import models.travelInsurance.TravelInsurance;

import java.io.IOException;
import java.time.LocalDate;

public class detailedCustomerController {

    private Customer currentCustomer;
    ObservableList<Insurance> insuranceObservableList;
    ObservableList<AccidentStatement> accidentStatementsObservableList;


    //Insurance table
    @FXML
    private TableView<Insurance> tblInsurance;

    @FXML
    private TableColumn<Insurance, String> clmnInsuranceType;

    @FXML
    private TableColumn<Insurance, LocalDate> clmnJoinDate;

    @FXML
    private TableColumn<Insurance, Double> clmnTotal;

    @FXML
    private TableColumn<Insurance, Double> clmnAnnualPremium;

    @FXML
    private TableColumn<Insurance, String> clmnCoverageDescription;

    //AccidentStatements table
    @FXML
    private TableView<AccidentStatement> tblAccidentStatement;

    @FXML
    private TableColumn<AccidentStatement, Integer> clmnAccidentNr;

    @FXML
    private TableColumn<AccidentStatement, LocalDate> clmnDateOfAccident;

    @FXML
    private TableColumn<AccidentStatement, String> clmnAccidentType;

    @FXML
    private TableColumn<AccidentStatement, String> clmnAccidentDescription;

    //FX elementene

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXTextField lblInsuranceNr;

    @FXML
    private JFXTextField  lblSurname;

    @FXML
    private JFXTextField  lblFirstName;

    @FXML
    private JFXTextField  lblCustomerSince;

    @FXML
    private JFXTextField  lblInvoiceAddress;

    @FXML
    private JFXTextField lblPendingCompensation;


    @FXML
    private void btnBack() {
        //lukker vinduet
        Stage currentStage = getCurrentStage();
        currentStage.close();
    }



    @FXML
    private void btnSaveCustomer() {
        //TODO exceptions, sjekke om tom osv
        //Oppdaterer kunden med redigert data
        currentCustomer.setLastName(lblSurname.getText());
        currentCustomer.setFirstName(lblFirstName.getText());
        currentCustomer.setInvoiceAddress(lblInvoiceAddress.getText());
        try {
            currentCustomer.setPendingCompensation(Double.parseDouble(lblPendingCompensation.getText()));
        } catch (NumberFormatException e) {
            // TODO: display error window.
        }
    }

    @FXML
    private void insuranceDblClicked(Insurance clickedInsurance) {
        try {
            openInsuranceWindow(clickedInsurance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void accidentStatementDblClicked(AccidentStatement clickedAccidentStatement) {

    }

    //Finner nåværende stage ved hjelp av en fx:id for å kunne lukke dette vinduet
    private Stage getCurrentStage(){
        return (Stage) lblSurname.getScene().getWindow();
    }


    public void pickCustomer(Customer aCustomer) {
        //Lokal variabel for kunden som vises, og arrayene til kunden til observablelister
        currentCustomer = aCustomer;
        updateTables();

        //Setter textboksene
        lblInsuranceNr.setText(String.valueOf(currentCustomer.getInsuranceNr()));
        lblSurname.setText(currentCustomer.getLastName());
        lblFirstName.setText(currentCustomer.getFirstName());
        lblCustomerSince.setText(currentCustomer.getCustomerSince().toString());
        lblInvoiceAddress.setText(currentCustomer.getInvoiceAddress());
        lblPendingCompensation.setText(String.valueOf(currentCustomer.getPendingCompensation()));
    }

    public void onWindowShow(WindowEvent event) {
        //Legger til en listener pa vinduet som refresher begge tablene nar vinduet far fokus
        anchorPane.getScene().getWindow().focusedProperty().addListener((observableValue, onFocus, onUnfocus) -> {
           updateTables();
        });

        //Hjelp metoder siden dette ikke kan ligge i den innebygde initialize metoden
        initializeInsuranceTable();
        initializeAccidentStatementTable();
    }

    //TODO FIXME
    //TRY ME CATCH THESE HANDS
    private void updateTables() {
        insuranceObservableList = FXCollections.observableArrayList(currentCustomer.getListOfInsurances());
        tblInsurance.setItems(insuranceObservableList);
        accidentStatementsObservableList = FXCollections.observableList(currentCustomer.getListOfAccidentStatements());
        tblInsurance.refresh();
        tblAccidentStatement.refresh();
    }

    private void initializeInsuranceTable() {
        //Valuefactory paa alle kollonner som bruker get metodene til customer
        clmnInsuranceType.setCellValueFactory(new PropertyValueFactory<>("insuranceName"));
        clmnJoinDate.setCellValueFactory(new PropertyValueFactory<>("dateOfIssue"));
        clmnTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        clmnAnnualPremium.setCellValueFactory(new PropertyValueFactory<>("annualPremium"));
        clmnCoverageDescription.setCellValueFactory(new PropertyValueFactory<>("coverageDescription"));


        SortedList<Insurance> sortedData = new SortedList<>(insuranceObservableList);

        //Binder lista til javafx comparetorproperty, denne gir muligheten til sortering av tablet automatisk.
        sortedData.comparatorProperty().bind(tblInsurance.comparatorProperty());

        //Oppdaterer tablet
        tblInsurance.setItems(sortedData);


        tblInsurance.setRowFactory(tableView -> {

            //Tom rad
            TableRow<Insurance> aRow = new TableRow<>();

            //rad far listener paa museklikk
            aRow.setOnMouseClicked(mouseEvent -> {
                //Hivs raden har innhold og klikket var et dobbeltklikk
                if ((! aRow.isEmpty() && mouseEvent.getClickCount() == 2)) {
                    //Kall pa dobleCliked med Customerobkjetet til raden
                    insuranceDblClicked(aRow.getItem());
                }
            });
            return aRow;
        });
    }

    private void initializeAccidentStatementTable() {
        //Valuefactory paa alle kollonner som bruker get metodene til customer
        clmnAccidentNr.setCellValueFactory(new PropertyValueFactory<>("accidentNr"));
        clmnDateOfAccident.setCellValueFactory(new PropertyValueFactory<>("dateOfAccident"));
        clmnAccidentType.setCellValueFactory(new PropertyValueFactory<>("accidentType"));
        clmnAccidentDescription.setCellValueFactory(new PropertyValueFactory<>("accidentDescription"));


        SortedList<AccidentStatement> sortedData = new SortedList<>(accidentStatementsObservableList);

        //Binder lista til javafx comparetorproperty, denne gir muligheten til sortering av tablet automatisk.
        sortedData.comparatorProperty().bind(tblAccidentStatement.comparatorProperty());

        //Oppdaterer tablet
        tblAccidentStatement.setItems(sortedData);

        tblAccidentStatement.setRowFactory(tableView -> {

            //Tom rad
            TableRow<AccidentStatement> aRow = new TableRow<>();

            //rad far listener paa museklikk
            aRow.setOnMouseClicked(mouseEvent -> {
                //Hivs raden har innhold og klikket var et dobbeltklikk
                if ((! aRow.isEmpty() && mouseEvent.getClickCount() == 2)) {
                    //Kall pa dobleCliked med Customerobkjetet til raden
                    accidentStatementDblClicked(aRow.getItem());
                }
            });
            return aRow;
        });
    }


    @FXML
    private void btnNewBoatInsurance() {
        try {
            String pathToXml = "/org/view/boatInsurance.fxml";
            openCreateNewInsuranceWindow(pathToXml, "Båtforsikring");
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: Display error window.
        }
    }

    @FXML
    private void  btnPrimaryResidenceInsurance() {

    }

    @FXML
    private void btnSecondaryResidenceInsurance() {

    }

    @FXML
    private void btnNewTravelInsurance() {
        try {
            String pathToXml = "/org/view/travelInsurance.fxml";
            openCreateNewInsuranceWindow(pathToXml, "Reiseforsikring");
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: Display error window.
        }
    }

    @FXML
    private void openCreateNewInsuranceWindow(String pathToXml, String stageTitle) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathToXml));
        Parent root = loader.load();
        InsuranceController controller = loader.getController();

        controller.setCustomer(currentCustomer);
        controller.setState(new NewInsurance());
        controller.load();

        WindowHandler windowHandler = new WindowHandler();
        windowHandler.openNewStageAndLockCurrent(getCurrentStage(), root, stageTitle);
    }

    @FXML
    private void openInsuranceWindow(Insurance insurance) throws IOException {
        String pathToXml;

        if (insurance instanceof BoatInsurance) {
            pathToXml = "/org/view/boatInsurance.fxml";
            openExistingInsuranceWindow(insurance, pathToXml, "Båtforsikring");
        }
        else if (insurance instanceof TravelInsurance) {
            pathToXml = "/org/view/travelInsurance.fxml";
            openExistingInsuranceWindow(insurance, pathToXml, "Reiseforsikring");
        }
        else if (insurance instanceof PrimaryResidenceInsurance) {
            System.out.println("Primary residence insurance clicked...");
            // TODO: implementer her.
        }
        else {
            // TODO: Display error window
        }

    }

    @FXML
    private void openExistingInsuranceWindow(Insurance insurance, String pathToXml, String stageTitle) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathToXml));
        Parent root = loader.load();
        InsuranceController controller = loader.getController();

        controller.setInsurance(insurance);
        controller.setState(new ExistingInsurance());
        controller.load();

        WindowHandler windowHandler = new WindowHandler();
        windowHandler.openNewStageAndLockCurrent(getCurrentStage(), root, stageTitle);
    }





}
