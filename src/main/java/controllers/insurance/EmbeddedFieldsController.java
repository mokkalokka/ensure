package controllers.insurance;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.customer.Customer;
import models.insurance.Insurance;

import java.time.LocalDate;

public class EmbeddedFieldsController {


    @FXML
    private TextField txtRegisteredTo;
    @FXML
    private TextField txtDateOfIssue;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtCoverageDescription;
    @FXML
    private TextField txtAnnualPremium;



    void displayNewInsurance(Customer ownerOfInsurance) {
        txtRegisteredTo.setText(String.valueOf(ownerOfInsurance.getInsuranceNr()));
        txtDateOfIssue.setText(String.valueOf(LocalDate.now()));
    }

    void displayExistingInsurance(Insurance insurance) {
        txtRegisteredTo.setText(String.valueOf(insurance.getRegisteredTo()));
        txtDateOfIssue.setText(String.valueOf(insurance.getDateOfIssue()));
        txtTotal.setText(String.valueOf(insurance.getTotal()));
        txtCoverageDescription.setText(insurance.getCoverageDescription());
        txtAnnualPremium.setText(String.valueOf(insurance.getAnnualPremium()));
    }

    TextField getTxtRegisteredTo() {
        return txtRegisteredTo;
    }

    TextField getTxtDateOfIssue() {
        return txtDateOfIssue;
    }

    TextField getTxtTotal() {
        return txtTotal;
    }

    TextField getTxtCoverageDescription() {
        return txtCoverageDescription;
    }

    TextField getTxtAnnualPremium() {
        return txtAnnualPremium;
    }
}
