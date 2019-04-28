package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.threading.FileReaderTask;
import models.customer.Customer;
import models.gui.ErrorDialog;
import models.gui.WindowHandler;
import models.company.InsuranceCompany;
import models.threading.FileWriterTask;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class toolbarController {

    private final InsuranceCompany INS_COMP = InsuranceCompany.getInstance();

    @FXML
    private AnchorPane anchorPane;

    //Progress bar FXML komponenter
    @FXML
    private Stage progressStage;
    private JFXButton btnProgress;
    private Label lblProgress;
    private ProgressBar fxProgressBar;

    @FXML
    private void toolbarOpenFile() {
        boolean readingFromFile = true;
        FileChooser fileChooser = fileChooserWithExtensionFilters();
        fileChooser.setTitle("Åpne fil");
        File file = fileChooser.showOpenDialog(null);

        if(file != null){
            String path = file.getPath();
            String fileExtension = findFileExtension(path);
            Task task = executeFileReaderTask(path, fileExtension);
            progressWindow(task, "Leser fra fil...");
            waitForUpdates(task, readingFromFile);
        }

        else{
            ErrorDialog errorDialog = new ErrorDialog("", "Ingen fil ble valgt");
            errorDialog.show();
        }
    }

    @FXML
    private void toolbarSaveAs(){
        Boolean readingFromFile = false;
        FileChooser fileChooser = fileChooserWithExtensionFilters();
        fileChooser.setTitle("Lagre som...");
        File file = fileChooser.showSaveDialog(null);

        if(file != null){
            String path = file.getPath();
            String fileExtension = findFileExtension(path);

            Task task = executeFileWriterTask(path, fileExtension);
            progressWindow(task, "Skriver til fil...");
            waitForUpdates(task, readingFromFile);
        }

        else{
            ErrorDialog errorDialog = new ErrorDialog("", "Ingen fil ble valgt");
            errorDialog.show();
        }

    }

    private Task executeFileWriterTask(String path, String fileExtension) {

        ArrayList<Customer> customersToFile = new ArrayList<>(INS_COMP.getCustomerList());
        ExecutorService service = Executors.newSingleThreadExecutor();

        Task task = new FileWriterTask(path,fileExtension,customersToFile);
        service.execute(task);

        return task;
    }


    private Task executeFileReaderTask(String path, String fileExtension) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        Task<List<Customer>> task = new FileReaderTask(path, fileExtension);
        service.execute(task);

        return task;
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

            task.setOnSucceeded(event -> {
                if(readingFromFile){
                    addCustomers((List<Customer>) task.getValue());
                }

                lblProgress.setText(succededTitle);
                btnProgress.setText("Lukk");
                btnProgress.setOnAction(e -> progressStage.close());
                fxProgressBar.progressProperty().bind(task.progressProperty());
            });

            task.setOnFailed(event -> {
                progressStage.close();
                ErrorDialog errorDialog = new ErrorDialog(failedTitle,
                        task.getException().getMessage(), isCritical);
                errorDialog.show();


            });

            task.setOnCancelled(event -> {
                ErrorDialog errorDialog = new ErrorDialog("Avbrutt","Abrutt av bruker");
                errorDialog.show();
            });

        }


    }

    private void addCustomers( List<Customer> customerListFromFile) {
        if (customerListFromFile == null) {
            ErrorDialog errorDialog = new ErrorDialog("Error", "Feil ved lesing fra fil");
            errorDialog.show();
        } else {
            INS_COMP.initNewCustomerList(customerListFromFile);
        }
    }

    private String findFileExtension(String path) {
        String[] filePathArray = path.split("\\.");
        String fileExtension = filePathArray[filePathArray.length - 1];
        return fileExtension;
    }

    private FileChooser fileChooserWithExtensionFilters() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJobj = new FileChooser.ExtensionFilter("Java Object (*.jobj)",
                "*.jobj");
        FileChooser.ExtensionFilter extFilterCsv = new FileChooser.ExtensionFilter("Comma-separated values (*.csv)",
                "*.csv");

        fileChooser.getExtensionFilters().add(extFilterJobj);
        fileChooser.getExtensionFilters().add(extFilterCsv);

        return fileChooser;
    }


    @FXML
    private void toolbarClose(){
        Platform.exit();
    }


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

    @FXML
    private void toolbarHelp(ActionEvent event){
        // TODO

    }

    //Finner nåværende stage ved hjelp av en fx:id for å kunne sette parent ved åpning av popup
    private Stage getCurrentStage(){
        return (Stage) anchorPane.getScene().getWindow();
    }



    //Denne metoden tegner ett vindu med progressbar programatisk
    private void progressWindow(Task task, String title){

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
        fxProgressBar.progressProperty().bind(task.progressProperty());

        Scene scene = new Scene(root, 200, 100);

        //Legger til refereanse for css fila
        scene.getStylesheets().add(getClass().getResource("/org/view/styles.css").toExternalForm());

        //Setter eieren til det nye vinduet til å være det du kom fra
        progressStage.initOwner(getCurrentStage());
        //Låser det gamle vinduet til det nye lukkes
        progressStage.initModality(Modality.WINDOW_MODAL);

        progressStage.setTitle(title);
        progressStage.setScene(scene);
        progressStage.show();

    }

    public void initialize(){
        // TODO
    }

}

