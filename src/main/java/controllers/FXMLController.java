package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.gui.OpenScene;

import java.io.IOException;

public class FXMLController {

    @FXML
    private Label label;

    @FXML
    private void btnNewCustomerClicked(ActionEvent event) {
        openNewCustomerScene(event);
    }


    private void openNewCustomerScene(ActionEvent event) {
        String pathToFXML = "/org/view/newCustomer.fxml";

        OpenScene openScene = new OpenScene();
        openScene.openScene(event,pathToFXML);
    }




    public void initialize() {
        // TODO
    }
}
