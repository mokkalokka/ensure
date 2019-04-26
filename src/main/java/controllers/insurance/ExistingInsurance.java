package controllers.insurance;

import models.exceptions.builderExceptions.BuilderInputException;

public class ExistingInsurance implements InsuranceState {

    @Override
    public void setFields(InsuranceController controller) {
        controller.displayExistingInsurance();
    }

    @Override
    public void saveInsurance(InsuranceController controller) throws BuilderInputException {
        controller.updateInsurance();
    }
}
