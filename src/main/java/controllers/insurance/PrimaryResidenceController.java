package controllers.insurance;

import models.builders.residenceInsurance.PrimaryResidenceInsuranceBuilder;
import models.exceptions.builderExceptions.BuilderInputException;
import models.insurance.residenceInsurance.PrimaryResidenceInsurance;

public class PrimaryResidenceController extends ResidenceInsuranceController {

    @Override
    void displayNewInsurance() {
        super.displayNewInsurance();
        txtAddress.setText(myCustomer.getInvoiceAddress());
        txtAddress.setEditable(false);
    }

    @Override
    void setUniqueInsuranceFields() {
        super.setUniqueInsuranceFields();
        txtAddress.setEditable(false);
    }

    @Override
    void checkForValidInput() throws BuilderInputException {
        // Kaster exception hvis noen feltene ikke er gyldig input.
        new PrimaryResidenceInsuranceBuilder()
                .setRegisteredTo(embeddedFieldsController.getTxtRegisteredTo().getText())
                .setAnnualPremium(embeddedFieldsController.getTxtAnnualPremium().getText())
                .setCoverageDescription(embeddedFieldsController.getTxtCoverageDescription().getText())
                .setTotal(embeddedFieldsController.getTxtTotal().getText())
                .setResidence(super.getResidence())
                .setPropertyInsuranceAmount(txtPropertyInsuranceAmount.getText())
                .setAssetsInsuranceAmount(txtAssetsInsuranceAmount.getText());
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
}
