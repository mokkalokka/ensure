package controllers.accidentStatement;

import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;

public class ExistingAccidentStatement implements AccidentStatementState {

    @Override
    public void setFields(AccidentStatementController controller) {
        controller.displayExistingAccidentStatement();
    }

    @Override
    public void saveAccidentStatement(AccidentStatementController controller) throws InvalidCustomerException, BuilderInputException {
        controller.updateAccidentStatement();
    }
}
