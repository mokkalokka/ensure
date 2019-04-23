package models.fileReader;

import models.customer.Customer;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.exceptions.fileReaderExceptions.FileReaderInputException;

import java.io.IOException;
import java.util.List;

interface fileReaderTaskInterface {

     List<Customer> call() throws IOException, FileReaderInputException, ClassNotFoundException, InvalidCustomerException;
}
