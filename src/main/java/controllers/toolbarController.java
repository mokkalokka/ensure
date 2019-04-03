package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.customer.Customer;
import models.customer.ListOfCustomers;
import models.fileReader.SerializedObjectReader;
import models.filewriter.SerializedObjectWriter;
import models.gui.OpenNewStage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class toolbarController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void toolbarOpenFile(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Java Object (*.jobj)", "*.jobj");
        fileChooser.getExtensionFilters().add(extFilter);

        fileChooser.setTitle("Open file");
        String path = fileChooser.showOpenDialog(null).getPath();

        SerializedObjectReader serializedObjectReader = new SerializedObjectReader();

        try {
            ArrayList<Customer> customerListFromFile = (ArrayList<Customer>) serializedObjectReader.readObject(path);
            for (Customer customer : customerListFromFile) {
                ListOfCustomers.addCustomer(customer);
            }
        } catch (IOException e) {
            e.printStackTrace(); //TODO: Fiks exceptions!
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void toolbarSaveAs(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        String path = fileChooser.showSaveDialog(null).getPath();

        SerializedObjectWriter serializedObjectWriter = new SerializedObjectWriter();
        try {
            serializedObjectWriter.writeObject(ListOfCustomers.getCustomers(),path); // TODO: Fiks exceptions!
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
        OpenNewStage openNewStage = new OpenNewStage();
        openNewStage.openNewStage(getCurrentStage(), pathToFXML, stageTitle);
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