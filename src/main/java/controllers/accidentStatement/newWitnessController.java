package controllers.accidentStatement;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.accidentStatement.AccidentStatement;
import models.accidentStatement.Witness;
import models.accidentStatement.WitnessHandler;
import models.builders.CustomerBuilder;
import models.builders.WitnessBuilder;
import models.company.InsuranceCompany;
import models.customer.Customer;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.exceptions.customerExceptions.InvalidFirstNameException;
import models.gui.ErrorDialog;

public class newWitnessController {

    private final InsuranceCompany INS_COMP = InsuranceCompany.getInstance();
    private Customer curentCustomer;
    private Witness myWitness;
    private WitnessHandler witnessHandler;
    private AccidentStatementController parentController;
    private AccidentStatement currentAccidentStatement;

    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private JFXTextField txtContactInformation;
    @FXML
    private Label lblStatus;

    @FXML
    private void btnAddWitness() {
        try {
            parentController.addWitness(getNewWitness());

        } catch (BuilderInputException | InvalidCustomerException e) {
            new ErrorDialog("Feil i inndata", e.getMessage()).show();
        }
    }

    private Witness getNewWitness() throws BuilderInputException, InvalidCustomerException {
            return new WitnessBuilder()
                    .setRegisteredTo(String.valueOf(curentCustomer.getInsuranceNr()))
                    .setFirstName(txtFirstName.getText())
                    .setLastName(txtLastName.getText())
                    .setContactInformation(txtContactInformation.getText())
                    .build();
    }


    @FXML
    private void btnClose(){
        Stage currentStage = getCurrentStage();
        currentStage.close();
    }

    //Finner nåværende stage ved hjelp av en fx:id for å kunne lukke dette vinduet
    private Stage getCurrentStage(){
        return (Stage) lblStatus.getScene().getWindow();
    }

    @FXML
    private void updateStatus(String message){
        int customerCount = INS_COMP.getCustomerCount();
        lblStatus.setText(message + "\n\nAntall brukere i systemet: " + customerCount);
    }

    private void invalidInputAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feil formatering av vitne");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }


    public void setParentController(AccidentStatementController parentController) {
        this.parentController = parentController;
        setCurrentAccidentStatement(parentController.getCurrentAccidentStatement());
    }



    private void setCurrentAccidentStatement(AccidentStatement accidentStatement) {
        currentAccidentStatement = accidentStatement;
    }

    public void setCurentCustomer(Customer curentCustomer) {
        this.curentCustomer = curentCustomer;
    }

    public void setWitnessHandler(WitnessHandler witnessHandler) {
        this.witnessHandler = witnessHandler;
    }

    public void initialize() {
        // TODO
    }
}
