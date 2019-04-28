package models.filewriter;

import models.customer.Customer;
import models.customer.CustomerList;
import models.exceptions.fileExceptions.NoCustomersFoundException;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SerializedObjectWriter extends FileWriterStrategy {

    public SerializedObjectWriter(String path, List<Customer> customerList) {
        super(path, customerList);
    }

    @Override
    public void writeFile() throws Exception {
        if(CustomerList.getCustomerCount() == 0){
            throw new NoCustomersFoundException();
        }

        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(customerList);

    }
}
