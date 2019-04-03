package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXTextField;
import models.customer.CustomerHandling;
import models.customer.CustomerList;
import models.gui.OpenScene;

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
    private void btnAddCustomerClicked(ActionEvent event) {
        CustomerHandling customerHandling = new CustomerHandling();
        String statusMessage = customerHandling.createNewCustomer(txtFirstName.getText(),txtLastName.getText(),
                txtInvoiceAddress.getText());
        updateStatus(statusMessage);
    }

    @FXML
    private void btnClose(){
        Platform.exit();
    }

    private void openTemporaryHomeScene(ActionEvent event) {
        String pathToFXML = "/org/view/customers.fxml";

        OpenScene openScene = new OpenScene();
        openScene.openScene(event,pathToFXML);
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
