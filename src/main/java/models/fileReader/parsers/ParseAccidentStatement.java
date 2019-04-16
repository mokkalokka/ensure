package models.fileReader.parsers;

import models.builders.AccidentStatementBuilder;
import models.insurance.AccidentStatement;

public class ParseAccidentStatement {
    public static AccidentStatement parseAccidentStatement(String[] lineArray) {
        AccidentStatement accidentStatement = new AccidentStatementBuilder()
                .setRegisteredTo(lineArray[0])
                .setAccidentNr(lineArray[1])
                .setDateOfAccident(lineArray[2])
                .setAppraisalAmount(lineArray[3])
                .setAccidentDescription(lineArray[4])
                .setAccidentType(lineArray[5])
                .setDispersedCompensation(lineArray[6])
                .build();

        return accidentStatement;
    }
}
