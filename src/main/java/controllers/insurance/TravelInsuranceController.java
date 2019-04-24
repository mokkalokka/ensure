package controllers.insurance;

import com.jfoenix.controls.JFXRadioButton;
import controllers.detailedCustomerController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import models.builders.travelInsurance.TravelInsuranceBuilder;
import models.customer.Customer;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.insurance.Insurance;
import models.travelInsurance.TravelInsurance;

public class TravelInsuranceController implements InsuranceController{

    private Customer myCustomer;
    private InsuranceState state;
    private TravelInsurance myInsurance;

    @FXML
    private EmbeddedFieldsController embeddedFieldsController;
    @FXML
    private detailedCustomerController parentController;

    @FXML
    private TextField txtMaxCoverage;
    @FXML
    private Label lblCoverageArea;
    @FXML
    private ToggleGroup radioGroup;
    @FXML
    private JFXRadioButton radioStandard;
    @FXML
    private JFXRadioButton radioPremium;

    @FXML
    public void initialize() {
        radioStandard.setUserData("STANDARD");
        radioPremium.setUserData("PREMIUM");

        radioGroup.selectedToggleProperty().addListener((ov, oldToggle, newToggle) -> {
            if (radioGroup.getSelectedToggle() != null) {
                String insuranceType = radioGroup.getSelectedToggle().getUserData().toString();
                setLblCoverageArea(insuranceType);
            }
        });
    }

    private void setLblCoverageArea(String insuranceType) {
        if (insuranceType.equals("STANDARD")) {
            lblCoverageArea.setText("Standard reisemål for reising opptil 30 dager.");
        }
        else if (insuranceType.equals("PREMIUM")) {
            lblCoverageArea.setText("Standard og høyrisiko reiesemål for reising i opptil 50 dager.");
        }
    }



    @Override
    public void load() {
        state.setFields(this);
    }

    @FXML
    private void btnSave() {
        try {
            state.saveInsurance(this);
        } catch (InvalidCustomerException e) {
            e.printStackTrace();
            // TODO: display error window
        }
        parentController.refreshTables();
    }

    @FXML
    private void btnClose() {
        Stage currentStage = getCurrentStage();
        currentStage.close();
    }

    @FXML
    private Stage getCurrentStage() {
        return (Stage) lblCoverageArea.getScene().getWindow();
    }

    @Override
    public Customer getCustomer() {
        return myCustomer;
    }

    @Override
    public void setCustomer(Customer customer) {
        myCustomer = customer;
    }

    @Override
    public EmbeddedFieldsController getEmbeddedFieldsController() {
        return embeddedFieldsController;
    }

    @Override
    public Insurance getNewInsurance() {
        boolean isPremium = radioGroup
                .getSelectedToggle()
                .getUserData()
                .equals("PREMIUM");

        return new TravelInsuranceBuilder()
                .setRegisteredTo(embeddedFieldsController.getTxtRegisteredTo().getText())
                .setAnnualPremium(embeddedFieldsController.getTxtAnnualPremium().getText())
                .setCoverageDescription(embeddedFieldsController.getTxtCoverageDescription().getText())
                .setDateOfIssue(embeddedFieldsController.getTxtDateOfIssue().getText())
                .setTotal(embeddedFieldsController.getTxtTotal().getText())
                .setMaxCoverage(txtMaxCoverage.getText())
                .setPremium(isPremium)
                .build();
    }

    @Override
    public Insurance getEditedInsurance() {
        boolean isPremium = radioGroup
                .getSelectedToggle()
                .getUserData()
                .equals("PREMIUM");

        return new TravelInsuranceBuilder()
                .setInsuranceID(myInsurance.getInsuranceID())
                .setRegisteredTo(embeddedFieldsController.getTxtRegisteredTo().getText())
                .setAnnualPremium(embeddedFieldsController.getTxtAnnualPremium().getText())
                .setCoverageDescription(embeddedFieldsController.getTxtCoverageDescription().getText())
                .setDateOfIssue(embeddedFieldsController.getTxtDateOfIssue().getText())
                .setTotal(embeddedFieldsController.getTxtTotal().getText())
                .setMaxCoverage(txtMaxCoverage.getText())
                .setPremium(isPremium)
                .build();
    }

    @Override
    public void setState(InsuranceState state) {
        this.state = state;
    }

    @Override
    public void setInsurance(Insurance insurance) {
        myInsurance = (TravelInsurance) insurance;
        setSelectedRadioToggle();
    }

    private void setSelectedRadioToggle() {
        if (myInsurance != null) {
            radioPremium.setSelected(myInsurance.isPremium());
        } else {
            radioStandard.setSelected(true);
        }
    }

    @Override
    public void displayExistingInsurance() {
        embeddedFieldsController.displayExistingInsurance(myInsurance);
        displayTravelData();

    }

    @Override
    public void setParentController(detailedCustomerController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void displayNewInsurance() {
        embeddedFieldsController.displayNewInsurance(myCustomer);
    }

    private void displayTravelData() {
        txtMaxCoverage.setText(String.valueOf(myInsurance.getMaxCoverage()));
    }



}
