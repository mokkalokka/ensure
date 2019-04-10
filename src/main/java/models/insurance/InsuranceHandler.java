package models.insurance;

import models.customer.CustomerHandler;
import models.exceptions.customerExceptions.NoSuchCustomerException;

public class InsuranceHandler {

    public void addNewInsurance(Insurance insurance) throws NoSuchCustomerException {

        CustomerHandler.addNewInsuranceToCustomer(insurance);
    }
}
