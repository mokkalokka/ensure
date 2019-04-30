package controllers.accidentStatement;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.accidentStatement.AccidentStatement;
import models.accidentStatement.Witness;
import models.builders.WitnessBuilder;
import models.company.InsuranceCompany;
import models.customer.Customer;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.gui.ErrorDialog;

public class NewWitnessController {

    private Customer curentCustomer;
    private Witness myWitness;
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

}
