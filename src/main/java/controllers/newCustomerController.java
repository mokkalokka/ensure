package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.builders.CustomerBuilder;
import models.customer.Customer;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.exceptions.customerExceptions.InvalidFirstNameException;
import models.exceptions.customerExceptions.InvalidLastNameException;
import models.gui.ErrorDialog;
import models.insurance.InsuranceCompany;

public class newCustomerController {

    private final InsuranceCompany INS_COMP = InsuranceCompany.getInstance();

    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private JFXTextField txtInvoiceAddress;
    @FXML
    private Label lblStatus;

    @FXML
    private void btnAddCustomer() {
        try {
            INS_COMP.addCustomer(getCurrentCustomer());
            updateStatus("Kunden er lagt til i listen");
            resetFieldColor();
        } catch (InvalidCustomerException e) {
            new ErrorDialog("Feil i inndata", e.getMessage()).show();
        }
    }

    private Customer getCurrentCustomer() throws InvalidCustomerException {
        return new CustomerBuilder()
                .setFirstName(txtFirstName.getText())
                .setLastName(txtLastName.getText())
                .setInvoiceAddress(txtInvoiceAddress.getText())
                .build();
    }

    private void setTextFieldFocusAndColor(TextField field){
        field.requestFocus();
        field.setStyle("-jfx-focus-color:rgb(250,105,102)");
    }

    private void resetFieldColor(){
        txtFirstName.setStyle("-jfx-focus-color:rgb(64,89,169)");
        txtLastName.setStyle("-jfx-focus-color:rgb(64,89,169)");
    }

    @FXML
    private void btnClose(){
        Stage currentStage = getCurrentStage();
        currentStage.close();
    }

    //Finner nåværende stage ved hjelp av en fx:id for å kunne lukke dette vinduet
    private Stage getCurrentStage(){
        return (Stage) lblStatus.getScene().getWindow();
    }

    @FXML
    private void updateStatus(String message){
        int customerCount = INS_COMP.getCustomerCount();
        lblStatus.setText(message + "\n\nAntall brukere i systemet: " + customerCount);
    }

    private void invalidInputAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feil formatering av kunde");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public void initialize() {
        // TODO
    }
}
