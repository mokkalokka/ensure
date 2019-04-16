package controllers.insurance;

import models.exceptions.customerExceptions.InvalidCustomerException;

public interface InsuranceState {

    void loadInsurance(InsuranceController controller);

    void saveInsurance(InsuranceController controller) throws InvalidCustomerException;

}
