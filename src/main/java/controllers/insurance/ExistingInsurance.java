package controllers.insurance;

import models.insurance.InsuranceHandler;

public class ExistingInsurance implements InsuranceState {

    @Override
    public void loadInsurance(InsuranceController controller) {
        controller.loadInsurance();
    }

    @Override
    public void saveInsurance(InsuranceController controller) {
        InsuranceHandler insuranceHandler = new InsuranceHandler();
        insuranceHandler.overwriteInsurance(controller.getCurrentInsurance());
    }
}
