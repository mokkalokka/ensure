package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import models.builders.boatInsurance.BoatBuilder;
import models.builders.boatInsurance.BoatInsuranceBuilder;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.insurance.InsuranceHandler;
import models.insurance.boatInsurance.Boat;
import models.insurance.boatInsurance.BoatInsurance;
import models.insurance.boatInsurance.BoatOwner;

public class BoatInsuranceController {

    private final InsuranceController insuranceController =
            new FXMLLoader(getClass().getResource("/org/view/insurance.fxml")).getController();
    private boolean currentInsuranceIsNew;

    @FXML
    TextField txtOwnerSurname;
    @FXML
    TextField txtOwnerFirstName;
    @FXML
    TextField txtRegistrationNr;
    @FXML
    TextField txtBoatType;
    @FXML
    TextField txtLengthInFt;
    @FXML
    TextField txtModelYear;
    @FXML
    TextField txtEngineType;
    @FXML
    TextField txtBoatModel;
    @FXML
    TextField txtEngineHP;

    @FXML
    private void btnSave() {
        if (currentInsuranceIsNew) {
            try {
                saveNewBoatInsurance();
                currentInsuranceIsNew = false;
            } catch (NoSuchCustomerException e) {
                // TODO: error window
                e.printStackTrace();
            }
        }
    }

    private void saveNewBoatInsurance() throws NoSuchCustomerException {
        InsuranceHandler insuranceHandler = new InsuranceHandler();
        insuranceHandler.addNewInsurance(getCurrentBoatInsurance());
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

    private void loadBoatInsurance(BoatInsurance boatInsurance) {
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
