package models.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CustomerList {
    //private static ArrayList<Customer> customerArrayList = new ArrayList<>();
    private static ObservableList<Customer> customerArrayList = FXCollections.observableArrayList();

    public static void addCustomer(Customer customer) {
        CustomerList.customerArrayList.add(customer);
    }

    public static int getCustomerCount(){
        return customerArrayList.size();
    }

    //for testing //TODO hele klassen burde vere en singleton
    public static ObservableList<Customer> getCustomerArrayList() {
        return customerArrayList;
    }
}
