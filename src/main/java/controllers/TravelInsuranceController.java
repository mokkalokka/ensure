package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import models.builders.travelInsurance.TravelInsuranceBuilder;
import models.travelInsurance.TravelInsurance;

public class TravelInsuranceController {

    private final InsuranceController insuranceController =
            new FXMLLoader(getClass().getResource("/org/view/insurance.fxml")).getController();

    @FXML
    private TextField txtMaxCoverage;

    @FXML
    private void btnSave() {

    }

    @FXML
    private TravelInsurance getCurrentTravelInsurance() {
        return new TravelInsuranceBuilder()
                .setRegisteredTo(insuranceController.getTxtRegisteredTo().getText())
                .setAnnualPremium(insuranceController.getTxtAnnualPremium().getText())
                .setCoverageDescription(insuranceController.getTxtCoverageDescription().getText())
                .setDateOfIssue(insuranceController.getTxtDateOfIssue().getText())
                .setTotal(insuranceController.getTxtTotal().getText())
                .setMaxCoverage(txtMaxCoverage.getText())
                .setPremium("true") // <-- TODO: Fiks her
                .build();
    }
}
