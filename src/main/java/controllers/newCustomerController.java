package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.customer.CustomerHandling;
import models.customer.CustomerList;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.exceptions.customerExceptions.InvalidFirstNameException;
import models.exceptions.customerExceptions.InvalidLastNameException;

public class newCustomerController {

    /* Alle datafelt fra FXML vinduet */
    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtInvoiceAddress;

    @FXML
    private Label lblStatus;

    @FXML
    private void btnAddCustomerClicked() {
        CustomerHandling customerHandling = new CustomerHandling();

        try {
            customerHandling.createNewCustomer(txtFirstName.getText(), txtLastName.getText(),
                    txtInvoiceAddress.getText());
            updateStatus("Kunden er lagt til i listen");
        }
        catch (InvalidFirstNameException e){
            setTextFieldFocusAndColor(txtFirstName);
            invalidInputAlert(e.getMessage());
        }
        catch (InvalidLastNameException e){
            setTextFieldFocusAndColor(txtLastName);
            invalidInputAlert(e.getMessage());
        }
        catch (InvalidCustomerException e){
            invalidInputAlert(e.getMessage());

        }
    }

    private void setTextFieldFocusAndColor(TextField field){
        field.requestFocus();
        field.setStyle("-jfx-focus-color:red");
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
        int customerCount = CustomerList.getCustomerCount();
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
