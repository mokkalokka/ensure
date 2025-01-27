package models.threading;

import javafx.concurrent.Task;
import models.customer.Customer;
import models.exceptions.fileExceptions.UnsuportedFileExtensionException;
import models.filewriter.CSVWriter;
import models.filewriter.SerializedObjectWriter;

import java.util.List;

public class FileWriterTask extends Task {
    private final String path;
    private final String fileExtension;
    private final List<Customer> customerList;


    public FileWriterTask(String path, String fileExtension, List<Customer> customerList) {
        this.path = path;
        this.customerList = customerList;
        this.fileExtension = fileExtension;
    }

    @Override
    protected Void call() throws Exception {
        if (fileExtension.equals("csv")) {
            CSVWriter csvWriter = new CSVWriter(path, customerList);
            csvWriter.writeFile();

        } else if (fileExtension.equals("jobj")) {
            SerializedObjectWriter serializedObjectWriter = new SerializedObjectWriter(path, customerList);
            serializedObjectWriter.writeFile();
        }

        //Dersom filtypen ikke er csv eller jobj kastes exception
        else {
            throw new UnsuportedFileExtensionException();
        }

        return null;
    }

    @Override
    protected void succeeded() {
        //Progressbar skal være 100% når oppgaven er ferdig
        updateProgress(100, 100);
    }
}
