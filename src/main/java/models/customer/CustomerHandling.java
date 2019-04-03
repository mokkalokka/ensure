package models.customer;

public class CustomerHandling {


    // Tror denne metode heller burde returnere true/false slik at man kan handle alt som skal vises i controller.
    public String createNewCustomer(String firstName, String lastName, String invoiceAddress){
        if(!stringContainsNumbers(firstName) && !stringContainsNumbers(lastName)){
        Customer customer = new Customer(firstName,lastName,invoiceAddress);
        addToCustomersList(customer);
        return "Kunden er lagt til i listen";
        }

        else{
            //TODO:Throw new exception (Lag denne)
            //TODO:Håndtere invoice Address
            return "Feil: navn og etternavn kan ikke \ninneholde noen siffer";
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
