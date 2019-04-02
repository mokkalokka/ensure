package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXTextField;
import models.customer.CustomerHandling;
import models.customer.ListOfCustomers;
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
        boolean addedSuccessfully;

        //Returnerer true om kunden blir lagt i listen
        addedSuccessfully = customerHandling.createNewCustomer(txtFirstName.getText(),txtLastName.getText(),
                txtInvoiceAddress.getText());
        if (addedSuccessfully){
            updateStatus("Kunden er lagt til i listen");
        }
        else{
            updateStatus("Ingen siffer tillatt i fornavn og etternavn");
        }
    }

    @FXML
    private void btnBackClicked(ActionEvent event){
        openTemporaryHomeScene(event);
    }


    private void openTemporaryHomeScene(ActionEvent event) {
        String pathToFXML = "/org/view/scene.fxml";
        OpenScene openScene = new OpenScene();
        openScene.openScene(event,pathToFXML);
    }

    @FXML
    private void updateStatus(String message){
        int customerCount = ListOfCustomers.getCustomerCount();
        lblStatus.setText(message +"\n\nAntall brukere i systemet: " + customerCount);
        //TODO: Dette kan muligens vises i en popup
    }

    public void initialize() {

    }
}

