package models.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.insurance.Insurance;

import java.util.ArrayList;

public class CustomerList {
    //private static ArrayList<Customer> customerArrayList = new ArrayList<>();
    private static ObservableList<Customer> customerList = FXCollections.observableArrayList();

    public static void addCustomer(Customer customer) {
        CustomerList.customerList.add(customer);
    }

    public static int getCustomerCount(){
        return customerList.size();
    }

    //for testing //TODO hele klassen burde vere en singleton
    public static ObservableList<Customer> getCustomerArrayList() {
        return customerList;
    }


}
