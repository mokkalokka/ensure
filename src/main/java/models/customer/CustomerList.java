package models.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.exceptions.customerExceptions.DuplicateCustomerException;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.insurance.Insurance;

import java.util.ArrayList;
import java.util.List;

public class CustomerList {
    //private static ArrayList<Customer> customerArrayList = new ArrayList<>();
    private static ObservableList<Customer> customerList = FXCollections.observableArrayList();

    public static void addCustomer(Customer newCustomer) throws DuplicateCustomerException {
        if (duplicateCustomer(newCustomer)) {
            throw new DuplicateCustomerException();
        }
        CustomerList.customerList.add(newCustomer);
    }

    public static int getCustomerCount(){
        return customerList.size();
    }

    //for testing //TODO hele klassen burde vere en singleton
    public static ObservableList<Customer> getCustomerList() {
        return customerList;
    }

    public static void initializeNewList(List<Customer> listOfCustomers) {
        customerList.clear();
        customerList.addAll(listOfCustomers);
    }

    private static boolean duplicateCustomer(Customer source){
        ObservableList<Customer> customerObservableList = getCustomerList();
        for (Customer customer : customerObservableList){
            if(customer.getFirstName().equals(source.getFirstName()) && customer.getLastName().equals(source.getLastName()) &&
                    customer.getInvoiceAddress().equals(source.getInvoiceAddress())){
                return true;
            }
        }
        return false;
    }

    public static void addInsuranceToCustomer(Insurance insurance) throws NoSuchCustomerException {
        for (Customer customer : getCustomerList()) {
            if (customer.getInsuranceNr() == insurance.getRegisteredTo()) {
                customer.addInsurance(insurance);
                return;
            }
        }
        throw (new NoSuchCustomerException());
    }
}
