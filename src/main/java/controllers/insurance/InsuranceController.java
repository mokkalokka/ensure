package controllers.insurance;

import controllers.detailedCustomerController;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import models.customer.Customer;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.gui.ErrorDialog;
import models.insurance.Insurance;

public abstract class InsuranceController {

    Insurance myInsurance;
    Customer myCustomer;
    InsuranceState state;

    @FXML
    EmbeddedFieldsController embeddedFieldsController;
    @FXML
    detailedCustomerController parentController;

    public void load() {
        state.setFields(this);
    }

    @FXML
    void btnSave() {
        try {
            state.saveInsurance(this);
        } catch (InvalidCustomerException e) {
            e.printStackTrace();
            //TODO: display error window
        } catch (BuilderInputException e) {
            ErrorDialog errorDialog = new ErrorDialog("Feil i lagring", e.getMessage());
            errorDialog.show();
        }
        parentController.refreshTables();
    }

    @FXML
    void btnClose() {
        Stage currentStage = getCurrentStage();
        currentStage.close();
    }

    void displayExistingInsurance() {
        setCommonInsuranceFields();
        setUniqueInsuranceFields();
    }

    void displayNewInsurance() {
        embeddedFieldsController.displayNewInsurance(myCustomer);
    }

    // Setter feltene som er felles for alle forsikringer.
    private void setCommonInsuranceFields() {
        embeddedFieldsController.displayExistingInsurance(myInsurance);
    }

    // Setter feltene som er unike for de ulike forsikringstypene.
    abstract void setUniqueInsuranceFields();

    public void setParentController(detailedCustomerController parentController) {
        this.parentController = parentController;
    }

    Customer getCustomer() {
        return myCustomer;
    }

    public void setCustomer(Customer customer) {
        myCustomer = customer;
    }

    public void setState(InsuranceState state) {
        this.state = state;
    }

    public void setInsurance(Insurance insurance) {
        myInsurance = insurance;
    }

    void updateInsurance() throws BuilderInputException {
        myInsurance.setAnnualPremium(Double.parseDouble(embeddedFieldsController.getTxtAnnualPremium().getText()));
        myInsurance.setCoverageDescription(embeddedFieldsController.getTxtCoverageDescription().getText());
        myInsurance.setTotal(Double.parseDouble(embeddedFieldsController.getTxtTotal().getText()));
    }

    abstract Stage getCurrentStage();

    abstract Insurance getNewInsurance() throws BuilderInputException;

    abstract Insurance getEditedInsurance() throws BuilderInputException;
}
