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
import javafx.stage.Stage;
import models.customer.Customer;
import models.customer.CustomerList;
import models.fileReader.CsvReaderTask;
import models.fileReader.SerializedObjectReaderTask;
import models.filewriter.CsvWriterTask;
import models.filewriter.SerializedObjectWriterTask;
import models.gui.ErrorDialog;
import models.gui.WindowHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class toolbarController {

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
        String path = fileChooser.showOpenDialog(null).getPath();
        String fileExtension = findFileExtension(path);
        Task task = executeFileReaderTask(path, fileExtension);
        progressWindow(task, "Leser fra fil...");
        waitForUpdates(task, readingFromFile);
    }

    @FXML
    private void toolbarSaveAs(){
        Boolean readingFromFile = false;
        FileChooser fileChooser = fileChooserWithExtensionFilters();
        fileChooser.setTitle("Lagre som...");

        String path = fileChooser.showSaveDialog(null).getPath();
        String fileExtension = findFileExtension(path);

        Task task = executeFileWriterTask(path, fileExtension);
        progressWindow(task, "Skriver til fil...");
        waitForUpdates(task, false);

    }

    private Task executeFileWriterTask(String path, String fileExtension) {
        Task task = null;
        ExecutorService service = Executors.newSingleThreadExecutor();

        ArrayList<Customer> customersToFile = new ArrayList<>(CustomerList.getCustomerList());

        if(fileExtension.equals("jobj")){
            //SerializedObjectWriter serializedObjectWriter = new SerializedObjectWriter();
            //serializedObjectWriter.writeObject(customersToFile,path); // TODO: Fiks exceptions!
            task = new SerializedObjectWriterTask(customersToFile, path);
            service.execute(task);
        }

        else if (fileExtension.equals("csv")){
            //writeToCsv(path);
            task = new CsvWriterTask(customersToFile, path);
            service.execute(task);
        }
        return task;
    }


    private Task executeFileReaderTask(String path, String fileExtension) {
        Task<List<Customer>> task = null;
        ExecutorService service = Executors.newSingleThreadExecutor();

        if (fileExtension.equals("csv")) {
            task = new CsvReaderTask(path);
            service.execute(task);
        } else if (fileExtension.equals("jobj")) {
            task = new SerializedObjectReaderTask(path);
            service.execute(task);
        }
        return task;
    }

    private void waitForUpdates(Task task, Boolean readingFromFile) {
        if (task != null){

            task.setOnSucceeded(event -> {
                if(readingFromFile){
                    addCustomers((List<Customer>) task.getValue());
                    lblProgress.setText("Alle kunder er lastet inn");
                }
                else {
                    lblProgress.setText("Alle kunder er skrevet til fil");
                }
                btnProgress.setText("Lukk");
                btnProgress.setOnAction(e -> progressStage.close());
                fxProgressBar.progressProperty().bind(task.progressProperty());

            });

            task.setOnFailed(event -> {
                ErrorDialog errorDialog = new ErrorDialog("error", task.getException().getMessage());
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
            CustomerList.initializeNewList(customerListFromFile);
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
            //TODO error vindu
        }
    }

    @FXML
    private void toolbarHelp(ActionEvent event){

    }

    //Finner nåværende stage ved hjelp av en fx:id for å kunne sette parent ved åpning av popup
    private Stage getCurrentStage(){
        return (Stage) anchorPane.getScene().getWindow();
    }


/*    public toolbarController getController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("toolbarController.fxml"));
        return loader.getController();
    }*/

    public void progressWindow(Task task, String title){
        progressStage = new Stage();
        fxProgressBar = new ProgressBar();
        btnProgress = new JFXButton();
        lblProgress = new Label();
        btnProgress.setText("Avbryt");
        btnProgress.setStyle("-fx-background-color: #E5E5E5;");

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

        progressStage.setTitle(title);

        progressStage.setScene(scene);
        progressStage.show();

    }

    public void initialize(){

    }

}





/*
    private void writeToCsv(String path) {
        //CsvWriter csvWriter = new CsvWriter();
        //csvWriter.writeCustomersData(customersToCsv, path);
        Task task = null;
        ExecutorService service = Executors.newSingleThreadExecutor();
        Boolean readingFromFile = false;


        ArrayList<Customer> customersToCsv = new ArrayList<>(CustomerList.getCustomerList());

        task = new CsvWriterTask(customersToCsv,path);
        service.execute(task);
        progressWindow(task,"Skriver til fil...");
        waitForUpdates(task, readingFromFile);

    }

    private void writeToJobj(String path) {
        SerializedObjectWriter serializedObjectWriter = new SerializedObjectWriter();

        //Kopierer fra observableList til vanlig arraylist som er serializable
        ArrayList<Customer> serializebleCustomers = new ArrayList<>(CustomerList.getCustomerList());

        try {
            serializedObjectWriter.writeObject(serializebleCustomers,path); // TODO: Fiks exceptions!
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 */
