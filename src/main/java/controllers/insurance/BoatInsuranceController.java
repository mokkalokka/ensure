package controllers.insurance;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import models.builders.boatInsurance.BoatBuilder;
import models.builders.boatInsurance.BoatInsuranceBuilder;
import models.exceptions.builderExceptions.BuilderInputException;
import models.insurance.Insurance;
import models.insurance.boatInsurance.Boat;
import models.insurance.boatInsurance.BoatInsurance;
import models.insurance.boatInsurance.BoatOwner;

public class BoatInsuranceController extends InsuranceController {

    @FXML
    private JFXTextField txtOwnerSurname;
    @FXML
    private JFXTextField txtOwnerFirstName;
    @FXML
    private JFXTextField txtRegistrationNr;
    @FXML
    private JFXTextField txtBoatType;
    @FXML
    private JFXTextField txtLengthInFt;
    @FXML
    private JFXTextField txtModelYear;
    @FXML
    private JFXTextField txtEngineType;
    @FXML
    private JFXTextField txtBoatModel;
    @FXML
    private JFXTextField txtEngineHP;

    @Override
    void setUniqueInsuranceFields() {
        displayBoat();
    }

    private void displayBoat() {
        BoatInsurance myBoatInsurance = (BoatInsurance) myInsurance;
        Boat myBoat = myBoatInsurance.getBoat();

        txtRegistrationNr.setText(myBoat.getRegistrationNr());
        txtBoatType.setText(myBoat.getBoatType());
        txtBoatModel.setText(myBoat.getBoatModel());
        txtEngineHP.setText(String.valueOf(myBoat.getEngineHP()));
        txtEngineType.setText(myBoat.getEngineType());
        txtLengthInFt.setText(String.valueOf(myBoat.getLengthInft()));
        txtModelYear.setText(myBoat.getModelYear());
        txtOwnerFirstName.setText(myBoat.getOwner().getFirstName());
        txtOwnerSurname.setText(myBoat.getOwner().getLastName());
    }

    @Override
    public Insurance getNewInsurance() throws BuilderInputException {
        return new BoatInsuranceBuilder()
                .setRegisteredTo(embeddedFieldsController.getTxtRegisteredTo().getText())
                .setAnnualPremium(embeddedFieldsController.getTxtAnnualPremium().getText())
                .setCoverageDescription(embeddedFieldsController.getTxtCoverageDescription().getText())
                .setTotal(embeddedFieldsController.getTxtTotal().getText())
                .setBoat(getCurrentBoat())
                .build();
    }

    @Override
    void updateInsurance() throws BuilderInputException {
        super.updateInsurance();
        ((BoatInsurance) myInsurance).setBoat(getCurrentBoat());
    }

    @Override
    void checkForValidInput() throws BuilderInputException {
        // Kaster exception hvis noen feltene ikke er gyldig input.
        new BoatInsuranceBuilder()
                .setRegisteredTo(embeddedFieldsController.getTxtRegisteredTo().getText())
                .setAnnualPremium(embeddedFieldsController.getTxtAnnualPremium().getText())
                .setCoverageDescription(embeddedFieldsController.getTxtCoverageDescription().getText())
                .setTotal(embeddedFieldsController.getTxtTotal().getText())
                .setBoat(getCurrentBoat());
    }

    private Boat getCurrentBoat() throws BuilderInputException {
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

    @Override
    Stage getCurrentStage() {
        return (Stage) txtBoatModel.getScene().getWindow();
    }
}
