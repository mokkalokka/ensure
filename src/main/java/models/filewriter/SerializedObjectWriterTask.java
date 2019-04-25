package models.filewriter;

import javafx.concurrent.Task;
import models.customer.CustomerList;
import models.exceptions.fileExceptions.NoCustomersFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializedObjectWriterTask extends Task implements fileWriterTaskInterface{
    private final Object customersToFile;
    private final String path;

    public SerializedObjectWriterTask(Object customersToFile, String path) {
        this.customersToFile = customersToFile;
        this.path = path;
    }


    @Override
    public Void call() throws Exception {
        if(CustomerList.getCustomerCount() == 0){
            throw new NoCustomersFoundException();
        }

        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(customersToFile);
        return null;
    }

    @Override
    protected void succeeded() {
        //super.succeeded();
        updateProgress(100,100);
    }
}
