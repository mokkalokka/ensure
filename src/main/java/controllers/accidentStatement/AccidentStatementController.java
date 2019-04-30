package controllers.accidentStatement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import controllers.insurance.InsuranceController;
import controllers.insurance.NewInsurance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.accidentStatement.Witness;
import models.builders.AccidentStatementBuilder;
import models.customer.Customer;
import models.accidentStatement.AccidentStatement;
import controllers.detailedCustomerController;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.gui.ErrorDialog;
import models.gui.WindowHandler;

import java.io.IOException;
import java.util.ArrayList;

public class AccidentStatementController {

    private Customer currentCustomer;
    private AccidentStatementState state;
    private AccidentStatement currentAccidentStatement;
    private detailedCustomerController parentController;
    private ObservableList<Witness> observableWitnessList;



    @FXML
    private JFXTextField txtAccidentType;
    @FXML
    private JFXTextField txtAccidentNr;
    @FXML
    private JFXDatePicker dateOfAccident;
    @FXML
    private JFXTextField txtAppraisalAmount;
    @FXML
    private JFXTextField txtDispersedCompensation;
    @FXML
    private JFXTextArea txtAccidentDescription;

    @FXML
    private TableView<Witness> tblWitness;
    @FXML
    private TableColumn<Witness, String> clmnFirstName;
    @FXML
    private TableColumn<Witness, String> clmnSurname;
    @FXML
    private TableColumn<Witness, String> clmnContactInfo;

    @FXML
    public void initialize() {

        tblWitness.setRowFactory(tableView -> {

            //Tom rad
            TableRow<Witness> aRow = new TableRow<>();

            ContextMenu rowMenu = new ContextMenu();
            MenuItem removeItem = new MenuItem("Fjern vitne");

            removeItem.setOnAction(e -> {
                removeWitness(aRow.getItem());
            });

            rowMenu.getItems().add(removeItem);

            return aRow;
        });

        clmnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        clmnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        clmnContactInfo.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));

        tblWitness.setItems(observableWitnessList);
    }



    @FXML
    private void btnNewWitness() {
        try {
            WindowHandler windowHandler = new WindowHandler();
            windowHandler.openNewStageAndLockCurrent(getCurrentStage(), "/org/view/newWitness.fxml", "Nytt vitne");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    private void setWitnesses() {
        observableWitnessList = FXCollections.observableArrayList(currentAccidentStatement.getListOfWitnesses());
    }

    private void removeWitness(Witness witnessToRemove) {
        currentAccidentStatement.removeWitness(witnessToRemove);
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
        AccidentStatement newAccidentStatement = new AccidentStatementBuilder()
                .setAccidentType(txtAccidentType.getText())
                .setRegisteredTo(txtAccidentNr.getText())
                .setDateOfAccident(dateOfAccident.getValue().toString())
                .setAppraisalAmount(txtAppraisalAmount.getText())
                .setDispersedCompensation(txtDispersedCompensation.getText())
                .setAccidentDescription(txtAccidentDescription.getText())
                .build();

        newAccidentStatement.setListOfWitnesses(observableWitnessList);
        return newAccidentStatement;

    }

    public void displayExistingAccidentStatement() {
        txtAccidentType.setText(currentAccidentStatement.getAccidentType());
        txtAccidentNr.setText(String.valueOf(currentAccidentStatement.getAccidentNr()));
        dateOfAccident.setValue(currentAccidentStatement.getDateOfAccident());
        txtAppraisalAmount.setText(String.valueOf(currentAccidentStatement.getAppraisalAmount()));
        txtDispersedCompensation.setText(String.valueOf(currentAccidentStatement.getDispersedCompensation()));
        txtAccidentDescription.setText(currentAccidentStatement.getAccidentDescription());
    }

    public void updateAccidentStatement() throws BuilderInputException {
        assert currentAccidentStatement != null;
        checkForValidInput();
        // TODO: dette må vise error ved feil input.
        currentAccidentStatement.setAccidentDescription(txtAccidentDescription.getText());
        currentAccidentStatement.setAccidentType(txtAccidentType.getText());
        currentAccidentStatement.setAppraisalAmount(Double.parseDouble(txtAppraisalAmount.getText()));
        currentAccidentStatement.setDateOfAccident(dateOfAccident.getValue());
        currentAccidentStatement.setDispersedCompensation(Double.parseDouble(txtDispersedCompensation.getText()));
        currentAccidentStatement.setListOfWitnesses(observableWitnessList);
    }

    private void checkForValidInput() throws BuilderInputException {
        // Kaster exception hvis noen feltene ikke er gyldig input.
        new AccidentStatementBuilder()
                .setAccidentType(txtAccidentType.getText())
                .setRegisteredTo(txtAccidentNr.getText())
                .setDateOfAccident(dateOfAccident.getValue().toString())
                .setAppraisalAmount(txtAppraisalAmount.getText())
                .setDispersedCompensation(txtDispersedCompensation.getText())
                .setAccidentDescription(txtAccidentDescription.getText());
    }

    public void setAccidentStatement(AccidentStatement accidentStatement) {
        currentAccidentStatement = accidentStatement;
        setWitnesses();
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
