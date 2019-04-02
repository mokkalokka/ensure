package models.fileReader;

import models.customer.Customer;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class OpenCSVReaderTest {

    @Test
    public void readObject() {
        OpenCSVReader reader = new OpenCSVReader();
        try {
            ArrayList<Customer> customerList;
            customerList =
                    (ArrayList<Customer>) reader.readObject("./src/test/resources/csvObjectWriterTest.csv");
            customerList.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}