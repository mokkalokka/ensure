package controllers.insurance;

import controllers.detailedCustomerController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.customer.Customer;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.insurance.Insurance;

public class ResidenceInsuranceController implements InsuranceController {

    private Customer myCustomer;
    private InsuranceState state;
    // private ResidenceInsurance myResidenceInsurance;

    @FXML detailedCustomerController parentController;

    @FXML
    private TextField txtCondition;


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
        return null;
    }

    @Override
    public void setCustomer(Customer customer) {

    }

    @Override
    public EmbeddedFieldsController getEmbeddedFieldsController() {
        return null;
    }

    @Override
    public Insurance getNewInsurance() {
        return null;
    }

    @Override
    public Insurance getEditedInsurance() {
        return null;
    }

    @Override
    public void load() {

    }

    @Override
    public void setState(InsuranceState state) {
        this.state = state;
    }

    @Override
    public void setInsurance(Insurance insurance) {

    }

    @Override
    public void loadInsurance() {

    }

    @Override
    public void setParent(detailedCustomerController parentController) {
        this.parentController = parentController;

    }
}
