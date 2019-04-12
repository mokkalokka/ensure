package models.insurance;

import models.customer.CustomerList;
import models.exceptions.customerExceptions.NoSuchCustomerException;

public class InsuranceHandler {

    public void addNewInsurance(Insurance insurance) throws NoSuchCustomerException {

        CustomerList.addInsuranceToCustomer(insurance);
    }
}
