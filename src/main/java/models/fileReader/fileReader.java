package models.fileReader;

import models.customer.Customer;
import models.exceptions.customerExceptions.InvalidCustomerException;

import java.io.IOException;
import java.util.List;

interface fileReader {

     List<Customer> readFile(String path) throws IOException, ClassNotFoundException, InvalidCustomerException;
}
