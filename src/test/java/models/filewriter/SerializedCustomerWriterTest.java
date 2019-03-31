package models.filewriter;

import models.customer.Customer;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SerializedCustomerWriterTest {

    @Test
    public void writeCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer("Bolle", "Marsvinsen");
        Customer customer2 = new Customer("Tore D.", "Hurt");
        customers.add(customer1);
        customers.add(customer2);

        String path = "/Users/eskilgaarehostad/IdeaProjects/DATA1600/ensure/src/test/resources/writeCustomersTest.jobj";

        SerializedCustomerWriter customerWriter = new SerializedCustomerWriter();
        try {
            customerWriter.writeCustomers(customers, path);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getCause());
        }

    }
}