package models.fileReader.parsers;

import models.accidentStatement.Witness;
import models.builders.WitnessBuilder;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;

public class ParseWitness {
    public static Witness parseWitness(String[] lineArray) throws BuilderInputException, InvalidCustomerException {
        Witness witness = new WitnessBuilder()
                .setRegisteredTo(lineArray[0])
                .setForAccidentNr(lineArray[1])
                .setFirstName(lineArray[2])
                .setLastName(lineArray[3])
                .setContactInformation(lineArray[4])
                .build();

        return witness;
    }
}
