package models.fileReader;

import javafx.concurrent.Task;
import models.customer.Customer;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class SerializedObjectReaderTask extends Task implements fileReaderTaskInterface {
    private final String path;

    public SerializedObjectReaderTask(String path) {
        this.path = path;
    }

    @Override
    public List<Customer> call() throws IOException, ClassNotFoundException, InvalidCustomerException {
        FileInputStream fin = new FileInputStream(path);
        ObjectInputStream oin = new ObjectInputStream(fin);
        List<Customer> loadedCustomers = (List<Customer>) oin.readObject();

        // TODO: Lag custom exceptions for InvalidCustomerFormat som denne metoden skal kaste.

        return loadedCustomers;
    }

    @Override
    protected void succeeded() {
        updateProgress(100,100);
        //super.succeeded();
    }
}
