package controllers.insurance;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import models.builders.travelInsurance.TravelInsuranceBuilder;
import models.travelInsurance.TravelInsurance;

public class TravelInsuranceController {

    private final EmbeddedFieldsController embeddedFieldsController =
            new FXMLLoader(getClass().getResource("/org/view/embeddedInsFields.fxml")).getController();

    @FXML
    private TextField txtMaxCoverage;

    @FXML
    private void btnSave() {

    }

    @FXML
    private TravelInsurance getCurrentTravelInsurance() {
        return new TravelInsuranceBuilder()
                .setRegisteredTo(embeddedFieldsController.getTxtRegisteredTo().getText())
                .setAnnualPremium(embeddedFieldsController.getTxtAnnualPremium().getText())
                .setCoverageDescription(embeddedFieldsController.getTxtCoverageDescription().getText())
                .setDateOfIssue(embeddedFieldsController.getTxtDateOfIssue().getText())
                .setTotal(embeddedFieldsController.getTxtTotal().getText())
                .setMaxCoverage(txtMaxCoverage.getText())
                .setPremium("true") // <-- TODO: Fiks her
                .build();
    }
}
