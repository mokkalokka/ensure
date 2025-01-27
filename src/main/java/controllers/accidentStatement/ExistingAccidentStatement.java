package controllers.accidentStatement;

import models.exceptions.builderExceptions.BuilderInputException;

public class ExistingAccidentStatement implements AccidentStatementState {

    @Override
    public void setFields(AccidentStatementController controller) {
        controller.displayExistingAccidentStatement();
        controller.initializeTable();
    }

    @Override
    public void saveAccidentStatement(AccidentStatementController controller) throws BuilderInputException {
        controller.updateAccidentStatement();
    }
}
