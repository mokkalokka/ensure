package models.filewriter;

import models.customer.Customer;

import java.util.List;

public class SerializedObjectWriter extends FileWriterStrategy {

    protected SerializedObjectWriter(String path, List<Customer> customerList) {
        super(path, customerList);
    }

    @Override
    public void writeFile() throws Exception {

    }
}
