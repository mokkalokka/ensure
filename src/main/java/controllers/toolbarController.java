package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.customer.Customer;
import models.customer.CustomerList;
import models.exceptions.customerExceptions.DuplicateCustomerException;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.fileReader.CsvReader;
import models.fileReader.SerializedObjectReader;
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
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJobj = new FileChooser.ExtensionFilter("Java Object (*.jobj)",
                "*.jobj");
        FileChooser.ExtensionFilter extFilterCsv = new FileChooser.ExtensionFilter("Comma-separated values (*.csv)",
                "*.csv");

        fileChooser.getExtensionFilters().add(extFilterJobj);
        fileChooser.getExtensionFilters().add(extFilterCsv);

        fileChooser.setTitle("Open file");
        String path = fileChooser.showOpenDialog(null).getPath();
        String[] filePathArray = path.split("\\.");
        String fileExtension = filePathArray[filePathArray.length - 1];

        if (fileExtension.equals("jobj")) {
            SerializedObjectReader serializedObjectReader = new SerializedObjectReader();

            try {
                List<Customer> customerListFromFile = serializedObjectReader.readFile(path);
                if (customerListFromFile == null) {
                    // TODO: Display error window.
                    System.err.println("Feil ved lesing fra fil");
                } else {
                    CustomerList.initializeNewList(customerListFromFile);
                }
            } catch (IOException e) {
                e.printStackTrace(); //TODO: Fiks exceptions!
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if (fileExtension.equals("csv")) {
            CsvReader csvReader = new CsvReader();
            try {
                List<Customer> customerListFromFile = csvReader.readFile(path);
                if (customerListFromFile == null) {
                    // TODO: Display error window.
                    System.err.println("Feil ved lesing fra fil");
                } else {
                    CustomerList.initializeNewList(customerListFromFile);
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InvalidCustomerException e) {
                e.printStackTrace();
            }
        }
    }

            @FXML
    private void toolbarSaveAs(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        String path = fileChooser.showSaveDialog(null).getPath();

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
