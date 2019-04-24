package controllers.insurance;

import controllers.detailedCustomerController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.customer.Customer;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.insurance.Insurance;
import models.insurance.residenceInsurance.ResidenceInsurance;

public abstract class ResidenceInsuranceController implements InsuranceController {

    Customer myCustomer;
    InsuranceState state;
    ResidenceInsurance myInsurance;

    @FXML detailedCustomerController parentController;
    @FXML EmbeddedFieldsController embeddedFieldsController;

    @FXML
    protected TextField txtAddress;
    @FXML
    protected TextField txtResidenceType;
    @FXML
    protected TextField txtCondition;
    @FXML
    protected TextField txtConstructionMaterial;
    @FXML
    protected TextField txtSqMeters;
    @FXML
    protected TextField txtPropertyInsuranceAmount;
    @FXML
    protected TextField txtAssetsInsuranceAmount;
    @FXML
    protected TextField txtYearOfConstruction;


    @FXML
    private void btnSave() {
        try {
            state.saveInsurance(this);
        } catch (InvalidCustomerException e) {
            e.printStackTrace();
            // TODO: display error window
        }
        parentController.refreshTables();
    }

    @FXML
    private void btnClose() {
        Stage currentStage = getCurrentStage();
        currentStage.close();
    }

    @FXML
    private Stage getCurrentStage() {
        return (Stage) txtCondition.getScene().getWindow();
    }

    @Override
    public Customer getCustomer() {
        return myCustomer;
    }

    @Override
    public void setCustomer(Customer customer) {
        myCustomer = customer;
    }

    @Override
    public void setParentController(detailedCustomerController parentController) {
        this.parentController = parentController;
    }

    @Override
    public EmbeddedFieldsController getEmbeddedFieldsController() {
        return embeddedFieldsController;
    }

    @Override
    public void setState(InsuranceState state) {
        this.state = state;
    }

    @Override
    public abstract void load();

    @Override
    public void displayExistingInsurance() {
        embeddedFieldsController.displayExistingInsurance(myInsurance);
        displayResidenceFields();
    }

    @Override
    public abstract Insurance getNewInsurance();

    @Override
    public abstract Insurance getEditedInsurance();

    @Override
    public void setInsurance(Insurance insurance) {
        myInsurance = (ResidenceInsurance) insurance;
    }

    public void displayResidenceFields() {
        txtAddress.setText(myInsurance.getResidence().getAddress());
        txtResidenceType.setText(myInsurance.getResidence().getResidenceType());
        txtCondition.setText(myInsurance.getResidence().getCondition());
        txtConstructionMaterial.setText(myInsurance.getResidence().getConstructionMaterial());
        txtYearOfConstruction.setText(String.valueOf(myInsurance.getResidence().getYearOfConstruction()));
        txtSqMeters.setText(String.valueOf(myInsurance.getResidence().getSqMeters()));
        txtPropertyInsuranceAmount.setText(String.valueOf(myInsurance.getPropertyInsuranceAmount()));
        txtAssetsInsuranceAmount.setText(String.valueOf(myInsurance.getAssetsInsuranceAmount()));
    }

    @Override
    public abstract void displayNewInsurance();
}
