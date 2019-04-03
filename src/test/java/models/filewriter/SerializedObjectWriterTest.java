package models.filewriter;

import models.customer.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class SerializedObjectWriterTest {
    ArrayList<Customer> customers = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        Customer customer1 = new Customer("Bolle", "Marsvinsen", "Trondheimsveien 7");
        Customer customer2 = new Customer("Tore D.", "Hurt", "Ullevålsveien 2a");
        customers.add(customer1);
        customers.add(customer2);
    }

    @After
    public void tearDown() throws Exception {
        // TODO: delete generated files here
    }

    @Test
    public void writeCustomers() {
        String path = "./src/test/resources/writeCustomersTest";

        SerializedObjectWriter writer = new SerializedObjectWriter();
        try {
            writer.writeObject(customers, path);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not write list of customers: " + e.getCause());
        }

    }
}