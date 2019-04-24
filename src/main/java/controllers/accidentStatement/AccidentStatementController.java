package controllers.accidentStatement;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.builders.AccidentStatementBuilder;
import models.customer.Customer;
import models.accidentStatement.AccidentStatement;
import controllers.detailedCustomerController;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.gui.ErrorDialog;

public class AccidentStatementController {
    private Customer currentCustomer;
    private AccidentStatementState state;
    private AccidentStatement currentAccidentStatement;
    private detailedCustomerController parentController;

    @FXML
    private TextField txtAccidentType;

    @FXML
    private TextField txtAccidentNr;

    @FXML
    private DatePicker dateOfAccident;

    @FXML
    private TextField txtAppraisalAmount;

    @FXML
    private TextField txtDispersedCompensation;

    @FXML
    private TextArea txtAccidentDescription;

    @FXML
    private void btnClose() {
        Stage currentstage = getCurrentStage();
        currentstage.close();
    }

    @FXML
    private void btnSave() {
        try {
            state.saveAccidentStatement(this);
        } catch (InvalidCustomerException e) {
            e.printStackTrace();
            //TODO error vindu
        } catch (BuilderInputException e) {
            ErrorDialog errorDialog = new ErrorDialog("Feil i lagring", e.getMessage());
            errorDialog.show();
        }
        parentController.refreshTables();
    }

    public Customer getCustomer() {
        return currentCustomer;
    }

    public void setCustomer(Customer customer) {
        currentCustomer = customer;
    }

    public void displayNewAccidentStatement() {
        txtAccidentNr.setText(String.valueOf(currentAccidentStatement.getAccidentNr()));
    }

    public AccidentStatement getNewAccidentStatement() throws BuilderInputException {
        return new AccidentStatementBuilder()
                .setAccidentType(txtAccidentType.getText())
                .setRegisteredTo(txtAccidentNr.getText())
                .setDateOfAccident(dateOfAccident.getValue().toString())
                .setAppraisalAmount(txtAppraisalAmount.getText())
                .setDispersedCompensation(txtDispersedCompensation.getText())
                .setAccidentDescription(txtAccidentDescription.getText())
                .build();
    }

    public void displayExistingAccidentStatement() {
        txtAccidentType.setText(currentAccidentStatement.getAccidentType());
        txtAccidentNr.setText(String.valueOf(currentAccidentStatement.getAccidentNr()));
        dateOfAccident.setValue(currentAccidentStatement.getDateOfAccident());
        txtAppraisalAmount.setText(String.valueOf(currentAccidentStatement.getAppraisalAmount()));
        txtDispersedCompensation.setText(String.valueOf(currentAccidentStatement.getDispersedCompensation()));
        txtAccidentDescription.setText(currentAccidentStatement.getAccidentDescription());
    }

    public AccidentStatement getEditedAccidentStatement() throws BuilderInputException {
        return new AccidentStatementBuilder()
                .setAccidentType(txtAccidentType.getText())
                .setRegisteredTo(txtAccidentNr.getText())
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

    public void setParentController(detailedCustomerController parentController) {
        this.parentController = parentController;
    }

    public void load() {
        state.setFields(this);
    }

    private Stage getCurrentStage() {
        return (Stage) txtAccidentType.getScene().getWindow();
    }
}
