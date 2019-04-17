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
import models.filewriter.CsvWriter;
import models.filewriter.SerializedObjectWriter;
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
        
        FileChooser fileChooser = fileChooserWithExtensionFilters();
        fileChooser.setTitle("Åpne fil");
        String path = fileChooser.showOpenDialog(null).getPath();
        String fileExtension = findFileExtension(path);
        Task task = executeFileReaderTask(path, fileExtension);
        waitForUpdatesAndAddCustomers(task);

    }

    private void waitForUpdatesAndAddCustomers(Task task) {
        if (task != null){

            progressWindow(task);
            task.setOnSucceeded(event -> {
                addCustomers((List<Customer>) task.getValue());
                btnProgress.setText("Lukk");
                btnProgress.setOnAction(e -> progressStage.close());
                lblProgress.setText("Alle kunder er lastet inn");
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

    public void progressWindow(Task task){
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
        root.getChildren().addAll(fxProgressBar, btnProgress,lblProgress);
        fxProgressBar.progressProperty().bind(task.progressProperty());

        Scene scene = new Scene(root, 200, 100);

        progressStage.setTitle("Leser inn fil...");

        progressStage.setScene(scene);
        progressStage.show();

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
    private void toolbarSaveAs(){
        FileChooser fileChooser = fileChooserWithExtensionFilters();
        fileChooser.setTitle("Lagre som...");

        String path = fileChooser.showSaveDialog(null).getPath();
        String fileExtension = findFileExtension(path);

        if(fileExtension.equals("jobj")){
            writeToJobj(path);

        }
        else if (fileExtension.equals("csv")){
            writeToCsv(path);
        }


    }

    private void writeToCsv(String path) {
        CsvWriter csvWriter = new CsvWriter();

        ArrayList<Customer> customersToCsv = new ArrayList<>(CustomerList.getCustomerList());
        try {
            csvWriter.writeCustomersData(customersToCsv, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void initialize(){

    }
}
