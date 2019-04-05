package models.customer;

import javafx.collections.ObservableList;
import models.exceptions.customerExceptions.*;

public class CustomerHandling {


    // Tror denne metode heller burde returnere true/false slik at man kan handle alt som skal vises i controller.
    public void createNewCustomer(String firstName, String lastName, String invoiceAddress) throws InvalidCustomerException {
        if(stringContainsNumbers(firstName)){
            throw new InvalidFirstNameException();
        }
        else if(stringContainsNumbers(lastName)){
            throw new InvalidLastNameException();
        }
        else if (firstName.isEmpty() || lastName.isEmpty() || invoiceAddress.isEmpty()){
            throw new EmptyFieldsException();
        }

        //Sjekker om kunden ligger i listen allerede
        else if (duplicateCustomer(firstName,lastName,invoiceAddress)){
            throw new DuplicateCustomerException();
        }

        //Dersom ingen feil har oppstått opprett kunden og legg kunden i lista
        else{
        Customer customer = new Customer(firstName,lastName,invoiceAddress);
        addToCustomersList(customer);
        }
    }

    //Legger til kunden i CustomerList
    private void addToCustomersList(Customer customer) {
        CustomerList.addCustomer(customer);
    }


    //Sjekker om en String inneholder nummer
    public boolean stringContainsNumbers(String string){
        return string.matches(".*\\d.*");
    }

    //Sjekker om kunden ligger i systemet allerede
    public boolean duplicateCustomer(String firstName, String lastName, String invoiceAddress){
        ObservableList<Customer> customerObservableList = CustomerList.getCustomerArrayList();
        for (Customer customer : customerObservableList){
            if(customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName) &&
                    customer.getInvoiceAddress().equals(invoiceAddress)){
                return true;
            }
        }
        return false;
    }


}
