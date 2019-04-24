package controllers.insurance;

import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;

public interface InsuranceState {

    void setFields(InsuranceController controller);

    void saveInsurance(InsuranceController controller) throws InvalidCustomerException, BuilderInputException;

}
