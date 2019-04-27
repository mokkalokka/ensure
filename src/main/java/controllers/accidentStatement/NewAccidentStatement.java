package controllers.accidentStatement;

import models.accidentStatement.AccidentStatement;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.insurance.InsuranceCompany;

public class NewAccidentStatement implements AccidentStatementState {

    private final InsuranceCompany INS_COMP = InsuranceCompany.getInstance();

    @Override
    public void setFields(AccidentStatementController controller) {
        controller.displayNewAccidentStatement();
    }

    @Override
    public void saveAccidentStatement(AccidentStatementController controller) throws InvalidCustomerException, BuilderInputException {
        AccidentStatement newAccidentStatement = controller.getNewAccidentStatement();

        INS_COMP.addAccidentStatementToCustomer(newAccidentStatement);

        controller.setAccidentStatement(newAccidentStatement);
        controller.setState(new ExistingAccidentStatement());
    }
}
