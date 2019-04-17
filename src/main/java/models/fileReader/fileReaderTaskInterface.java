package models.fileReader;

import models.customer.Customer;
import models.exceptions.customerExceptions.InvalidCustomerException;

import java.io.IOException;
import java.util.List;

interface fileReaderTaskInterface {

     List<Customer> call() throws IOException, ClassNotFoundException, InvalidCustomerException;
}
