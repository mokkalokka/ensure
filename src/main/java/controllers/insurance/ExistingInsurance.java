package controllers.insurance;

import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.insurance.InsuranceHandler;

public class ExistingInsurance implements InsuranceState {

    @Override
    public void setFields(InsuranceController controller) {
        controller.displayExistingInsurance();
    }

    @Override
    public void saveInsurance(InsuranceController controller) throws NoSuchCustomerException, BuilderInputException {
        InsuranceHandler insuranceHandler = new InsuranceHandler();
        insuranceHandler.overwriteExistingInsurance(controller.getEditedInsurance());
    }
}
