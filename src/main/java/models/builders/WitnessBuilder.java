package models.builders;

import models.accidentStatement.Witness;
import models.exceptions.customerExceptions.EmptyFieldsException;
import models.exceptions.customerExceptions.InvalidFirstNameException;
import models.exceptions.customerExceptions.InvalidLastNameException;

public class WitnessBuilder {

    private final StringChecker sc = new StringChecker();
    private int registeredTo = 0;
    private String lastName;
    private String firstName;
    private String contactInformation;

    public WitnessBuilder setRegisteredTo(String registeredTo) throws EmptyFieldsException {
        if (sc.isEmptyOrNull(registeredTo)) {
            throw new EmptyFieldsException();
        }
        this.registeredTo = Integer.parseInt(registeredTo);
        return this;
    }

    public WitnessBuilder setLastName(String lastName) throws InvalidLastNameException, EmptyFieldsException {
        if (sc.isEmptyOrNull(lastName)) {
            throw new EmptyFieldsException();
        }
        else if (sc.containsNumbers(lastName)) {
            throw new InvalidLastNameException();
        }
        this.lastName = lastName;
        return this;
    }

    public WitnessBuilder setFirstName(String firstName) throws InvalidFirstNameException, EmptyFieldsException {
        if (sc.isEmptyOrNull(firstName)) {
            throw new EmptyFieldsException();
        }
        else if (sc.containsNumbers(firstName)) {
            throw new InvalidFirstNameException();
        }
        this.firstName = firstName;
        return this;
    }

    public WitnessBuilder setContactInformation(String contactInformation) throws EmptyFieldsException {
        if (sc.isEmptyOrNull(contactInformation)) {
            throw new EmptyFieldsException();
        }
        this.contactInformation = contactInformation;
        return this;
    }


    public Witness build() {
        return new Witness(
                registeredTo,
                firstName,
                lastName,
                contactInformation
        );
    }


}
