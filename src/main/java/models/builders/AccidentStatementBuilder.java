package models.builders;

import models.insurance.AccidentStatement;

import java.io.Serializable;
import java.util.Date;

public class AccidentStatementBuilder {

    private Date dateOfAccident;
    private String accidentType; // type skade, kanskje annet datafelt
    private String accidentDescription;
    private double appraisalAmount; // Takseringsbeøp av skaden
    private double dispersedCompensation; // utbetalt erstatning (kan være mindre enn appraisalAmount)

    public AccidentStatementBuilder setDateOfAccident(Date dateOfAccident) {
        this.dateOfAccident = dateOfAccident;
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

    public AccidentStatementBuilder setAppraisalAmount(double appraisalAmount) {
        this.appraisalAmount = appraisalAmount;
        return this;
    }

    public AccidentStatementBuilder setDispersedCompensation(double dispersedCompensation) {
        this.dispersedCompensation = dispersedCompensation;
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
