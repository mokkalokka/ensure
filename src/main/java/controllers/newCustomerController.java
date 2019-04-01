package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXTextField;
import javafx.stage.Stage;
import models.customer.Customer;
import models.customer.CustomerHandling;
import models.customer.ListOfCustomers;

import java.io.IOException;

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
    private void btnBackClicked(ActionEvent event){
        openTemporaryHomeScene(event);
    }


    private void openTemporaryHomeScene(ActionEvent event){
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("/org/view/scene.fxml"));
            Scene root = new Scene(parent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(root);
            window.show();
        }
        catch (IOException e){
            System.out.println("FXML file not found!");
        }
    }

    @FXML
    private void updateStatus(String message){
        int customerCount = ListOfCustomers.getCustomerCount();
        lblStatus.setText(message +"\nAntall brukere i systemet: " + customerCount);
    }

    public void initialize() {
        // TODO
    }
}
