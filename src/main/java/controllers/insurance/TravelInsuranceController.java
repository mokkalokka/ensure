package controllers.insurance;

import com.jfoenix.controls.JFXRadioButton;
import controllers.detailedCustomerController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import models.builders.travelInsurance.TravelInsuranceBuilder;
import models.exceptions.builderExceptions.BuilderInputException;
import models.insurance.Insurance;
import models.travelInsurance.TravelInsurance;

public class TravelInsuranceController extends InsuranceController {

    @FXML
    private TextField txtMaxCoverage;
    @FXML
    private Label lblCoverageArea;
    @FXML
    private ToggleGroup radioGroup;
    @FXML
    private JFXRadioButton radioStandard;
    @FXML
    private JFXRadioButton radioPremium;

    @Override
    public void load() {
        super.load();
        setSelectedRadioToggle();
    }

    @FXML
    public void initialize() {
        radioStandard.setUserData("STANDARD");
        radioPremium.setUserData("PREMIUM");

        radioGroup.selectedToggleProperty().addListener((ov, oldToggle, newToggle) -> {
            if (radioGroup.getSelectedToggle() != null) {
                String insuranceType = radioGroup.getSelectedToggle().getUserData().toString();
                setLblCoverageArea(insuranceType);
            }
        });
    }

    @FXML
    private void setLblCoverageArea(String insuranceType) {
        if (insuranceType.equals("STANDARD")) {
            lblCoverageArea.setText("Standard reisemål for reising opptil 30 dager.");
        }
        else if (insuranceType.equals("PREMIUM")) {
            lblCoverageArea.setText("Standard og høyrisiko reisemål for reising i opptil 50 dager.");
        }
    }

    @Override
    public Insurance getNewInsurance() throws BuilderInputException {
        return new TravelInsuranceBuilder()
                .setRegisteredTo(embeddedFieldsController.getTxtRegisteredTo().getText())
                .setAnnualPremium(embeddedFieldsController.getTxtAnnualPremium().getText())
                .setCoverageDescription(embeddedFieldsController.getTxtCoverageDescription().getText())
                .setDateOfIssue(embeddedFieldsController.getTxtDateOfIssue().getText())
                .setTotal(embeddedFieldsController.getTxtTotal().getText())
                .setMaxCoverage(txtMaxCoverage.getText())
                .setPremium(getSelectedRadioValue())
                .build();
    }

    @Override
    void updateInsurance() throws BuilderInputException {
        super.updateInsurance();
        ((TravelInsurance) myInsurance).setMaxCoverage(Double.parseDouble(txtMaxCoverage.getText()));
        ((TravelInsurance) myInsurance).setPremium(getSelectedRadioValue());
    }

    private void setSelectedRadioToggle() {
        TravelInsurance myTravelInsurance;

        if ((myTravelInsurance = (TravelInsurance) myInsurance) != null) {
            radioPremium.setSelected(myTravelInsurance.isPremium());
        } else {
            radioStandard.setSelected(true);
        }
    }

    private boolean getSelectedRadioValue() {
        return radioGroup.getSelectedToggle()
                .getUserData()
                .equals("PREMIUM");
    }

    @Override
    void setUniqueInsuranceFields() {
        TravelInsurance myTravelInsurance = (TravelInsurance) myInsurance;
        txtMaxCoverage.setText(String.valueOf(myTravelInsurance.getMaxCoverage()));
    }

    @FXML
    Stage getCurrentStage() {
        return (Stage) lblCoverageArea.getScene().getWindow();
    }

}
