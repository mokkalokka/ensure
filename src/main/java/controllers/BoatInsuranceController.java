package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import models.builders.boatInsurance.BoatBuilder;
import models.builders.boatInsurance.BoatInsuranceBuilder;
import models.customer.CustomerHandler;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.insurance.InsuranceHandler;
import models.insurance.boatInsurance.Boat;
import models.insurance.boatInsurance.BoatInsurance;
import models.insurance.boatInsurance.BoatOwner;

public class BoatInsuranceController {

    private final InsuranceController insuranceController =
            new FXMLLoader(getClass().getResource("/org/view/insurance.fxml")).getController();

    // En slags 'state' for om nåværende forsikring er ny, eller skal overskrive en eksisterende.
    // Siden det kun er 2 states og disse påvirker bare 1 metode, er det valgt en enkel løsning med boolsk variabel,
    // i stedet for å implementere State-designmønsteret.
    private boolean currentInsuranceIsNew;

    @FXML
    private TextField txtOwnerSurname;
    @FXML
    private TextField txtOwnerFirstName;
    @FXML
    private TextField txtRegistrationNr;
    @FXML
    private TextField txtBoatType;
    @FXML
    private TextField txtLengthInFt;
    @FXML
    private TextField txtModelYear;
    @FXML
    private TextField txtEngineType;
    @FXML
    private TextField txtBoatModel;
    @FXML
    private TextField txtEngineHP;

    @FXML
    private void btnSave() {
        try {
            if (currentInsuranceIsNew) {
                saveNewBoatInsurance();
                currentInsuranceIsNew = false;
            }
            else {
                overwriteExistingInsurance();
            }
        } catch (NoSuchCustomerException e) {
            e.printStackTrace();
            // TODO: Display error window.
        }
    }



    private void saveNewBoatInsurance() throws NoSuchCustomerException {
        InsuranceHandler insuranceHandler = new InsuranceHandler();
        insuranceHandler.addNewInsurance(getCurrentBoatInsurance());
    }

    private void overwriteExistingInsurance() {
        try {
            CustomerHandler.overwriteInsurance(getCurrentBoatInsurance());
        } catch (NoSuchCustomerException e) {
            e.printStackTrace();
            // TODO: Display error window.
        }
    }

    private BoatInsurance getCurrentBoatInsurance() {
        return new BoatInsuranceBuilder()
                .setRegisteredTo(insuranceController.getTxtRegisteredTo().getText())
                .setAnnualPremium(insuranceController.getTxtAnnualPremium().getText())
                .setCoverageDescription(insuranceController.getTxtCoverageDescription().getText())
                .setTotal(insuranceController.getTxtTotal().getText())
                .setBoat(getCurrentBoat())
                .build();
    }

    private Boat getCurrentBoat() {
        return new BoatBuilder()
                .setRegistrationNr(txtRegistrationNr.getText())
                .setBoatModel(txtBoatModel.getText())
                .setBoatType(txtBoatType.getText())
                .setEngineHP(txtEngineHP.getText())
                .setEngineType(txtEngineType.getText())
                .setLengthInft(txtLengthInFt.getText())
                .setModelYear(txtModelYear.getText())
                .setOwner(new BoatOwner(txtOwnerFirstName.getText(), txtOwnerSurname.getText()))
                .build();
    }

    protected void loadBoatInsurance(BoatInsurance boatInsurance) {
        txtRegistrationNr.setText(boatInsurance.getBoat().getBoatModel());
        txtBoatModel.setText(boatInsurance.getBoat().getBoatModel());
        txtBoatType.setText(boatInsurance.getBoat().getBoatType());
        txtEngineHP.setText(String.valueOf(boatInsurance.getBoat().getEngineHP()));
        txtEngineType.setText(boatInsurance.getBoat().getEngineType());
        txtLengthInFt.setText(String.valueOf(boatInsurance.getBoat().getLengthInft()));
        txtModelYear.setText(boatInsurance.getBoat().getModelYear());
        txtOwnerFirstName.setText(boatInsurance.getBoat().getOwner().getFirstName());
        txtOwnerSurname.setText(boatInsurance.getBoat().getOwner().getLastName());
    }

    private void setCurrentInsuranceIsNew(boolean currentInsuranceIsNew) {
        this.currentInsuranceIsNew = currentInsuranceIsNew;
    }
}
