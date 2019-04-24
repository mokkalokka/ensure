package models.fileReader;

import models.customer.Customer;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.exceptions.fileExceptions.InvalidLineLengthException;

import java.io.IOException;
import java.util.List;

interface fileReaderTaskInterface {

     List<Customer> call() throws IOException, InvalidLineLengthException, ClassNotFoundException, InvalidCustomerException;
}
