package models.filewriter;

import models.customer.Customer;

import java.io.IOException;
import java.util.List;

interface fileWriter {

    void writeCustomers(List<Customer> customers, String path) throws IOException;
}
