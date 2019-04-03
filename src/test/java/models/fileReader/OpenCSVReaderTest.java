package models.fileReader;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
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
                    (ArrayList<Customer>) reader.readObject("./src/test/resources/csvdata.csv");
            System.out.println("------Read from .csv --------");
            System.out.println("Number of customers read from .csv file: " + customerList.size());
            customerList.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}