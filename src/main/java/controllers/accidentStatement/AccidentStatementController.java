package controllers.accidentStatement;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.builders.AccidentStatementBuilder;
import models.customer.Customer;
import models.accidentStatement.AccidentStatement;

public class AccidentStatementController {
    private Customer currentCustomer;
    private AccidentStatementState state;
    private AccidentStatement currentAccidentStatement;

    @FXML
    private TextField txtAccidentType;

    @FXML
    private TextField txtRegisteredTo;

    @FXML
    private DatePicker dateOfAccident;

    @FXML
    private TextField txtAppraisalAmount;

    @FXML
    private TextField txtDispersedCompensation;

    @FXML
    private TextArea txtAccidentDescription;



    public Customer getCustomer() {
        return currentCustomer;
    }

    public void setCustomer(Customer customer) {
        currentCustomer = customer;
    }

    public void displayNewAccidentStatement() {
        txtRegisteredTo.setText(String.valueOf(currentCustomer.getInsuranceNr()));
    }

    public AccidentStatement getNewAccidentStatement() {
        return new AccidentStatementBuilder()
                .setAccidentType(txtAccidentType.getText())
                .setRegisteredTo(txtRegisteredTo.getText())
                .setDateOfAccident(dateOfAccident.getValue().toString())
                .setAppraisalAmount(txtAppraisalAmount.getText())
                .setDispersedCompensation(txtDispersedCompensation.getText())
                .setAccidentDescription(txtAccidentDescription.getText())
                .build();
    }

    public void displayExistingAccidentStatement() {
        txtAccidentType.setText(currentAccidentStatement.getAccidentType());
        txtRegisteredTo.setText(String.valueOf(currentAccidentStatement.getRegisteredTo()));
        dateOfAccident.setValue(currentAccidentStatement.getDateOfAccident());
        txtAppraisalAmount.setText(String.valueOf(currentAccidentStatement.getAppraisalAmount()));
        txtDispersedCompensation.setText(String.valueOf(currentAccidentStatement.getDispersedCompensation()));
        txtAccidentDescription.setText(currentAccidentStatement.getAccidentDescription());
    }

    public AccidentStatement getEditedAccidentStatement() {
        return new AccidentStatementBuilder()
                .setAccidentType(txtAccidentType.getText())
                .setRegisteredTo(txtRegisteredTo.getText())
                .setDateOfAccident(dateOfAccident.getValue().toString())
                .setAppraisalAmount(txtAppraisalAmount.getText())
                .setDispersedCompensation(txtDispersedCompensation.getText())
                .setAccidentDescription(txtAccidentDescription.getText())
                .build();
    }

    public void setAccidentStatement(AccidentStatement accidentStatement) {
        currentAccidentStatement = accidentStatement;
    }

    public void setState(AccidentStatementState state) {
        this.state = state;
    }

}
