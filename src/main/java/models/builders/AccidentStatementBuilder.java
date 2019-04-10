package models.builders;

import models.insurance.AccidentStatement;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AccidentStatementBuilder {

    private LocalDate dateOfAccident;
    private String accidentType; // type skade, kanskje annet datafelt
    private String accidentDescription;
    private double appraisalAmount; // Takseringsbeøp av skaden
    private double dispersedCompensation; // utbetalt erstatning (kan være mindre enn appraisalAmount)

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
        return new AccidentStatement(
                dateOfAccident,
                accidentType,
                accidentDescription,
                appraisalAmount,
                dispersedCompensation);
    }
}
