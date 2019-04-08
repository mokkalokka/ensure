package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import models.customer.Customer;

public class detailedCustomerController {

    //FX elementene
    @FXML
    private JFXTextField lblInsuranceNr;

    @FXML
    private JFXTextField  lblSurname;

    @FXML
    private JFXTextField  lblFirstName;

    @FXML
    private JFXTextField  lblCustomerSince;

    @FXML
    private JFXTextField  lblInvoiceAddress;

    @FXML
    private void btnBack() {
        //lukker vinduet
        Stage currentStage = getCurrentStage();
        currentStage.close();
    }

    @FXML
    private void btnSaveCustomer() {
        //TODO exceptions, sjekke om tom osv
        //Oppdaterer kunden med redigert data
        currentCustomer.setLastName(lblSurname.getText());
        currentCustomer.setFirstName(lblFirstName.getText());
        currentCustomer.setInvoiceAddress(lblInvoiceAddress.getText());
    }

    private Customer currentCustomer;

    //Finner nåværende stage ved hjelp av en fx:id for å kunne lukke dette vinduet
    private Stage getCurrentStage(){
        return (Stage) lblSurname.getScene().getWindow();
    }


    public void pickCustomer(Customer aCustomer) {
        //Lokal variabel for kunden som vises
        currentCustomer = aCustomer;

        //Setter textboksene
        lblInsuranceNr.setText(String.valueOf(aCustomer.getInsuranceNr()));
        lblSurname.setText(aCustomer.getLastName());
        lblFirstName.setText(aCustomer.getFirstName());
        lblCustomerSince.setText(aCustomer.getCustomerSince().toString());
        lblInvoiceAddress.setText(aCustomer.getInvoiceAddress());


    }

}
