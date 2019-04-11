package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.customer.Customer;
import models.insurance.Insurance;

import java.time.LocalDate;

public class InsuranceController {

    Insurance currentInsurance;

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


    public void btnBack() {
        getCurrentStage().close();
    }


    private Stage getCurrentStage(){
        return (Stage) txtRegisteredTo.getScene().getWindow();
    }


    public void displayInsurance(Insurance anInsurance) {
        currentInsurance = anInsurance;

        txtRegisteredTo.setText(String.valueOf(currentInsurance.getRegisteredTo()));
        txtDateOfIssue.setText(String.valueOf(currentInsurance.getDateOfIssue()));
        txtTotal.setText(String.valueOf(currentInsurance.getTotal()));
        txtCoverageDescription.setText(currentInsurance.getCoverageDescription());
        txtAnnualPremium.setText(String.valueOf(txtAnnualPremium));
    }

    public void setCreateNewInsuranceState(Customer customer) {
        txtRegisteredTo.setText(String.valueOf(customer.getInsuranceNr()));
        txtDateOfIssue.setText(String.valueOf(LocalDate.now()));
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
