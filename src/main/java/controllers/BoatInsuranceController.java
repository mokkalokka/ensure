package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import models.builders.boatInsurance.BoatBuilder;
import models.builders.boatInsurance.BoatInsuranceBuilder;
import models.insurance.boatInsurance.Boat;
import models.insurance.boatInsurance.BoatInsurance;
import models.insurance.boatInsurance.BoatOwner;

public class BoatInsuranceController {

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

    public BoatInsurance createBoatInsurance() {
        Boat boat = new BoatBuilder()
                .setRegistrationNr(txtRegistrationNr.getText())
                .setBoatModel(txtBoatModel.getText())
                .setBoatType(txtBoatType.getText())
                .setEngineHP(txtEngineHP.getText())
                .setEngineType(txtEngineType.getText())
                .setLengthInft(txtLengthInFt.getText())
                .setModelYear(txtModelYear.getText())
                .setOwner(new BoatOwner(txtOwnerFirstName.getText(), txtOwnerSurname.getText()))
                .build();
        return null;
    }

    public void loadBoatInsurance(BoatInsurance boatInsurance) {
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

    public BoatInsurance saveBoatInsurance() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/view/insurance.fxml"));
        InsuranceController insuranceController = loader.getController();
        insuranceController.getTxtAnnualPremium();
        // TODO: Fiks her.
        return null;
    }
}
