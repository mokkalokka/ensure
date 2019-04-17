package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.customer.Customer;
import models.customer.CustomerList;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.fileReader.CsvReader;
import models.fileReader.SerializedObjectReader;
import models.filewriter.CsvWriter;
import models.filewriter.SerializedObjectWriter;
import models.gui.WindowHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class toolbarController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void toolbarOpenFile() {
        List<Customer> customerListFromFile = null;

        FileChooser fileChooser = fileChooserWithExtensionFilters();
        fileChooser.setTitle("Åpne fil");
        String path = fileChooser.showOpenDialog(null).getPath();
        String fileExtension = findFileExtension(path);

        if (fileExtension.equals("jobj")) {
            customerListFromFile = readSerializedObject(path);

        }
        else if (fileExtension.equals("csv")) {
            customerListFromFile = readCsv(path);
        }
        addCustomers(customerListFromFile);
    }

    private List<Customer> readCsv(String path) {
        CsvReader csvReader = new CsvReader();
        try {
            return csvReader.readFile(path);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidCustomerException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void addCustomers(List<Customer> customerListFromFile ) {
        if (customerListFromFile == null) {
            // TODO: Display error window.
            System.err.println("Feil ved lesing fra fil");
        } else {
            CustomerList.initializeNewList(customerListFromFile);
        }
    }

    private List<Customer> readSerializedObject(String path) {
        SerializedObjectReader serializedObjectReader = new SerializedObjectReader();

        try {
            return serializedObjectReader.readFile(path);

        } catch (IOException e) {
            e.printStackTrace(); //TODO: Fiks exceptions!
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String findFileExtension(String path) {
        String[] filePathArray = path.split("\\.");
        String fileExtension = filePathArray[filePathArray.length - 1];
        return fileExtension;
    }

    private FileChooser fileChooserWithExtensionFilters() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJobj = new FileChooser.ExtensionFilter("Java Object (*.jobj)",
                "*.jobj");
        FileChooser.ExtensionFilter extFilterCsv = new FileChooser.ExtensionFilter("Comma-separated values (*.csv)",
                "*.csv");

        fileChooser.getExtensionFilters().add(extFilterJobj);
        fileChooser.getExtensionFilters().add(extFilterCsv);

        return fileChooser;
    }

    @FXML
    private void toolbarSaveAs(){
        FileChooser fileChooser = fileChooserWithExtensionFilters();
        fileChooser.setTitle("Lagre som...");

        String path = fileChooser.showSaveDialog(null).getPath();
        String fileExtension = findFileExtension(path);

        if(fileExtension.equals("jobj")){
            writeToJobj(path);

        }
        else if (fileExtension.equals("csv")){
            writeToCsv(path);
        }


    }

    private void writeToCsv(String path) {
        CsvWriter csvWriter = new CsvWriter();

        ArrayList<Customer> customersToCsv = new ArrayList<>(CustomerList.getCustomerList());
        try {
            csvWriter.writeCustomersData(customersToCsv, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToJobj(String path) {
        SerializedObjectWriter serializedObjectWriter = new SerializedObjectWriter();

        //Kopierer fra observableList til vanlig arraylist som er serializable
        ArrayList<Customer> serializebleCustomers = new ArrayList<>(CustomerList.getCustomerList());

        try {
            serializedObjectWriter.writeObject(serializebleCustomers,path); // TODO: Fiks exceptions!
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void toolbarClose(){
        Platform.exit();
    }


    @FXML
    private void toolbarNewCustomer(){
        String pathToFXML = "/org/view/newCustomer.fxml";
        String stageTitle = "Registrer ny kunde";
        WindowHandler windowHandler = new WindowHandler();
        try {
            windowHandler.openNewStageAndLockCurrent(getCurrentStage(), pathToFXML, stageTitle);
        } catch (IOException e) {
            //TODO error vindu
        }
    }

    @FXML
    private void toolbarHelp(ActionEvent event){

    }

    //Finner nåværende stage ved hjelp av en fx:id for å kunne sette parent ved åpning av popup
    private Stage getCurrentStage(){
        return (Stage) anchorPane.getScene().getWindow();
    }

    public void initialize(){

    }
}
