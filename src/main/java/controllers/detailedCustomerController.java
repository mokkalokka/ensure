package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.customer.Customer;
import models.gui.OpenNewStage;
import models.insurance.Insurance;
import models.insurance.boatInsurance.BoatInsurance;
import models.insurance.residenceInsurance.PrimaryResidenceInsurance;
import models.travelInsurance.TravelInsurance;

import java.io.IOException;

public class detailedCustomerController {

    private Customer currentCustomer;

    //FX elementene
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

    //Finner nåværende stage ved hjelp av en fx:id for å kunne lukke dette vinduet
    private Stage getCurrentStage(){
        return (Stage) lblSurname.getScene().getWindow();
    }


    public void pickCustomer(Customer aCustomer) {
        //Lokal variabel for kunden som vises
        currentCustomer = aCustomer;

        //Setter textboksene
        lblInsuranceNr.setText(String.valueOf(aCustomer.getInsuranceNr()));
        lblSurname.setText(aCustomer.getLastName());
        lblFirstName.setText(aCustomer.getFirstName());
        lblCustomerSince.setText(aCustomer.getCustomerSince().toString());
        lblInvoiceAddress.setText(aCustomer.getInvoiceAddress());
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

            //Visning av nye vindu
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            //Setter eieren til den det nye vinduet til å være det du kommer fra
            stage.initOwner(getCurrentStage());
            //Setter modality slik at det gamle vinduet blir låst frem til det nye blir lukket
            stage.initModality(Modality.WINDOW_MODAL);

            stage.show();
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

        OpenNewStage openNewStage = new OpenNewStage();
        openNewStage.openNewStage(getCurrentStage(), pathToXml, stageTitle);
    }

}
