package models.builders;

import models.insurance.AccidentStatement;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AccidentStatementBuilder {

    private int registeredTo;
    private LocalDate dateOfAccident;
    private String accidentType; // type skade, kanskje annet datafelt
    private String accidentDescription;
    private double appraisalAmount; // Takseringsbeøp av skaden
    private double dispersedCompensation; // utbetalt erstatning (kan være mindre enn appraisalAmount)
    private int accidentNr = 0;

    public AccidentStatementBuilder setRegisteredTo(String registeredTo) {
        this.registeredTo = Integer.parseInt(registeredTo);
        return this;
    }

    public AccidentStatementBuilder setAccidentNr(String accidentNr) {
        this.accidentNr = Integer.parseInt(accidentNr);
        return this;
    }

    public AccidentStatementBuilder setDateOfAccident(String dateOfAccident) {
        //TODO: Mulig å endre date format i parse
        this.dateOfAccident = LocalDate.parse(dateOfAccident);
        return this;
    }

    public AccidentStatementBuilder setAccidentType(String accidentType) {
        this.accidentType = accidentType;
        return this;
    }

    public AccidentStatementBuilder setAccidentDescription(String accidentDescription) {
        this.accidentDescription = accidentDescription;
        return this;
    }

    public AccidentStatementBuilder setAppraisalAmount(String appraisalAmount) {
        this.appraisalAmount = Double.parseDouble(appraisalAmount);
        return this;
    }

    public AccidentStatementBuilder setDispersedCompensation(String dispersedCompensation) {
        this.dispersedCompensation = Double.parseDouble(dispersedCompensation);
        return this;
    }

    public AccidentStatement build(){
        //Dersom accidentNr ikke blir satt av en csv fil blir denne inkrementert
        if (accidentNr == 0){
            accidentNr = AccidentStatement.NEXT_ACCIDENT_NR.getAndIncrement();
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
