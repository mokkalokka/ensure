package models.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class OpenScene {

    public void openScene(ActionEvent event, String pathToFXML) {
        try{
            Parent FXML = FXMLLoader.load(getClass().getResource(pathToFXML));
            Scene root = new Scene(FXML);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(root);
            window.show();
        }
        catch (IOException e){
            System.out.println("FXML file not found!");
        }
    }
}
