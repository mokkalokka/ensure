package models.filewriter;

import models.customer.Customer;

import java.util.List;

public abstract class FileWriterStrategy {
    protected final String path;
    protected final List<Customer> customerList;

    protected FileWriterStrategy(String path, List<Customer> customerList) {
        this.path = path;
        this.customerList = customerList;
    }

    public abstract void writeFile() throws Exception;

}
