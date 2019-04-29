package controllers.insurance;

import models.builders.residenceInsurance.PrimaryResidenceInsuranceBuilder;
import models.builders.residenceInsurance.SecondaryResidenceInsuranceBuilder;
import models.exceptions.builderExceptions.BuilderInputException;
import models.insurance.residenceInsurance.SecondaryResidenceInsurance;

public class SecondaryResidenceController extends ResidenceInsuranceController {

    @Override
    void setUniqueInsuranceFields() {
        super.setUniqueInsuranceFields();
        txtAddress.setEditable(true);
    }

    @Override
    void checkForValidInput() throws BuilderInputException {
        // Kaster exception hvis noen feltene ikke er gyldig input.
        new SecondaryResidenceInsuranceBuilder()
                .setRegisteredTo(embeddedFieldsController.getTxtRegisteredTo().getText())
                .setAnnualPremium(embeddedFieldsController.getTxtAnnualPremium().getText())
                .setCoverageDescription(embeddedFieldsController.getTxtCoverageDescription().getText())
                .setTotal(embeddedFieldsController.getTxtTotal().getText())
                .setResidence(super.getResidence())
                .setPropertyInsuranceAmount(txtPropertyInsuranceAmount.getText())
                .setAssetsInsuranceAmount(txtAssetsInsuranceAmount.getText());
    }

    @Override
    SecondaryResidenceInsurance getNewInsurance() throws BuilderInputException {
        return new SecondaryResidenceInsuranceBuilder()
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
