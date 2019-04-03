package models.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
public class OpenNewStage {

    public void openNewStage(Stage currentStage, String pathToFXML, String stageTitle) {
        try{
            //Henter inn FXML fila til det nye vinduet
            Parent FXML = FXMLLoader.load(getClass().getResource(pathToFXML));
            Stage newStage = new Stage();
            newStage.setTitle(stageTitle);
            newStage.setScene(new Scene(FXML, 350, 400));
            //Finner nåværende stage fra ActionEvent

            //Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            //Setter eieren til den det nye vinduet til å være det du kommer fra
            newStage.initOwner(currentStage);
            //Setter modality slik at det gamle vinduet blir låst frem til det nye blir lukket
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.show();
        }
        catch (IOException e){
            System.out.println("FXML file not found!");
            //TODO:Kaste exception håndtere dette!
        }
    }
}