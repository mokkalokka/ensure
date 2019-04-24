package controllers.insurance;

import controllers.detailedCustomerController;
import models.customer.Customer;
import models.exceptions.builderExceptions.BuilderInputException;
import models.insurance.Insurance;

public interface InsuranceController {

    Customer getCustomer();

    void setCustomer(Customer customer);

    EmbeddedFieldsController getEmbeddedFieldsController();

    Insurance getNewInsurance() throws BuilderInputException;

    Insurance getEditedInsurance() throws BuilderInputException;

    void load();

    void setState(InsuranceState state);

    void setInsurance(Insurance insurance);

    void displayExistingInsurance();

    void displayNewInsurance();

    void setParentController(detailedCustomerController parent);
}
