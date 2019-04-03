package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXTextField;
import javafx.stage.Stage;
import models.customer.CustomerHandling;
import models.customer.CustomerList;

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
        String statusMessage = customerHandling.createNewCustomer(txtFirstName.getText(),txtLastName.getText(),
                txtInvoiceAddress.getText());
        updateStatus(statusMessage);
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
        lblStatus.setText(message +"\n\nAntall brukere i systemet: " + customerCount);
    }

    public void initialize() {
        // TODO
    }
}
