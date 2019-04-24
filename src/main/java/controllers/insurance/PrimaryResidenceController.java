package controllers.insurance;

import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.gui.ErrorDialog;
import models.insurance.Insurance;
import models.insurance.residenceInsurance.PrimaryResidenceInsurance;

public class PrimaryResidenceController extends ResidenceInsuranceController {

    private PrimaryResidenceInsurance myInsurance;

    @Override
    public void load() {
        try {
            state.saveInsurance(this);
        } catch (InvalidCustomerException e) {
            e.printStackTrace();
            // TODO: display error window
        } catch (BuilderInputException e) {
            ErrorDialog errorDialog = new ErrorDialog("Feil ved lagring ", e.getMessage());
            errorDialog.show();
        }
        parentController.refreshTables();
    }

    @Override
    public void displayExistingInsurance() {
        embeddedFieldsController.displayExistingInsurance(myInsurance);
        displayResidenceFields();

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
    public void displayNewInsurance() {
        embeddedFieldsController.displayNewInsurance(myCustomer);
        txtAddress.setText(myCustomer.getInvoiceAddress());
        txtAddress.setEditable(false);
    }
}
