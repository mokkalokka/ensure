package models.fileReader;

import models.customer.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class SerializedCustomerReaderTest {

    @Test
    public void readCustomers() {
        List<Customer> customers;
        SerializedCustomerReader customerReader = new SerializedCustomerReader();
        String path = "./src/test/resources/writeCustomersTest.jobj";

        try {
            customers = customerReader.readCustomers(path);
            System.out.println(customers);
        } catch (Exception e) {
            System.err.println(e.getClass() + ", Error reading from file: " + e.getMessage());
        }

    }
}