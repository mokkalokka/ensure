package controllers.insurance;

import controllers.detailedCustomerController;
import models.insurance.Insurance;
import models.insurance.residenceInsurance.SecondaryResidenceInsurance;

public class SecondaryResidenceController extends ResidenceInsuranceController {

    private SecondaryResidenceInsurance myInsurance;

    @Override
    public void load() {

    }

    @Override
    public void displayExistingInsurance() {
        txtAddress.setText(myInsurance.getResidence().getAddress());
        txtResidenceType.setText(myInsurance.getResidence().getResidenceType());
        txtCondition.setText(myInsurance.getResidence().getCondition());
        txtConstructionMaterial.setText(myInsurance.getResidence().getConstructionMaterial());
        txtYearOfConstruction.setText(String.valueOf(myInsurance.getResidence().getYearOfConstruction()));
        txtSqMeters.setText(String.valueOf(myInsurance.getResidence().getSqMeters()));
        txtPropertyInsuranceAmount.setText(String.valueOf(myInsurance.getPropertyInsuranceAmount()));
        txtAssetsInsuranceAmount.setText(String.valueOf(myInsurance.getAssetsInsuranceAmount()));
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
    void displayResidenceFields() {

    }

    @Override
    public void displayNewInsurance() {
        embeddedFieldsController.displayNewInsurance(myCustomer);
        txtAddress.setEditable(true);
    }
}
