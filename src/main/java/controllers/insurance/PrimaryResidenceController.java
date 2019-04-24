package controllers.insurance;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.exceptions.customerExceptions.InvalidCustomerException;
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
    public void setInsurance(Insurance insurance) {
        myInsurance = (PrimaryResidenceInsurance) insurance;
    }

    @Override
    void displayResidenceFields() {
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
    public void displayNewInsurance() {
        embeddedFieldsController.displayNewInsurance(myCustomer);
        txtAddress.setText(myCustomer.getInvoiceAddress());
        txtAddress.setEditable(false);
    }
}
