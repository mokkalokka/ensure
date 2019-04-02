package models.customer;

public class CustomerHandling {

    public boolean createNewCustomer(String firstName, String lastName, String invoiceAddress){
        if(!stringContainsNumbers(firstName) && !stringContainsNumbers(lastName)){
            Customer customer = new Customer(firstName,lastName,invoiceAddress);
            ListOfCustomers.addCustomer(customer);
            return true;
        }
        else{
            //TODO:Throw new exception (Lag denne)
            //TODO:Håndtere invoice Address
            return false;
        }
    }

    //Sjekker om en String inneholder nummer
    public boolean stringContainsNumbers(String string){
        return string.matches(".*\\d.*");
    }
}

