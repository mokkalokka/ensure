package controllers.insurance;


import models.customer.Customer;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.insurance.Insurance;
import models.insurance.InsuranceHandler;

public class NewInsurance implements InsuranceState {

    @Override
    public void setFields(InsuranceController controller) {
        controller.displayNewInsurance();
    }

    @Override
    public void saveInsurance(InsuranceController controller) throws InvalidCustomerException {
        Insurance newInsurance = controller.getNewInsurance();

        InsuranceHandler insuranceHandler = new InsuranceHandler();
        insuranceHandler.addNewInsurance(newInsurance);

        controller.setInsurance(newInsurance);
        controller.setState(new ExistingInsurance());
    }
}
