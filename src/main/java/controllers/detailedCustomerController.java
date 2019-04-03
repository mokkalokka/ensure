package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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
    private void btnBack() {
        //lukker vinduet
        Stage currentStage = getCurrentStage();
        currentStage.close();
    }

    //Finner nåværende stage ved hjelp av en fx:id for å kunne lukke dette vinduet
    private Stage getCurrentStage(){
        return (Stage) lblSurname.getScene().getWindow();
    }


    public void pickCustomer(Customer aCustomer) {

        //Setter alle lablene
        lblInsuranceNr.setText(String.valueOf(aCustomer.getInsuranceNr()));
        lblSurname.setText(aCustomer.getLastName());
        lblFirstName.setText(aCustomer.getFirstName());
        lblCustomerSince.setText(aCustomer.getCustomerSince().toString());
        lblInvoiceAddress.setText(aCustomer.getInvoiceAddress());


    }

}
