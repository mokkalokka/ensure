package models.threading;

import javafx.concurrent.Task;
import models.exceptions.fileExceptions.UnsuportedFileExtensionException;
import models.fileReader.CsvReader;
import models.fileReader.SerializedObjectReader;

public class FileReaderTask extends Task {
    private final String path;
    private final String fileExtension;

    public FileReaderTask(String path, String fileExtension) {
        this.path = path;
        this.fileExtension = fileExtension;
    }

    @Override
    protected Object call() throws Exception {

        if (fileExtension.equals("csv")) {
            CsvReader csvReader = new CsvReader(path);
            return csvReader.readFile();

        } else if (fileExtension.equals("jobj")) {
            SerializedObjectReader serializedObjectReader = new SerializedObjectReader(path);
            return serializedObjectReader.readFile();
        } else {
            throw new UnsuportedFileExtensionException();
        }
    }

    @Override
    protected void succeeded() {
        //Progressbar skal være 100% når oppgaven er ferdig
        updateProgress(100, 100);
    }
}
