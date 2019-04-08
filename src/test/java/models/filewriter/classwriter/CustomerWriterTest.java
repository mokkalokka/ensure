package models.filewriter.classwriter;

import com.opencsv.bean.BeanField;
import models.customer.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerWriterTest {

    @Test
    public void generateHeader() {
        Customer customer1 = new Customer("Bolle", "Marsvinsen", "Trondheimsveien 7");

        CustomerWriter writer = new CustomerWriter();
        System.out.println(writer.generateHeader());

    }

    @Test
    public void write() {
    }
}