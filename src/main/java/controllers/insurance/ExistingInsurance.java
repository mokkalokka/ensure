package controllers.insurance;

import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.insurance.InsuranceHandler;

public class ExistingInsurance implements InsuranceState {

    @Override
    public void loadInsurance(InsuranceController controller) {
        controller.loadInsurance();
    }

    @Override
    public void saveInsurance(InsuranceController controller) throws NoSuchCustomerException {
        InsuranceHandler insuranceHandler = new InsuranceHandler();
        insuranceHandler.overwriteExistingInsurance(controller.getCurrentInsurance());
    }
}
