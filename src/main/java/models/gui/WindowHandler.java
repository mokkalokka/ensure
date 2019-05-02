package models.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowHandler {

    public void openNewStageAndLockCurrent(Stage currentStage, String pathToFXML, String stageTitle) throws IOException {
        //Henter inn FXML fila til det nye vinduet
        Parent FXML = FXMLLoader.load(getClass().getResource(pathToFXML));
        openNewStageAndLockCurrent(currentStage, FXML, stageTitle);
    }

    public void openNewStageAndLockCurrent(Stage currentStage, Parent newFXMLDocument, String stageTitle) {
        //Setter stage med
        Stage newStage = new Stage();
        newStage.setTitle(stageTitle);
        newStage.setScene(new Scene(newFXMLDocument));

        //legger til styles.css i det nye vinduet
        newStage.getScene().getStylesheets().add(getClass().getResource("/org/view/styles.css").toExternalForm());

        //Setter eieren til det nye vinduet til å være det du kom fra
        newStage.initOwner(currentStage);
        //Låser det gamle vinduet til det nye lukkes
        newStage.initModality(Modality.WINDOW_MODAL);

        newStage.show();
    }
}