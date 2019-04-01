package models.filewriter;

import models.customer.Customer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ObjectCsvWriterTest {
    private ArrayList<Customer> customers = new ArrayList<>();
    private Customer customer1, customer2, customer3;

    @Before
    public void setUp() {
        Customer customer1 = new Customer("Bolle", "Marsvinsen", "Trondheimsveien 7");
        Customer customer2 = new Customer("Tore D.", "Hurt", "Ullevålsveien 2a");
        customers.add(customer1);
        customers.add(customer2);
    }

    @Test
    public void writeObject() {
        String path = "./src/test/resources/csvObjectWriterTest.csv";

        ObjectCsvWriter csvWriter = new ObjectCsvWriter();
        try {
            csvWriter.writeObject(customers, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}