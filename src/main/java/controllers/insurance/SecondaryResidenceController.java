package controllers.insurance;

import controllers.detailedCustomerController;
import models.builders.residenceInsurance.PrimaryResidenceInsuranceBuilder;
import models.builders.residenceInsurance.SecondaryResidenceInsuranceBuilder;
import models.exceptions.builderExceptions.BuilderInputException;
import models.insurance.Insurance;
import models.insurance.residenceInsurance.SecondaryResidenceInsurance;

public class SecondaryResidenceController extends ResidenceInsuranceController {


    @Override
    public void load() {

    }

    @Override
    public Insurance getNewInsurance() throws BuilderInputException {
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

    @Override
    public Insurance getEditedInsurance() throws BuilderInputException {
        return new SecondaryResidenceInsuranceBuilder()
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
    public void setInsurance(Insurance insurance) {
        myInsurance = (SecondaryResidenceInsurance) insurance;
    }

    @Override
    public void displayNewInsurance() {
        embeddedFieldsController.displayNewInsurance(myCustomer);
        txtAddress.setEditable(true);
    }
}
