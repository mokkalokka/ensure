package controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.customer.Customer;
import models.gui.OpenScene;

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
        OpenScene openScene = new OpenScene();
        openScene.openScene(event, "/org/view/detailedCustomer.fxml");
    }

    public void initialize() {
    }

    public void showCustomer(Customer aCustomer) {
        //TODO formatere strenger istedenfor "" +
        lblInsuranceNr.setText("" + aCustomer.getInsuranceNr());
        lblSurname.setText(aCustomer.getLastName());
        lblFirstName.setText(aCustomer.getFirstName());
        lblCustomerSince.setText("" + aCustomer.getCustomerSince());
        lblInvoiceAddress.setText(aCustomer.getInvoiceAddress());
    }

}
