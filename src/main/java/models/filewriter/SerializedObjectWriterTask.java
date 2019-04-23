package models.filewriter;

import javafx.concurrent.Task;

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
        String filepath = path + ".jobj"; // kanskje .jobj skal være direkte i input

        FileOutputStream fos = new FileOutputStream(filepath);
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
