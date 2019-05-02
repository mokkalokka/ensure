package controllers.accidentStatement;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import controllers.DetailedCustomerController;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.accidentStatement.AccidentStatement;
import models.accidentStatement.Witness;
import models.builders.AccidentStatementBuilder;
import models.customer.Customer;
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
    private DetailedCustomerController parentController;
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
    void initializeTable() {

        tblWitness.setRowFactory(tableView -> {

            //Tom rad
            TableRow<Witness> aRow = new TableRow<>();

            ContextMenu rowMenu = new ContextMenu();
            MenuItem removeItem = new MenuItem("Fjern vitne");

            removeItem.setOnAction(e -> {
                removeWitness(aRow.getItem());
            });

            rowMenu.getItems().add(removeItem);

            //Gjør så den ikke kjøres når rad er tom
            aRow.contextMenuProperty().bind(
                    Bindings.when(Bindings.isNotNull(aRow.itemProperty()))
                            .then(rowMenu)
                            .otherwise((ContextMenu) null));

            return aRow;
        });

        clmnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        clmnSurname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        clmnContactInfo.setCellValueFactory(new PropertyValueFactory<>("contactInformation"));

        tblWitness.setItems(observableWitnessList);
    }

    @FXML
    private void btnNewWitness() {
        try {
            openNewWitnessWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openNewWitnessWindow() throws IOException {
        String stageTitle = "Nytt vitne";
        String path = "/org/view/newWitness.fxml";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();
        NewWitnessController controller = loader.getController();
        controller.setCurentCustomer(currentCustomer);
        controller.setParentController(this);

        WindowHandler windowHandler = new WindowHandler();
        windowHandler.openNewStageAndLockCurrent(getCurrentStage(), root, stageTitle);
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
        } catch (InvalidCustomerException | BuilderInputException e) {
            new ErrorDialog("Feil ved lagring", e.getMessage()).show();
        }
        parentController.refreshTables();
    }

    private void removeWitness(Witness witnessToRemove) {
        observableWitnessList.remove(witnessToRemove);
    }

    public void addWitness(Witness newWitness) {
        observableWitnessList.add(newWitness);
    }

    public AccidentStatement getCurrentAccidentStatement() {
        return currentAccidentStatement;
    }

    public Customer getCustomer() {
        return currentCustomer;
    }

    public void setCustomer(Customer customer) {
        currentCustomer = customer;

    }

    public void displayNewAccidentStatement() {
        txtAccidentNr.setText(String.valueOf(currentCustomer.getInsuranceNr()));
    }

    void initNewWitnessList() {
        observableWitnessList = FXCollections.observableArrayList(new ArrayList());
    }

    public AccidentStatement getNewAccidentStatement() throws BuilderInputException {
        return new AccidentStatementBuilder()
                .setAccidentType(txtAccidentType.getText())
                .setRegisteredTo(txtAccidentNr.getText())
                .setDateOfAccident(dateOfAccident.getValue().toString())
                .setAppraisalAmount(txtAppraisalAmount.getText())
                .setDispersedCompensation(txtDispersedCompensation.getText())
                .setAccidentDescription(txtAccidentDescription.getText())
                .setListOfWitnesses(observableWitnessList)
                .build();
    }

    void displayExistingAccidentStatement() {
        txtAccidentType.setText(currentAccidentStatement.getAccidentType());
        txtAccidentNr.setText(String.valueOf(currentAccidentStatement.getAccidentNr()));
        dateOfAccident.setValue(currentAccidentStatement.getDateOfAccident());
        txtAppraisalAmount.setText(String.valueOf(currentAccidentStatement.getAppraisalAmount()));
        txtDispersedCompensation.setText(String.valueOf(currentAccidentStatement.getDispersedCompensation()));
        txtAccidentDescription.setText(currentAccidentStatement.getAccidentDescription());
        observableWitnessList = FXCollections.observableArrayList(currentAccidentStatement.getListOfWitnesses());
    }

    void updateAccidentStatement() throws BuilderInputException {
        assert currentAccidentStatement != null;
        checkForValidInput();
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
                .setAccidentDescription(txtAccidentDescription.getText())
                .setListOfWitnesses(observableWitnessList);
    }

    public void setAccidentStatement(AccidentStatement accidentStatement) {
        currentAccidentStatement = accidentStatement;
    }

    public void setState(AccidentStatementState state) {
        this.state = state;
    }

    public void setParentController(DetailedCustomerController parentController) {
        this.parentController = parentController;
    }

    public void load() {
        state.setFields(this);
    }

    private Stage getCurrentStage() {
        return (Stage) txtAccidentType.getScene().getWindow();
    }
}
