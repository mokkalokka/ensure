package models.fileHandler;

import javafx.concurrent.Task;
import javafx.stage.FileChooser;
import models.company.InsuranceCompany;
import models.customer.Customer;
import models.threading.FileReaderTask;
import models.threading.FileWriterTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileHandler {
    private final InsuranceCompany INS_COMP = InsuranceCompany.getInstance();

    public String findFileExtension(String path) {
        //Splitter opp strengen med path og returnerer delen som er etter det siste punktumet,
        //dette vil da være filtypen (jobj eller csv)
        String[] filePathArray = path.split("\\.");
        String fileExtension = filePathArray[filePathArray.length - 1]; //Length -1 fordi index starter på 0
        return fileExtension;
    }

    public FileChooser fileChooserWithExtensionFilters(boolean readingFromFile) {
        FileChooser fileChooser = new FileChooser();

        //Dersom man skal lese fra fil legges det til ett samlet filter for jobj og csv
        if (readingFromFile) {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                    "Java Object (*.jobj), " + "Comma-separated values (*.csv)",
                    "*.jobj", "*.csv");

            fileChooser.getExtensionFilters().add(extFilter);
        }

        //Dersom man skriver til fil legges det til to separate filter for csv og jobj
        //slik at brukeren kan velge filformat i listen istede for å skrive filtype manuelt
        else {
            FileChooser.ExtensionFilter extFilterCsv = new FileChooser.ExtensionFilter(
                    "Comma-separated values (*.csv)", "*.csv");
            FileChooser.ExtensionFilter extFilterJobj = new FileChooser.ExtensionFilter(
                    "Java Object (*.jobj)", "*.jobj");
            fileChooser.getExtensionFilters().addAll(extFilterCsv, extFilterJobj);
        }

        return fileChooser;
    }

    public Task executeFileWriterTask(String path, String fileExtension) {
        //Henter listen med kunder for å skrive til fil
        ArrayList<Customer> customersToFile = new ArrayList<>(INS_COMP.getCustomerList());

        //Instansierer en ny singleThread executor
        ExecutorService service = Executors.newSingleThreadExecutor();

        //Oppretter en ny task
        Task task = new FileWriterTask(path, fileExtension, customersToFile);

        //Starter task
        service.execute(task);

        return task;
    }

    public Task executeFileReaderTask(String path, String fileExtension) {
        //Instansierer en ny singleThread executor
        ExecutorService service = Executors.newSingleThreadExecutor();

        //Oppretter en ny fileReaderTask
        Task<List<Customer>> task = new FileReaderTask(path, fileExtension);

        //Starter task
        service.execute(task);

        return task;
    }
}
