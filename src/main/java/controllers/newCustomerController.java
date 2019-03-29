package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXTextField;
import models.customer.Customer;
import models.customer.CustomerHandling;
import models.customer.ListOfCustomers;

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
        customerHandling.createNewCustomer(txtFirstName.getText(),txtLastName.getText(),txtInvoiceAddress.getText());
    }

    public void initialize() {
        // TODO
    }
}
