package models.fileReader;

import models.customer.Customer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class SerializedCustomerReader implements fileReader {

    @Override
    public List<Customer> readCustomers(String path) throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(path);
        ObjectInputStream oin = new ObjectInputStream(fin);
        Object loadedCustomers = oin.readObject();

        // TODO: Lag custom exceptions for InvalidCustomerFormat som denne metoden skal kaste.

        return (ArrayList<Customer>) loadedCustomers;

    }
}
