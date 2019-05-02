package controllers.insurance;

import models.company.InsuranceCompany;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.insurance.Insurance;

public class NewInsurance implements InsuranceState {

    @Override
    public void setFields(InsuranceController controller) {
        controller.displayNewInsurance();
    }

    @Override
    public void saveInsurance(InsuranceController controller) throws InvalidCustomerException, BuilderInputException {
        Insurance newInsurance = controller.getNewInsurance();

        InsuranceCompany INS_COMP = InsuranceCompany.getInstance();
        INS_COMP.addInsuranceToCustomer(controller.getNewInsurance());

        controller.setInsurance(newInsurance);
        controller.setState(new ExistingInsurance());
    }
}
