package controllers;

import com.opencsv.CSVReader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.customer.Customer;
import models.customer.CustomerList;
import models.fileReader.CsvReader;
import models.fileReader.SerializedObjectReader;
import models.filewriter.SerializedObjectWriter;
import models.gui.WindowHandler;
import java.io.IOException;
import java.util.ArrayList;

public class toolbarController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void toolbarOpenFile(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Java Object (*.jobj)",
                "*.jobj","*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        fileChooser.setTitle("Open file");
        String path = fileChooser.showOpenDialog(null).getPath();
        String[] filePathArray = path.split("\\.");
        String fileExtension = filePathArray[filePathArray.length - 1];
        System.out.println(fileExtension);

        if(fileExtension.equals("jobj")){
            SerializedObjectReader serializedObjectReader = new SerializedObjectReader();

            try {
                ArrayList<Customer> customerListFromFile = (ArrayList<Customer>) serializedObjectReader.readObject(path);
                for (Customer customer : customerListFromFile) {
                    CustomerList.addCustomer(customer);
                }
            } catch (IOException e) {
                e.printStackTrace(); //TODO: Fiks exceptions!
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        else if (fileExtension.equals("csv")){
            CsvReader csvReader = new CsvReader();

            try {
                csvReader.readCsv(path);
            } catch (IOException e) {
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
