package controllers.insurance;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.builders.residenceInsurance.ResidenceBuilder;
import models.exceptions.builderExceptions.BuilderInputException;
import models.insurance.Insurance;
import models.insurance.residenceInsurance.Residence;
import models.insurance.residenceInsurance.ResidenceInsurance;

public abstract class ResidenceInsuranceController extends InsuranceController {

    @FXML
    protected TextField txtAddress;
    @FXML
    protected TextField txtResidenceType;
    @FXML
    protected TextField txtCondition;
    @FXML
    protected TextField txtConstructionMaterial;
    @FXML
    protected TextField txtSqMeters;
    @FXML
    protected TextField txtPropertyInsuranceAmount;
    @FXML
    protected TextField txtAssetsInsuranceAmount;
    @FXML
    protected TextField txtYearOfConstruction;

    Residence getResidence() throws BuilderInputException {
        return new ResidenceBuilder()
                .setAddress(txtAddress.getText())
                .setCondition(txtCondition.getText())
                .setConstructionMaterial(txtConstructionMaterial.getText())
                .setSqMeters(txtSqMeters.getText())
                .setType(txtResidenceType.getText())
                .setYearOfConstruction(txtYearOfConstruction.getText())
                .build();
    }

    @Override
    void setUniqueInsuranceFields() {
        ResidenceInsurance myResidenceInsurance = (ResidenceInsurance) myInsurance;
        Residence myResidence = myResidenceInsurance.getResidence();

        txtAddress.setText(myResidence.getAddress());
        txtResidenceType.setText(myResidence.getResidenceType());
        txtCondition.setText(myResidence.getCondition());
        txtConstructionMaterial.setText(myResidence.getConstructionMaterial());
        txtYearOfConstruction.setText(String.valueOf(myResidence.getYearOfConstruction()));
        txtSqMeters.setText(String.valueOf(myResidence.getSqMeters()));
        txtPropertyInsuranceAmount.setText(String.valueOf(myResidenceInsurance.getPropertyInsuranceAmount()));
        txtAssetsInsuranceAmount.setText(String.valueOf(myResidenceInsurance.getAssetsInsuranceAmount()));
    }

    @FXML
    void updateInsurance() throws BuilderInputException {
        super.updateInsurance();
        ((ResidenceInsurance) myInsurance).setPropertyInsuranceAmount(Double.parseDouble(txtPropertyInsuranceAmount.getText()));
        ((ResidenceInsurance) myInsurance).setAssetsInsuranceAmount(Double.parseDouble(txtAssetsInsuranceAmount.getText()));
        ((ResidenceInsurance) myInsurance).setResidence(getResidence());
        parentController.refreshTables();
    }

    @FXML
    Stage getCurrentStage() {
        return (Stage) txtCondition.getScene().getWindow();
    }

    abstract Insurance getEditedInsurance() throws BuilderInputException;

    abstract Insurance getNewInsurance() throws BuilderInputException;

}
