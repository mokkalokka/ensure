package models.customer;

import java.util.ArrayList;

public class ListOfCustomers {
    private static ArrayList<Customer> customerArrayList = new ArrayList<>();

    public static void addCustomer(Customer customer) {
        ListOfCustomers.customerArrayList.add(customer);
    }

    public static int getCustomerCount(){
        return customerArrayList.size();
    }

    //for testing //TODO hele klassen burde vere en singleton
    public ArrayList<Customer> getCustomerArrayList() {
        return customerArrayList;
    }
}

