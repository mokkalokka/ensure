package models.threading;

import javafx.concurrent.Task;
import models.exceptions.fileExceptions.UnsuportedFileExtensionException;
import models.fileReader.CSVReader;
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
            CSVReader csvReader = new CSVReader(path);
            //Returnerer en ArrayList med kunder fra fil
            return csvReader.readFile();

        } else if (fileExtension.equals("jobj")) {
            SerializedObjectReader serializedObjectReader = new SerializedObjectReader(path);
            //Returnerer en ArrayList med kunder fra fil
            return serializedObjectReader.readFile();
        }

        //Dersom filtypen ikke er csv eller jobj kastes exception
        else {
            throw new UnsuportedFileExtensionException();
        }
    }

    @Override
    protected void succeeded() {
        //Progressbar skal være 100% når oppgaven er ferdig
        updateProgress(100, 100);
    }
}
