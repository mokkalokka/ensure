package models.accidentStatement;

import models.customer.CustomerList;
import models.exceptions.customerExceptions.NoSuchCustomerException;

public class AccidentStatementHandler {

    public void addNewAccidentStatement(AccidentStatement accidentStatement) throws NoSuchCustomerException {

        CustomerList.addAccidentStatementToCustomer(accidentStatement);

    }

    public void overwriteExistingAccidentStatement(AccidentStatement accidentStatement) throws NoSuchCustomerException {

        CustomerList.overwriteAccidentStatementInCustomer(accidentStatement);

    }
}
