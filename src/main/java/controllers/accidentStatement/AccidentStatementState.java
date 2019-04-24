package controllers.accidentStatement;

import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;

public interface AccidentStatementState {
    void setFields(AccidentStatementController controller);

    void saveAccidentStatement(AccidentStatementController controller) throws InvalidCustomerException, BuilderInputException;
}
