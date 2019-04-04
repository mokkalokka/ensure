package models.customer;

import models.exceptions.InvalidCustomerException;
import models.exceptions.InvalidFirstNameException;

public class CustomerHandling {


    // Tror denne metode heller burde returnere true/false slik at man kan handle alt som skal vises i controller.
    public void createNewCustomer(String firstName, String lastName, String invoiceAddress) throws InvalidFirstNameException {
        if(!stringContainsNumbers(firstName) && !stringContainsNumbers(lastName)){
        Customer customer = new Customer(firstName,lastName,invoiceAddress);
        addToCustomersList(customer);
        }

        else{
            //TODO:Throw new exception (Lag denne)
            //TODO:Håndtere invoice Address
            throw new InvalidFirstNameException();
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
