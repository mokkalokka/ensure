package models.fileReader;

import models.customer.Customer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class SerializedObjectReader implements fileReader {

    @Override
    public List<Customer> readFile(String path) throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(path);
        ObjectInputStream oin = new ObjectInputStream(fin);
        List<Customer> loadedCustomers = (List<Customer>) oin.readObject();

        // TODO: Lag custom exceptions for InvalidCustomerFormat som denne metoden skal kaste.

        return loadedCustomers;

    }


}
