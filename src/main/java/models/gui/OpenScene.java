package models.gui;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class OpenScene {

    public void openScene(ActionEvent event, Parent FXML) {
        Scene root = new Scene(FXML);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(root);
        window.show();
    }
}