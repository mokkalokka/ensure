package models.customer;

public class CustomerHandling {

    public void createNewCustomer(String firstName, String lastName, String invoiceAddress){
        Customer customer = new Customer(firstName,lastName,invoiceAddress);
        addToCustomersList(customer);
    }

    private void addToCustomersList(Customer customer) {
        ListOfCustomers.addCustomer(customer);
    }


}
