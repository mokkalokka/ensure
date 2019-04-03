package models.customer;

import java.util.ArrayList;

public class CustomerList {
    private static ArrayList<Customer> customerArrayList = new ArrayList<>();

    public static void addCustomer(Customer customer) {
        CustomerList.customerArrayList.add(customer);
    }

    public static int getCustomerCount(){
        return customerArrayList.size();
    }

    //for testing //TODO hele klassen burde vere en singleton
    public static ArrayList<Customer> getCustomerArrayList() {
        return customerArrayList;
    }
}
