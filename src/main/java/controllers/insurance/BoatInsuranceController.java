package controllers.insurance;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.builders.boatInsurance.BoatBuilder;
import models.builders.boatInsurance.BoatInsuranceBuilder;
import models.customer.Customer;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.insurance.Insurance;
import models.insurance.boatInsurance.Boat;
import models.insurance.boatInsurance.BoatInsurance;
import models.insurance.boatInsurance.BoatOwner;
import controllers.detailedCustomerController;

public class BoatInsuranceController implements InsuranceController {

    private Customer myCustomer;
    private InsuranceState state;
    private BoatInsurance myInsurance;

    @FXML
    private Parent embeddedFields;
    @FXML
    private EmbeddedFieldsController embeddedFieldsController;

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

    private detailedCustomerController parent;

    @FXML
    private void btnSave() {
        try {
            state.saveInsurance(this);
        } catch (InvalidCustomerException e) {
            e.printStackTrace();
            // TODO: display error window
        }
        parent.refreshTables();
    }

    @FXML
    private void btnClose() {
        Stage currentStage = getCurrentStage();
        currentStage.close();
    }


    public void load() {
        state.setFields(this);
    }

    public void displayExistingInsurance() {
        embeddedFieldsController.displayExistingInsurance(myInsurance);
        displayBoat();
    }

    private void displayBoat() {
        txtRegistrationNr.setText(myInsurance.getBoat().getBoatModel());
        txtBoatModel.setText(myInsurance.getBoat().getBoatModel());
        txtBoatType.setText(myInsurance.getBoat().getBoatType());
        txtEngineHP.setText(String.valueOf(myInsurance.getBoat().getEngineHP()));
        txtEngineType.setText(myInsurance.getBoat().getEngineType());
        txtLengthInFt.setText(String.valueOf(myInsurance.getBoat().getLengthInft()));
        txtModelYear.setText(myInsurance.getBoat().getModelYear());
        txtOwnerFirstName.setText(myInsurance.getBoat().getOwner().getFirstName());
        txtOwnerSurname.setText(myInsurance.getBoat().getOwner().getLastName());
    }

    public Insurance getNewInsurance() {
        return new BoatInsuranceBuilder()
                .setRegisteredTo(embeddedFieldsController.getTxtRegisteredTo().getText())
                .setAnnualPremium(embeddedFieldsController.getTxtAnnualPremium().getText())
                .setCoverageDescription(embeddedFieldsController.getTxtCoverageDescription().getText())
                .setTotal(embeddedFieldsController.getTxtTotal().getText())
                .setBoat(getCurrentBoat())
                .build();
    }

    public Insurance getEditedInsurance() {
        return new BoatInsuranceBuilder()
                .setInsuranceID(myInsurance.getInsuranceID())
                .setRegisteredTo(embeddedFieldsController.getTxtRegisteredTo().getText())
                .setAnnualPremium(embeddedFieldsController.getTxtAnnualPremium().getText())
                .setCoverageDescription(embeddedFieldsController.getTxtCoverageDescription().getText())
                .setTotal(embeddedFieldsController.getTxtTotal().getText())
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

    public EmbeddedFieldsController getEmbeddedFieldsController() {
        return embeddedFieldsController;
    }

    public void setState(InsuranceState state) {
        this.state = state;
    }

    @Override
    public void setInsurance(Insurance insurance) {
        myInsurance = (BoatInsurance) insurance;
    }

    public void setCustomer(Customer customer) {
        myCustomer = customer;
    }

    public Customer getCustomer() {
        return myCustomer;
    }

    private Stage getCurrentStage() {
        return (Stage) txtBoatModel.getScene().getWindow();
    }

    public void setParent(detailedCustomerController parentController) {
        this.parent = parentController;
    }

    @Override
    public void displayNewInsurance() {
        embeddedFieldsController.displayNewInsurance(myCustomer);
    }

}
