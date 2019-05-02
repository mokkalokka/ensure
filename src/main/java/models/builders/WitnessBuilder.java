package models.builders;

import models.accidentStatement.Witness;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.builderExceptions.EmptyFieldException;
import models.exceptions.customerExceptions.InvalidFirstNameException;
import models.exceptions.customerExceptions.InvalidLastNameException;

public class WitnessBuilder {

    private final StringChecker sc = new StringChecker();
    private int registeredTo;
    private int forAccidentNr = 0;
    private String lastName;
    private String firstName;
    private String contactInformation;

    public WitnessBuilder setRegisteredTo(String registeredTo) throws BuilderInputException {
        if (sc.isEmptyOrNull(registeredTo)) {
            throw new EmptyFieldException("Registrert til");
        }
        this.registeredTo = Integer.parseInt(registeredTo);
        return this;
    }

    public WitnessBuilder setForAccidentNr(String forAccidentNr) throws BuilderInputException {
        if (sc.isEmptyOrNull(forAccidentNr)) {
            throw new EmptyFieldException("Hører til skadenummer");
        }
        this.forAccidentNr = Integer.parseInt(forAccidentNr);
        return this;
    }

    public WitnessBuilder setLastName(String lastName) throws BuilderInputException, InvalidLastNameException {
        String fieldName = "Etternavn";
        if (sc.isEmptyOrNull(lastName)) {
            throw new EmptyFieldException(fieldName);
        } else if (sc.containsNumbers(lastName)) {
            throw new InvalidLastNameException();
        }
        this.lastName = lastName;
        return this;
    }

    public WitnessBuilder setFirstName(String firstName) throws BuilderInputException, InvalidFirstNameException {
        if (sc.isEmptyOrNull(firstName)) {
            throw new EmptyFieldException("Fornavn");
        } else if (sc.containsNumbers(firstName)) {
            throw new InvalidFirstNameException();
        }
        this.firstName = firstName;
        return this;
    }

    public WitnessBuilder setContactInformation(String contactInformation) throws BuilderInputException {
        if (sc.isEmptyOrNull(contactInformation)) {
            throw new EmptyFieldException("Kontaktinformasjon");
        }
        this.contactInformation = contactInformation;
        return this;
    }


    public Witness build() {
        if (forAccidentNr == 0) {
            return new Witness(
                    registeredTo,
                    firstName,
                    lastName,
                    contactInformation);
        } else {
            return new Witness(
                    registeredTo,
                    firstName,
                    lastName,
                    contactInformation,
                    forAccidentNr);
        }

    }
}



