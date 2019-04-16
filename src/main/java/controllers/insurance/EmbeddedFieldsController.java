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



    public void displayNewInsurance(Customer ownerOfInsurance) {
        txtRegisteredTo.setText(String.valueOf(ownerOfInsurance.getInsuranceNr()));
        txtDateOfIssue.setText(String.valueOf(LocalDate.now()));
    }

    public void displayExistingInsurance(Insurance insurance) {
        txtRegisteredTo.setText(String.valueOf(insurance.getRegisteredTo()));
        txtDateOfIssue.setText(String.valueOf(insurance.getDateOfIssue()));
        txtTotal.setText(String.valueOf(insurance.getTotal()));
        txtCoverageDescription.setText(insurance.getCoverageDescription());
        txtAnnualPremium.setText(String.valueOf(txtAnnualPremium));
    }

    public TextField getTxtRegisteredTo() {
        return txtRegisteredTo;
    }

    public TextField getTxtDateOfIssue() {
        return txtDateOfIssue;
    }

    public TextField getTxtTotal() {
        return txtTotal;
    }

    public TextField getTxtCoverageDescription() {
        return txtCoverageDescription;
    }

    public TextField getTxtAnnualPremium() {
        return txtAnnualPremium;
    }
}
