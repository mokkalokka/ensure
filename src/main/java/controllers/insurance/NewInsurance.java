package controllers.insurance;


import models.customer.Customer;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.insurance.InsuranceHandler;

public class NewInsurance implements InsuranceState {

    @Override
    public void loadInsurance(InsuranceController controller) {
        Customer customer = controller.getMyCustomer();
        EmbeddedFieldsController embeddedFieldsController = controller.getEmbeddedFieldsController();
        embeddedFieldsController.displayNewInsurance(customer);
    }

    @Override
    public void saveInsurance(InsuranceController controller) throws InvalidCustomerException {
        InsuranceHandler insuranceHandler = new InsuranceHandler();
        insuranceHandler.addNewInsurance(controller.getCurrentInsurance());

        controller.setMyInsurance(controller.getCurrentInsurance());
        controller.setState(new ExistingInsurance());
    }
}
