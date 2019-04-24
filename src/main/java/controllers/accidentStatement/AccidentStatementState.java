package controllers.accidentStatement;

import models.exceptions.customerExceptions.InvalidCustomerException;

public interface AccidentStatementState {
    void setFields(AccidentStatementController controller);

    void saveAccidentStatement(AccidentStatementController controller) throws InvalidCustomerException;
}
