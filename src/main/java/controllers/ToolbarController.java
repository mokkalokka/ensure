package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.fileHandler.FileHandler;
import models.customer.Customer;
import models.gui.ErrorDialog;
import models.gui.WindowHandler;
import models.company.InsuranceCompany;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ToolbarController {

    private final InsuranceCompany INS_COMP = InsuranceCompany.getInstance();
    private CustomersController customersController;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Menu menu;

    //ProgressWindow FXML komponenter som blir tegnet av newProgressWindow()
    @FXML
    private Stage progressStage;
    private JFXButton btnProgress;
    private Label lblProgress;
    private ProgressBar fxProgressBar;

    @FXML
    private void toolbarOpenFile() {
        FileHandler fileHandler = new FileHandler();

        boolean readingFromFile = true;
        //Henter en ny filvelger som har filter i henhold til at man leser fra fil
        FileChooser fileChooser = fileHandler.fileChooserWithExtensionFilters(readingFromFile);
        fileChooser.setTitle("Åpne fil");
        //Definerer filen som skal leses via en filvalg vindu
        File file = fileChooser.showOpenDialog(null);

        //Dersom man har valgt en fil
        if(file != null){
            String path = file.getPath();
            String fileExtension = fileHandler.findFileExtension(path);
            //Starter en ny tråd for lesing av fil og sender ved filtypen
            Task task = fileHandler.executeFileReaderTask(path, fileExtension);
            newProgressWindow(task, "Leser fra fil...");
            //Låser gui elementer
            setReadOnly(true);
            waitForUpdates(task, readingFromFile);
        }

        //Dersom fileChooser vinduet blir lukket uten å velge en fil vis feilmeldingen
        else{
            new ErrorDialog("", "Ingen fil ble valgt")
            .show();
        }
    }

    @FXML
    private void toolbarSaveAs(){
        FileHandler fileHandler = new FileHandler();

        Boolean readingFromFile = false;
        //Henter en ny filvelger som har filter i henhold til at man lagrer til fil
        FileChooser fileChooser = fileHandler.fileChooserWithExtensionFilters(readingFromFile);
        fileChooser.setTitle("Lagre som...");
        //Definerer filen som skal leses via en filvalg vindu
        File file = fileChooser.showSaveDialog(null);

        //Dersom man har valgt en fil
        if(file != null){
            String path = file.getPath();
            String fileExtension = fileHandler.findFileExtension(path);
            //Starter en ny tråd for skriving av fil og sender ved filtypen
            Task task = fileHandler.executeFileWriterTask(path, fileExtension);
            newProgressWindow(task, "Skriver til fil...");
            //Låser gui elementer
            setReadOnly(true);
            waitForUpdates(task, readingFromFile);
        }

        //Dersom fileChooser vinduet blir lukket uten å velge en fil vis feilmeldingen
        else{
            new ErrorDialog("", "Ingen fil ble valgt")
            .show();
        }
    }

    //Denne metoden tegner ett vindu med progressbar programatisk
    private void newProgressWindow(Task task, String title){

        //Instansierer det nye vinduet og elementene som skal være med
        progressStage = new Stage();
        fxProgressBar = new ProgressBar();
        btnProgress = new JFXButton();
        lblProgress = new Label();
        btnProgress.setText("Avbryt");

        //Legger til muligheten til å kanselere skriving/lesing av fil fra knappen
        btnProgress.setOnAction(event -> {
            task.cancel();
            progressStage.close();
        });

        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(20);
        root.getChildren().addAll(fxProgressBar, btnProgress, lblProgress);

        //Binder progressbaren til task sin progresjon
        //Denne vil i prakssis kun vise om den jobber eller er ferdig
        fxProgressBar.progressProperty().bind(task.progressProperty());

        //Definerer størrelsen til det nye vinduet
        Scene scene = new Scene(root, 200, 100);

        //Legger til refereanse for css fila
        scene.getStylesheets().add(getClass().getResource("/org/view/styles.css")
                .toExternalForm());

        //Setter tittel som er avhengig av om man skriver eller leser
        progressStage.setTitle(title);

        //Legger til scene i den nye stagen og viser denne til skjerm
        progressStage.setScene(scene);
        progressStage.show();

    }

    private void waitForUpdates(Task task, Boolean readingFromFile) {
        if (task != null){
            boolean isCritical = true;
            String succededTitle;
            String failedTitle;

            if(readingFromFile){
                succededTitle = "Alle kunder er lastet inn";
                failedTitle = "Feil ved lesing av fil";
            }
            else{
                succededTitle = "Alle kunder er skrevet til fil";
                failedTitle = "Feil ved skriving til fil";
            }

            //Når task for lesing/skriving er fullført
            task.setOnSucceeded(event -> {
                //Dersom man har lest fra fil, legg disse kundene til i lista til forsikringsselskapet
                if(readingFromFile){
                    addCustomers((List<Customer>) task.getValue());
                }
                //Låser opp alle gui funksjoner
                setReadOnly(false);

                //Oppdaterer informasjonen på progressWindow
                lblProgress.setText(succededTitle);
                btnProgress.setText("Lukk");

                //Setter knappen i progressWindow til å lukke vinduet onAction
                btnProgress.setOnAction(e -> progressStage.close());
            });

            //Dersom task blir slutter ved en feil:
            task.setOnFailed(event -> {
                // Låser opp gui funksjoner
                setReadOnly(false);
                progressStage.close();

                //Viser feilen som kommer fra task.getException() til skjerm ved ErrorDialog
                new ErrorDialog(failedTitle,
                        task.getException().getMessage(), isCritical)
                .show();
            });

            //Dersom task blir avbrutt av bruker
            task.setOnCancelled(event -> {
                // Låser opp gui funksjoner
                setReadOnly(false);

                new ErrorDialog("Avbrutt","Abrutt av bruker")
                .show();
            });
        }
    }

    //Legger til kundene fra fil inn i forsikringsselskapets liste dersom ingen feil har oppstått
    private void addCustomers( List<Customer> customerListFromFile) {
        //Dersom lista er tom vis error
        if (customerListFromFile == null) {
            new ErrorDialog("Error", "Feil ved lesing fra fil")
            .show();
        }
        //Ellers, overskriv kundelista med den nye lista fra fil
        else {
            INS_COMP.initNewCustomerList(customerListFromFile);
        }
    }

    //Åpner vinduet for å legge til en ny kunde
    @FXML
    private void toolbarNewCustomer(){
        String pathToFXML = "/org/view/newCustomer.fxml";
        String stageTitle = "Registrer ny kunde";
        WindowHandler windowHandler = new WindowHandler();
        try {
            windowHandler.openNewStageAndLockCurrent(getCurrentStage(), pathToFXML, stageTitle);
        } catch (IOException e) {
            ErrorDialog errorDialog = new ErrorDialog("Feil ved innlasting av vindu",
                    "Finner ikke filen til vinduet", true);
            errorDialog.show();
        }
    }

    //Finner nåværende stage ved hjelp av en fx:id for å kunne sette parent ved åpning av popup
    private Stage getCurrentStage(){
        return (Stage) anchorPane.getScene().getWindow();
    }

    @FXML
    private void toolbarClose(){
        Platform.exit();
    }

    //Får tilgang til customersController for å kunne låse elementer ved lesing/skriving
    public void setCustomersController(CustomersController customersController) {
        this.customersController = customersController;
    }

    // En metode som låser/låser opp gui elementer når man leser inn / skriver til fil
    private void setReadOnly(boolean isReadOnly){
        customersController.setReadOnly(isReadOnly);
        menu.setDisable(isReadOnly);
    }

}

