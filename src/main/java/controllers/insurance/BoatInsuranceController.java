package controllers.insurance;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.builders.boatInsurance.BoatBuilder;
import models.builders.boatInsurance.BoatInsuranceBuilder;
import models.customer.Customer;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.gui.ErrorDialog;
import models.insurance.Insurance;
import models.insurance.boatInsurance.Boat;
import models.insurance.boatInsurance.BoatInsurance;
import models.insurance.boatInsurance.BoatOwner;
import controllers.detailedCustomerController;

public class BoatInsuranceController extends InsuranceController {

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

    public Insurance getNewInsurance() throws BuilderInputException {
        return new BoatInsuranceBuilder()
                .setRegisteredTo(embeddedFieldsController.getTxtRegisteredTo().getText())
                .setAnnualPremium(embeddedFieldsController.getTxtAnnualPremium().getText())
                .setCoverageDescription(embeddedFieldsController.getTxtCoverageDescription().getText())
                .setTotal(embeddedFieldsController.getTxtTotal().getText())
                .setBoat(getCurrentBoat())
                .build();
    }

    void updateInsurance() throws BuilderInputException {
        super.updateInsurance();
        ((BoatInsurance) myInsurance).setBoat(getCurrentBoat());
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
