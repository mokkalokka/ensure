package models.fileReader;

import models.customer.Customer;

import java.util.List;

public abstract class FileReaderStrategy {
    protected final String path;

    protected FileReaderStrategy(String path) {
        this.path = path;
    }

    public abstract List<Customer> readFile() throws Exception;

}
