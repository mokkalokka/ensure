package controllers.accidentStatement;

import controllers.insurance.InsuranceController;
import controllers.insurance.NewInsurance;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
import models.gui.WindowHandler;

import java.io.IOException;

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
        Stage currentStage = getCurrentStage();
        currentStage.close();
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
        //TODO denne ma vise forsikringsid ikke id til kunde
        txtAccidentNr.setText(String.valueOf(currentCustomer.getInsuranceNr()));
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

    public void updateAccidentStatement() {
        assert currentAccidentStatement != null;

        // TODO: dette må vise error ved feil input.
        currentAccidentStatement.setAccidentDescription(txtAccidentDescription.getText());
        currentAccidentStatement.setAccidentType(txtAccidentType.getText());
        currentAccidentStatement.setAppraisalAmount(Double.parseDouble(txtAppraisalAmount.getText()));
        currentAccidentStatement.setDateOfAccident(dateOfAccident.getValue());
        currentAccidentStatement.setDispersedCompensation(Double.parseDouble(txtDispersedCompensation.getText()));
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
