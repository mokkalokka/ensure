package models.fileReader;

import models.customer.Customer;

import java.io.IOException;
import java.util.List;

interface fileReader {

     List<Customer> readObject(String path) throws IOException, ClassNotFoundException;
}
