package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.gui.OpenNewStage;
import models.gui.OpenScene;

import java.io.IOException;

public class FXMLController {

    public Label lblTemporary;

    @FXML
    private void btnNewCustomerClicked(ActionEvent event) {
        openCustomerScene(event);
    }

    private void openCustomerScene(ActionEvent event) {
        String pathToFXML = "/org/view/newCustomer.fxml";
        String stageTitle = "Registrer ny kunde";

        OpenNewStage openNewStage = new OpenNewStage();
        openNewStage.openNewStage(event, pathToFXML, stageTitle);
    }

    public void initialize() {
        // TODO
    }
}
