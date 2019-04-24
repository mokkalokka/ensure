package controllers.accidentStatement;

import models.accidentStatement.AccidentStatement;
import models.accidentStatement.AccidentStatementHandler;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;

public class NewAccidentStatement implements AccidentStatementState {
    @Override
    public void setFields(AccidentStatementController controller) {
        controller.displayNewAccidentStatement();
    }

    @Override
    public void saveAccidentStatement(AccidentStatementController controller) throws InvalidCustomerException, BuilderInputException {
        AccidentStatement newAccidentStatement = controller.getNewAccidentStatement();

        AccidentStatementHandler accidentStatementHandler = new AccidentStatementHandler();
        accidentStatementHandler.addNewAccidentStatement(newAccidentStatement);

        controller.setAccidentStatement(newAccidentStatement);
        controller.setState(new ExinstingAccidentStatement());
    }
}
