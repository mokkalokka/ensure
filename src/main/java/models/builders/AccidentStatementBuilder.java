package models.builders;


import models.accidentStatement.AccidentStatement;
import models.accidentStatement.Witness;
import models.exceptions.builderExceptions.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccidentStatementBuilder {

    private int registeredTo;
    private LocalDate dateOfAccident;
    private String accidentType; // type skade, kanskje annet datafelt
    private String accidentDescription;
    private double appraisalAmount; // Takseringsbeøp av skaden
    private double dispersedCompensation; // utbetalt erstatning (kan være mindre enn appraisalAmount)
    private int accidentNr = 0;
    private final StringChecker stringChecker = new StringChecker();
    private List<Witness> listOfWitnesses = new ArrayList<>();

    public AccidentStatementBuilder setRegisteredTo(String registeredTo) throws BuilderInputException {
        String fieldName = "Registrert til";

        if (stringChecker.isEmptyOrNull(registeredTo)) {
            throw new EmptyFieldException(fieldName);
        }

        try {
            this.registeredTo = Integer.parseInt(registeredTo);
        } catch (NumberFormatException e) {
            throw new InvalidPositiveIntegerException(fieldName + ": " + registeredTo + ",");
        }

        return this;
    }

    public AccidentStatementBuilder setAccidentNr(String accidentNr) throws BuilderInputException {
        String fieldName = "Skadenummer";

        if (stringChecker.isEmptyOrNull(accidentNr)) {
            throw new EmptyFieldException(fieldName);
        }

        try {
            this.accidentNr = Integer.parseInt(accidentNr);
        } catch (NumberFormatException e) {
            throw new InvalidPositiveIntegerException(fieldName + ": " + accidentNr + ",");
        }

        return this;
    }

    public AccidentStatementBuilder setDateOfAccident(String dateOfAccident) throws BuilderInputException {
        String fieldName = "Dato for skade";

        if (stringChecker.isEmptyOrNull(dateOfAccident)) {
            throw new EmptyFieldException(fieldName);
        }
        try {
            this.dateOfAccident = LocalDate.parse(dateOfAccident);
        } catch (DateTimeException e) {
            throw new InvalidDateFormatException(fieldName + ": " + dateOfAccident + ",");
        }
        return this;
    }

    public AccidentStatementBuilder setAccidentType(String accidentType) throws BuilderInputException {
        String fieldName = "Type skade";

        if (stringChecker.isEmptyOrNull(accidentType)) {
            throw new EmptyFieldException(fieldName);
        }
        this.accidentType = accidentType;
        return this;
    }

    public AccidentStatementBuilder setAccidentDescription(String accidentDescription) throws BuilderInputException {
        String fieldName = "Beskrivelse av skaden";

        if (stringChecker.isEmptyOrNull(accidentDescription)) {
            throw new EmptyFieldException(fieldName);
        }
        this.accidentDescription = accidentDescription;
        return this;
    }

    public AccidentStatementBuilder setAppraisalAmount(String appraisalAmount) throws BuilderInputException {
        String fieldName = "Takseringsbeløp";

        if (stringChecker.isEmptyOrNull(appraisalAmount)) {
            throw new EmptyFieldException(fieldName);
        }
        try {
            this.appraisalAmount = Double.parseDouble(appraisalAmount);
            if (stringChecker.isNegative(appraisalAmount)) {
                throw new InvalidPositiveDoubleException(fieldName + ": " + appraisalAmount + ",");
            }

        } catch (NumberFormatException e) {
            throw new InvalidPositiveDoubleException(fieldName + ": " + appraisalAmount + ",");
        }
        return this;
    }

    public AccidentStatementBuilder setDispersedCompensation(String dispersedCompensation) throws BuilderInputException {
        String fieldName = "Utbetalt erstatningsbeløp";

        if (stringChecker.isEmptyOrNull(dispersedCompensation)) {
            throw new EmptyFieldException(fieldName);
        }
        try {
            this.dispersedCompensation = Double.parseDouble(dispersedCompensation);

            if (stringChecker.isNegative(dispersedCompensation)) {
                throw new InvalidPositiveDoubleException(fieldName + ": " + dispersedCompensation + ",");
            }
        } catch (NumberFormatException e) {
            throw new InvalidPositiveDoubleException(fieldName + ": " + dispersedCompensation + ",");
        }
        return this;
    }

    public AccidentStatementBuilder setListOfWitnesses(List<Witness> witnessList) {
        this.listOfWitnesses = witnessList;
        return this;
    }

    public AccidentStatement build() {
        //Dersom accidentNr ikke blir satt av en csv fil blir denne inkrementert
        if (accidentNr == 0) {
            return new AccidentStatement(
                    registeredTo,
                    dateOfAccident,
                    accidentType,
                    accidentDescription,
                    appraisalAmount,
                    dispersedCompensation,
                    listOfWitnesses
            );
        }
        return new AccidentStatement(
                registeredTo,
                dateOfAccident,
                accidentType,
                accidentDescription,
                appraisalAmount,
                dispersedCompensation,
                accidentNr,
                listOfWitnesses
        );
    }


}
