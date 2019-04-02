package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.gui.OpenNewStage;

public class toolbarController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void toolbarOpenFile(ActionEvent event){

    }

    @FXML
    private void toolbarSaveAs(ActionEvent event){

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
