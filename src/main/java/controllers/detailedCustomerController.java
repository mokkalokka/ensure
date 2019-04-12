package controllers;

import com.jfoenix.controls.JFXTextField;
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
    private void btnBoatInsurance() {
        WindowHandler windowHandler = new WindowHandler();
        try {
            windowHandler.openNewStageAndLockCurrent(getCurrentStage(), "/org/view/boatInsurance.fxml", "Vis Kunde");
        } catch (IOException e) {
            //TODO error håndtering
        }
    }

    @FXML
    private void  btnPrimaryResidenceInsurance() {

    }

    @FXML
    private void btnSecondaryResidenceInsurance() {

    }

    @FXML
    private void btnTravelInsurance() {

    }



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
    }

    @FXML
    private void insuranceDblClicked(Insurance clickedInsurance) {
        openInsuranceWindow(clickedInsurance);
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
        insuranceObservableList = FXCollections.observableArrayList(currentCustomer.getListOfInsurances());
        accidentStatementsObservableList = FXCollections.observableList(currentCustomer.getListOfAccidentStatements());

        //Setter textboksene
        lblInsuranceNr.setText(String.valueOf(currentCustomer.getInsuranceNr()));
        lblSurname.setText(currentCustomer.getLastName());
        lblFirstName.setText(currentCustomer.getFirstName());
        lblCustomerSince.setText(currentCustomer.getCustomerSince().toString());
        lblInvoiceAddress.setText(currentCustomer.getInvoiceAddress());
    }

    public void onWindowShow(WindowEvent event) {
        //Legger til en listener pa vinduet som refresher begge tablene nar vinduet far fokus
        anchorPane.getScene().getWindow().focusedProperty().addListener((observableValue, onFocus, onUnfocus) -> {
           tblInsurance.refresh();
           tblAccidentStatement.refresh();
        });

        //Hjelp metoder siden dette ikke kan ligge i den innebygde initialize metoden
        initializeInsuranceTable();
        initializeAccidentStatementTable();
    }

    private void initializeInsuranceTable() {
        //Valuefactory paa alle kollonner som bruker get metodene til customer
        clmnInsuranceType.setCellValueFactory(new PropertyValueFactory<>("insurnanceName"));
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
    private void openInsuranceWindow(Insurance insurance) {

        if (insurance instanceof BoatInsurance) {
            BoatInsurance boatInsurance = (BoatInsurance) insurance;
            openBoatInsuranceWindow(boatInsurance);
        }
        else if (insurance instanceof TravelInsurance) {
            TravelInsurance travelInsurance = (TravelInsurance) insurance;
            openTravelInsuranceWindow(travelInsurance);
        }
        else if (insurance instanceof PrimaryResidenceInsurance) {
            PrimaryResidenceInsurance primaryResidenceInsurance = (PrimaryResidenceInsurance) insurance;
            openPrimaryResidenceInsuranceWindow(primaryResidenceInsurance);
        }
        else {
            // TODO: Display error window
        }

    }

    // Setter felles datafelt for alle Insurance i insurance.fxml. Dette vinduet er del av alle
    // subforsikringsvinduene.
    @FXML
    private void setCommonInsuranceFields(Insurance insurance) {
        FXMLLoader insuranceViewLoader = new FXMLLoader(getClass().getResource("/org/view/insurance.fxml"));
        InsuranceController insuranceController = insuranceViewLoader.getController();
        insuranceController.displayInsurance(insurance);
    }

    private void openPrimaryResidenceInsuranceWindow(PrimaryResidenceInsurance primaryResidenceInsurance) {
        setCommonInsuranceFields(primaryResidenceInsurance);
        // TODO: last inn ny fxml og sett controller med argumentet.
    }

    private void openTravelInsuranceWindow(TravelInsurance travelInsurance) {
        setCommonInsuranceFields(travelInsurance);
        // TODO: last inn ny fxml og sett controller med argumentet.
    }

    private void openBoatInsuranceWindow(BoatInsurance boatInsurance) {
        setCommonInsuranceFields(boatInsurance);

        try {
            //Last inn ny fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/view/boatInsurance.fxml"));
            Parent root = loader.load();

            //Finner kontrolleren til fxml fila og passerer boatinsurance til kontrolleren.
            BoatInsuranceController controller = loader.getController();
            controller.loadBoatInsurance(boatInsurance);

            WindowHandler windowHandler = new WindowHandler();
            windowHandler.openNewStageAndLockCurrent(getCurrentStage(), root, "Båt forsikring");

        } catch (IOException e) {
            e.printStackTrace();
            // TODO: Display error window.
        }
    }

    @FXML
    private void openCreateNewInsuranceWindow(String pathToXml, String stageTitle) {
        FXMLLoader insuranceViewLoader = new FXMLLoader(getClass().getResource("/org/view/insurance.fxml"));
        InsuranceController insuranceController = insuranceViewLoader.getController();
        insuranceController.setCreateNewInsuranceState(currentCustomer);

        WindowHandler windowHandler = new WindowHandler();
        try {
            windowHandler.openNewStageAndLockCurrent(getCurrentStage(), pathToXml, stageTitle);
        } catch(IOException e) {
            //Todo error vindu
        }
    }

}
