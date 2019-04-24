package models.builders;


import models.accidentStatement.AccidentStatement;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.builderExceptions.EmptyFieldException;
import models.exceptions.builderExceptions.InvalidDateFormatException;
import models.exceptions.builderExceptions.NotANumberException;


import java.time.DateTimeException;
import java.time.LocalDate;

public class AccidentStatementBuilder {

    private int registeredTo;
    private LocalDate dateOfAccident;
    private String accidentType; // type skade, kanskje annet datafelt
    private String accidentDescription;
    private double appraisalAmount; // Takseringsbeøp av skaden
    private double dispersedCompensation; // utbetalt erstatning (kan være mindre enn appraisalAmount)
    private int accidentNr = 0;
    private final StringChecker stringChecker = new StringChecker();

    public AccidentStatementBuilder setRegisteredTo(String registeredTo) throws BuilderInputException {
        String fieldName = "Registrert til";

        if (stringChecker.isEmptyOrNull(registeredTo)) {
            throw new EmptyFieldException(fieldName);
        }

        try {
            this.registeredTo = Integer.parseInt(registeredTo);
        } catch (NumberFormatException e) {
            throw new NotANumberException(fieldName);
        }

        return this;
    }

    public AccidentStatementBuilder setAccidentNr(String accidentNr) throws BuilderInputException {
        String fieldName = "Skadenummer";

        if (stringChecker.isEmptyOrNull(accidentNr)) {
            throw new EmptyFieldException(fieldName);
        }

        try {
            this.registeredTo = Integer.parseInt(accidentNr);
        } catch (NumberFormatException e) {
            throw new NotANumberException(fieldName);
        }

        return this;
    }

    public AccidentStatementBuilder setDateOfAccident(String dateOfAccident) throws BuilderInputException {
        String fieldName = "Dato for skade";

        if (stringChecker.isEmptyOrNull(dateOfAccident)) {
            throw new EmptyFieldException(fieldName);
        }
        try{
        this.dateOfAccident = LocalDate.parse(dateOfAccident);

        }
        catch (DateTimeException e){
            throw new InvalidDateFormatException(fieldName);
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
        try{
        this.appraisalAmount = Double.parseDouble(appraisalAmount);

        }
        catch (NumberFormatException e){
            throw new NotANumberException(fieldName);
        }
        return this;
    }

    public AccidentStatementBuilder setDispersedCompensation(String dispersedCompensation) throws BuilderInputException {
        String fieldName = "Utbetalt erstatningsbeløp";

        if (stringChecker.isEmptyOrNull(dispersedCompensation)) {
            throw new EmptyFieldException(fieldName);
        }
        try{

        this.dispersedCompensation = Double.parseDouble(dispersedCompensation);
        }
        catch (NumberFormatException e){
            throw new NotANumberException(fieldName);
        }
        return this;
    }

    public AccidentStatement build(){
        //Dersom accidentNr ikke blir satt av en csv fil blir denne inkrementert
        if (accidentNr == 0){
            return new AccidentStatement(
                    registeredTo,
                    dateOfAccident,
                    accidentType,
                    accidentDescription,
                    appraisalAmount,
                    dispersedCompensation
            );
        }
        return new AccidentStatement(
                registeredTo,
                dateOfAccident,
                accidentType,
                accidentDescription,
                appraisalAmount,
                dispersedCompensation,
                accidentNr
                );
    }
}
