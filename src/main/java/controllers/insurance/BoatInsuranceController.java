package controllers.insurance;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import models.builders.boatInsurance.BoatBuilder;
import models.builders.boatInsurance.BoatInsuranceBuilder;
import models.customer.Customer;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.insurance.Insurance;
import models.insurance.InsuranceHandler;
import models.insurance.boatInsurance.Boat;
import models.insurance.boatInsurance.BoatInsurance;
import models.insurance.boatInsurance.BoatOwner;

public class BoatInsuranceController implements InsuranceController {

    private Customer myCustomer;
    private InsuranceState state;
    private BoatInsurance myInsurance;

    @FXML
    private Parent embeddedFields;
    @FXML
    private EmbeddedFieldsController embeddedFieldsController;


    // En slags 'state' for om nåværende forsikring er ny, eller skal overskrive en eksisterende.
    // Siden det kun er 2 states og disse påvirker bare 1 metode, er det valgt en enkel løsning med boolsk variabel,
    // i stedet for å implementere State-designmønsteret.
    private boolean currentInsuranceIsNew;

    @FXML
    private TextField txtOwnerSurname;
    @FXML
    private TextField txtOwnerFirstName;
    @FXML
    private TextField txtRegistrationNr;
    @FXML
    private TextField txtBoatType;
    @FXML
    private TextField txtLengthInFt;
    @FXML
    private TextField txtModelYear;
    @FXML
    private TextField txtEngineType;
    @FXML
    private TextField txtBoatModel;
    @FXML
    private TextField txtEngineHP;

    @FXML
    public void initialize() {
        state = new NewInsurance();
        state.loadInsurance(this);
    }


    @FXML
    private void btnSave() {
        try {
            if (currentInsuranceIsNew) {
                saveNewBoatInsurance();
                currentInsuranceIsNew = false;
            }
            else {
                overwriteExistingInsurance();
            }
        } catch (NoSuchCustomerException e) {
            e.printStackTrace();
            // TODO: Display error window.
        }
    }


    private void saveNewBoatInsurance() throws NoSuchCustomerException {
        InsuranceHandler insuranceHandler = new InsuranceHandler();
        insuranceHandler.addNewInsurance(getCurrentInsurance());
    }

    private void overwriteExistingInsurance() {
        /* TODO: OVerwrite existing insurance here :

        try {
           // CustomerList.overwriteInsurance(getCurrentInsurance());
        } catch (NoSuchCustomerException e) {
            e.printStackTrace();
            // TODO: Display error window.
        }
        */
    }

    public Insurance getCurrentInsurance() {
        return new BoatInsuranceBuilder()
                .setRegisteredTo(embeddedFieldsController.getTxtRegisteredTo().getText())
                .setAnnualPremium(embeddedFieldsController.getTxtAnnualPremium().getText())
                .setCoverageDescription(embeddedFieldsController.getTxtCoverageDescription().getText())
                .setTotal(embeddedFieldsController.getTxtTotal().getText())
                .setBoat(getCurrentBoat())
                .build();
    }

    private Boat getCurrentBoat() {
        return new BoatBuilder()
                .setRegistrationNr(txtRegistrationNr.getText())
                .setBoatModel(txtBoatModel.getText())
                .setBoatType(txtBoatType.getText())
                .setEngineHP(txtEngineHP.getText())
                .setEngineType(txtEngineType.getText())
                .setLengthInft(txtLengthInFt.getText())
                .setModelYear(txtModelYear.getText())
                .setOwner(new BoatOwner(txtOwnerFirstName.getText(), txtOwnerSurname.getText()))
                .build();
    }

    public void loadInsurance() {
        txtRegistrationNr.setText(myInsurance.getBoat().getBoatModel());
        txtBoatModel.setText(myInsurance.getBoat().getBoatModel());
        txtBoatType.setText(myInsurance.getBoat().getBoatType());
        txtEngineHP.setText(String.valueOf(myInsurance.getBoat().getEngineHP()));
        txtEngineType.setText(myInsurance.getBoat().getEngineType());
        txtLengthInFt.setText(String.valueOf(myInsurance.getBoat().getLengthInft()));
        txtModelYear.setText(myInsurance.getBoat().getModelYear());
        txtOwnerFirstName.setText(myInsurance.getBoat().getOwner().getFirstName());
        txtOwnerSurname.setText(myInsurance.getBoat().getOwner().getLastName());
    }

    public EmbeddedFieldsController getEmbeddedFieldsController() {
        return embeddedFieldsController;
    }

    public void setState(InsuranceState state) {
        this.state = state;
    }

    @Override
    public void setMyInsurance(Insurance insurance) {
        myInsurance = (BoatInsurance) insurance;
    }

    public Customer getMyCustomer() {
        return myCustomer;
    }

    public TextField getTxtOwnerSurname() {
        return txtOwnerSurname;
    }

    public TextField getTxtOwnerFirstName() {
        return txtOwnerFirstName;
    }

    public TextField getTxtRegistrationNr() {
        return txtRegistrationNr;
    }

    public TextField getTxtBoatType() {
        return txtBoatType;
    }

    public TextField getTxtLengthInFt() {
        return txtLengthInFt;
    }

    public TextField getTxtModelYear() {
        return txtModelYear;
    }

    public TextField getTxtEngineType() {
        return txtEngineType;
    }

    public TextField getTxtBoatModel() {
        return txtBoatModel;
    }

    public TextField getTxtEngineHP() {
        return txtEngineHP;
    }
}
