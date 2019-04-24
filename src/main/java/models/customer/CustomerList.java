package models.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.accidentStatement.AccidentStatement;
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

    //Kanskje feilhåndtering
    public static void removeCustomer(Customer customerToRemove) {
        CustomerList.customerList.remove(customerToRemove);
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

    public static void overwriteInsuranceInCustomer(Insurance newInsurance) throws NoSuchCustomerException {
        //Indeksen til forsikringen som skal overskrives
        int insuranceID = newInsurance.getInsuranceID();

        //For alle customers sjekk hvilken den nye forsikringen hører til
        for (Customer customer : getCustomerList()) {
            if (insuranceBelongsToCustomer(newInsurance, customer)) {
                for (int i = 0; i < customer.getListOfInsurances().size(); i++) {
                    Insurance oldInsurance = customer.getListOfInsurances().get(i);
                    if (oldInsurance.getInsuranceID() == insuranceID) {
                        customer.overwriteInsurance(i, newInsurance);
                        return;
                    }
                }
            }
        }
        throw (new NoSuchCustomerException());
    }

    public static void addAccidentStatementToCustomer(AccidentStatement accidentStatement) throws NoSuchCustomerException {
        for (Customer customer : getCustomerList()) {
            if (customer.getInsuranceNr() == accidentStatement.getRegisteredTo()) {
                customer.addAccidentStatement(accidentStatement);
                return;
            }
        }
        throw (new NoSuchCustomerException());
    }

    public static void overwriteAccidentStatementInCustomer(AccidentStatement newAccidentStatement) throws NoSuchCustomerException {
        //Indeksen til skademeldingen som skal overskrives
        int accidentStatementID = newAccidentStatement.getAccidentNr();

        for (Customer customer : getCustomerList()) {
            if (accidentStatementBelongsToCustomer(newAccidentStatement, customer)) {
                for (int i = 0; i < customer.getListOfAccidentStatements().size(); i++) {
                    AccidentStatement oldAccidnetStatement = customer.getListOfAccidentStatements().get(i);
                    if (oldAccidnetStatement.getAccidentNr() == accidentStatementID) {
                        customer.overwriteAccidentStatement(i, newAccidentStatement);
                        return;
                    }
                }
            }
        }
        throw (new NoSuchCustomerException());
    }

    private static boolean insuranceBelongsToCustomer(Insurance insurance, Customer customer) {
        return insurance.getRegisteredTo() == customer.getInsuranceNr();
    }

    private static boolean accidentStatementBelongsToCustomer(AccidentStatement accidentStatement, Customer customer) {
        return accidentStatement.getRegisteredTo() == customer.getInsuranceNr();
    }

}
