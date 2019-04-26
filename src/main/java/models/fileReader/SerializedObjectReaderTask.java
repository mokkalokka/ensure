package models.fileReader;

import javafx.concurrent.Task;
import models.customer.Customer;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.exceptions.fileExceptions.WrongSerialVersionIDException;

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
    public List<Customer> call() throws Exception{
        try{
        FileInputStream fin = new FileInputStream(path);
        ObjectInputStream oin = new ObjectInputStream(fin);
        List<Customer> loadedCustomers = (List<Customer>) oin.readObject();

        return loadedCustomers;
        }
        catch (IOException e){
            throw new WrongSerialVersionIDException();
        }
    }

    @Override
    protected void succeeded() {
        updateProgress(100,100);
        //super.succeeded();
    }
}
