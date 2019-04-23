package controllers.insurance;


import models.customer.Customer;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.insurance.InsuranceHandler;

public class NewInsurance implements InsuranceState {

    @Override
    public void loadInsurance(InsuranceController controller) {
        Customer customer = controller.getCustomer();
        EmbeddedFieldsController embeddedFieldsController = controller.getEmbeddedFieldsController();
        embeddedFieldsController.displayNewInsurance(customer);
    }

    @Override
    public void saveInsurance(InsuranceController controller) throws InvalidCustomerException {
        InsuranceHandler insuranceHandler = new InsuranceHandler();
        insuranceHandler.addNewInsurance(controller.getNewInsurance());

        controller.setInsurance(controller.getNewInsurance());
        controller.setState(new ExistingInsurance());
    }
}
