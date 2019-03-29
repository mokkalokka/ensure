package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.customer.Customer;
import java.util.Date;


public class customersController {
    //Alle fx elementene
    @FXML
    private JFXTextField txtSearch;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private TableColumn<Customer, Integer> clmnInsuranceNr;

    @FXML
    private TableColumn<Customer, String> clmnSurname;

    @FXML
    private TableColumn<Customer, String> clmnFirstName;

    @FXML
    private TableColumn<Customer, Date>  clmnCustomerSince;

    @FXML
    private TableColumn<Customer, String> clmnInvoiceAddress; //TODO: finne en måte å fremstille arrayene for skademeldinger og forsikringer

    //TODO: Liste med alle kunder, denne trenger absolutt ikke være her
    private ObservableList<Customer> customerData = FXCollections.observableArrayList();

    //Dummy data
    public customersController() {
        customerData.add(new Customer("Ola", "Normann","Adressegata 12"));
        customerData.add(new Customer("Kari", "Halvorsen", "Hovedveien 19b"));
        customerData.add(new Customer("Per", "Sørensen", "Gokstadveien 5"));
    }
    public void initialize() {
        // TODO

        //ValueFactory på alle kolonnene
        clmnInsuranceNr.setCellValueFactory(new PropertyValueFactory<>("insuranceNr"));
        clmnSurname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        clmnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        clmnCustomerSince.setCellValueFactory(new PropertyValueFactory<>("customerSince"));
        clmnInvoiceAddress.setCellValueFactory(new PropertyValueFactory<>("invoiceAddress"));

        //Alle kunder til en filteret liste uten filter
        FilteredList<Customer> filteredData = new FilteredList<>(customerData, null);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(aCustomer -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (aCustomer.searchData().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Customer> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblCustomer.comparatorProperty());

        tblCustomer.setItems(sortedData);

    }
}
