package models.fileReader.parsers;

import models.accidentStatement.AccidentStatement;
import models.builders.AccidentStatementBuilder;
import models.exceptions.builderExceptions.BuilderInputException;


public class ParseAccidentStatement {
    public static AccidentStatement parseAccidentStatement(String[] lineArray) throws BuilderInputException {
        AccidentStatement accidentStatement = new AccidentStatementBuilder()
                .setRegisteredTo(lineArray[0])
                .setDateOfAccident(lineArray[1])
                .setAccidentType(lineArray[2])
                .setAccidentDescription(lineArray[3])
                .setAppraisalAmount(lineArray[4])
                .setDispersedCompensation(lineArray[5])
                .setAccidentNr(lineArray[6])
                .build();

        return accidentStatement;
    }
}
