package models.fileReader;

import models.customer.Customer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class JobjReader {

    public List<Customer> readCustomerList(String path) throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(path);
        ObjectInputStream oin = new ObjectInputStream(fin);
        Object loadedObject = oin.readObject();


        ArrayList<Customer> customerList = (ArrayList<Customer>) loadedObject;

        return customerList;


    }
}
