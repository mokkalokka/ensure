package controllers.accidentStatement;

import models.accidentStatement.AccidentStatementHandler;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;

public class ExinstingAccidentStatement implements AccidentStatementState {
    @Override
    public void setFields(AccidentStatementController controller) {
        controller.displayExistingAccidentStatement();
    }

    @Override
    public void saveAccidentStatement(AccidentStatementController controller) throws InvalidCustomerException, BuilderInputException {
        AccidentStatementHandler accidentStatementHandler = new AccidentStatementHandler();
        accidentStatementHandler.overwriteExistingAccidentStatement(controller.getEditedAccidentStatement());
    }
}
