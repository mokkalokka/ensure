package controllers.insurance;

import controllers.detailedCustomerController;
import models.insurance.Insurance;
import models.insurance.residenceInsurance.SecondaryResidenceInsurance;

public class SecondaryResidenceController extends ResidenceInsuranceController {


    @Override
    public void load() {

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
    public void setInsurance(Insurance insurance) {

    }

    @Override
    public void displayNewInsurance() {
        embeddedFieldsController.displayNewInsurance(myCustomer);
        txtAddress.setEditable(true);
    }
}
