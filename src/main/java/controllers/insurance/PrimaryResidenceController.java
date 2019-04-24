package controllers.insurance;

import models.builders.residenceInsurance.PrimaryResidenceInsuranceBuilder;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.gui.ErrorDialog;
import models.insurance.Insurance;
import models.insurance.residenceInsurance.PrimaryResidenceInsurance;

public class PrimaryResidenceController extends ResidenceInsuranceController {

    private PrimaryResidenceInsurance myInsurance;

    @Override
    public void btnSave() {
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
    public PrimaryResidenceInsurance getNewInsurance() throws BuilderInputException {
        return new PrimaryResidenceInsuranceBuilder()
                .setRegisteredTo(embeddedFieldsController.getTxtRegisteredTo().getText())
                .setAnnualPremium(embeddedFieldsController.getTxtAnnualPremium().getText())
                .setCoverageDescription(embeddedFieldsController.getTxtCoverageDescription().getText())
                .setTotal(embeddedFieldsController.getTxtTotal().getText())
                .setResidence(super.getResidence())
                .setPropertyInsuranceAmount(txtPropertyInsuranceAmount.getText())
                .setAssetsInsuranceAmount(txtAssetsInsuranceAmount.getText())
                .build();
    }

    @Override
    public PrimaryResidenceInsurance getEditedInsurance() throws BuilderInputException {
        return new PrimaryResidenceInsuranceBuilder()
                .setInsuranceID(myInsurance.getInsuranceID())
                .setRegisteredTo(embeddedFieldsController.getTxtRegisteredTo().getText())
                .setAnnualPremium(embeddedFieldsController.getTxtAnnualPremium().getText())
                .setCoverageDescription(embeddedFieldsController.getTxtCoverageDescription().getText())
                .setTotal(embeddedFieldsController.getTxtTotal().getText())
                .setResidence(super.getResidence())
                .setPropertyInsuranceAmount(txtPropertyInsuranceAmount.getText())
                .setAssetsInsuranceAmount(txtAssetsInsuranceAmount.getText())
                .build();
    }

    @Override
    public void displayNewInsurance() {
        embeddedFieldsController.displayNewInsurance(myCustomer);
        txtAddress.setText(myCustomer.getInvoiceAddress());
        txtAddress.setEditable(false);
    }

    @Override
    public void setInsurance(Insurance insurance) {
        myInsurance = (PrimaryResidenceInsurance) insurance;
    }
}
