package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.customer.Customer;
import models.gui.OpenScene;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class detailedCustomerController {

    //FX elementene
    @FXML
    private Label lblInsuranceNr;

    @FXML
    private Label lblSurname;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblCustomerSince;

    @FXML
    private Label lblInvoiceAddress;

    @FXML
    private void btnBack(ActionEvent event) {
        //Bytter ut vind
        OpenScene openScene = new OpenScene();
        openScene.openScene(event, "/org/view/customers.fxml");
    }



    public void pickCustomer(Customer aCustomer) {
        //Datoformat uten klokkeslett
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        //Setter alle lablene
        lblInsuranceNr.setText(String.valueOf(aCustomer.getInsuranceNr()));
        lblSurname.setText(aCustomer.getLastName());
        lblFirstName.setText(aCustomer.getFirstName());
        lblCustomerSince.setText(dateFormat.format(aCustomer.getCustomerSince()));
        lblInvoiceAddress.setText(aCustomer.getInvoiceAddress());


    }

}
