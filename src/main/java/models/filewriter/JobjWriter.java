package models.filewriter;

import models.customer.Customer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class JobjWriter {

    public void writeCustomersData(List<Customer> customerList, String path) throws IOException {
        String filepath = path + ".jobj"; // kanskje .jobj skal være direkte i input

        FileOutputStream fos = new FileOutputStream(filepath);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(customerList);
    }
}
