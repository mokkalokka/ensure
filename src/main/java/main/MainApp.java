package main;

import javafx.application.Application;
import controllers.CustomersController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.company.InsuranceCompany;


public class MainApp extends Application {

    private final InsuranceCompany INS_COMP = InsuranceCompany.getInstance();

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/view/customers.fxml"));
        Parent root = (Parent) loader.load();

        //Henter kontrolleren
        CustomersController controller = (CustomersController)loader.getController();

        //onWindowShow blir kjort i controlleren etter initialize blir kjort og vinduet har blitt lastet inn
        stage.setOnShown(controller::onWindowShow);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/org/view/styles.css").toExternalForm());

        stage.setTitle(INS_COMP.getName());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
