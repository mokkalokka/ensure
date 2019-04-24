package controllers.insurance;

import controllers.detailedCustomerController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.customer.Customer;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.insurance.Insurance;

public abstract class ResidenceInsuranceController implements InsuranceController {

    Customer myCustomer;
    InsuranceState state;
    // private ResidenceInsurance myResidenceInsurance;

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
    public abstract void displayExistingInsurance();

    @Override
    public abstract Insurance getNewInsurance();

    @Override
    public abstract Insurance getEditedInsurance();

    @Override
    public abstract void setInsurance(Insurance insurance);

    abstract void displayResidenceFields();

    @Override
    public abstract void displayNewInsurance();
}
