package models.customer;

import models.exceptions.customerExceptions.EmptyFieldsException;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.exceptions.customerExceptions.InvalidFirstNameException;
import models.exceptions.customerExceptions.InvalidLastNameException;

public class CustomerHandling {


    // Tror denne metode heller burde returnere true/false slik at man kan handle alt som skal vises i controller.
    public void createNewCustomer(String firstName, String lastName, String invoiceAddress) throws InvalidCustomerException {
        if(stringContainsNumbers(firstName)){
            throw new InvalidFirstNameException();
        }
        else if(stringContainsNumbers(lastName)){
            throw new InvalidLastNameException();
        }
        else if (firstName.equals("") || lastName.equals("") || invoiceAddress.equals("")){
            throw new EmptyFieldsException();
        }
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


}
