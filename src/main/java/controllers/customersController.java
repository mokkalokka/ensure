package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.customer.Customer;
import models.customer.CustomerList;
import models.gui.OpenScene;

import java.io.IOException;
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
    private TableColumn<Customer, String> clmnInvoiceAddress;

    //Liste linket til tablet fra lista med alle customers
    private ObservableList<Customer> observableCustomerList = FXCollections.observableArrayList(CustomerList.getCustomerArrayList());


    @FXML
    private void btnRegister(ActionEvent event) {
        OpenScene openScene = new OpenScene();
        openScene.openScene(event, "/org/view/newCustomer.fxml");
    }

    
    public void doubleClicked(Customer clickedCustomer) {
        //TODO maa erstatte vindu, ikke aapne nytt
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/view/detailedCustomer.fxml"));
            Parent root = loader.load();

            detailedCustomerController controller = loader.getController();

            controller.pickCustomer(clickedCustomer);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("FXML file not found!");
        }
    }



    public void initialize() {
        //Klikking paa kunder

        tblCustomer.setRowFactory(tableView -> {
            TableRow<Customer> aRow = new TableRow<>();
            aRow.setOnMouseClicked(mouseEvent -> {
                if ((! aRow.isEmpty() && mouseEvent.getClickCount() == 2)) {

                    doubleClicked(aRow.getItem());
                }
            });
            return aRow;
        });



        //Soek i kunder

        //ValueFactory på alle kolonnene
        clmnInsuranceNr.setCellValueFactory(new PropertyValueFactory<>("insuranceNr"));
        clmnSurname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        clmnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        clmnCustomerSince.setCellValueFactory(new PropertyValueFactory<>("customerSince"));
        clmnInvoiceAddress.setCellValueFactory(new PropertyValueFactory<>("invoiceAddress"));

        //Alle kunder til en filteret liste uten filter
        FilteredList<Customer> filteredData = new FilteredList<>(observableCustomerList, null);

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
