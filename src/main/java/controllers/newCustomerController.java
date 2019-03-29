package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXTextField;

public class newCustomerController {


    /* Alle datafelt fra FXML vinduet */
    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtInvoiceAddress;

    @FXML
    private JFXButton btnAddCustomer;




    @FXML
    private void addCustomer(ActionEvent event) {

        System.out.println("Velkommen: " + txtFirstName.getText() );
    }


    public void initialize() {
        // TODO
    }
}
