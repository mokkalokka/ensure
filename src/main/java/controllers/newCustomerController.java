package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXTextField;
import models.customer.CustomerHandling;
import models.customer.ListOfCustomers;
import models.gui.OpenScene;
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
            Parent FXML = FXMLLoader.load(getClass().getResource("/org/view/scene.fxml"));
            OpenScene openScene = new OpenScene();
            openScene.openScene(event,FXML);
        }
        catch (IOException e){
            System.out.println("FXML file not found!");
        }
    }

    @FXML
    private void updateStatus(String message){
        int customerCount = ListOfCustomers.getCustomerCount();
        lblStatus.setText(message +"\n\nAntall brukere i systemet: " + customerCount);
    }

    public void initialize() {
        // TODO
    }
}
