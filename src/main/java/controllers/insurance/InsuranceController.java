package controllers.insurance;

import controllers.DetailedCustomerController;
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
    DetailedCustomerController parentController;

    public void load() {
        state.setFields(this);
    }

    @FXML
    void btnSave() {
        try {
            state.saveInsurance(this);
        } catch (InvalidCustomerException e) {
            new ErrorDialog("Feil ved lagring", e.getMessage()).show();
        } catch (BuilderInputException e) {
            new ErrorDialog("Feil ved lagring", e.getMessage()).show();
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

    public void setParentController(DetailedCustomerController parentController) {
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
        checkForValidInput();
        myInsurance.setAnnualPremium(Double.parseDouble(embeddedFieldsController.getTxtAnnualPremium().getText()));
        myInsurance.setCoverageDescription(embeddedFieldsController.getTxtCoverageDescription().getText());
        myInsurance.setTotal(Double.parseDouble(embeddedFieldsController.getTxtTotal().getText()));
    }

    // Skal kaste exception hvis noen feltene ikke er gyldig input.
    abstract void checkForValidInput() throws BuilderInputException;

    abstract Stage getCurrentStage();

    abstract Insurance getNewInsurance() throws BuilderInputException;

}
