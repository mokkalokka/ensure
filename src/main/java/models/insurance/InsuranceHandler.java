package models.insurance;

import models.customer.CustomerHandling;
import models.customer.CustomerList;
import models.exceptions.customerExceptions.NoSuchCustomerException;

public class InsuranceHandler {

    public void addNewInsurance(Insurance insurance) throws NoSuchCustomerException {

        CustomerHandling.addInsuranceToCustomer(insurance);
    }
}
