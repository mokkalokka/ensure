package models.Insurance;

import java.util.Date;

public class AccidentStatement {
    private Date dateOfAccident;
    private int accidentNr; // skal inkrementeres.
    private String accidentType; // type skade, kanskje annet datafelt
    private String accidentDescription;
    // private ArrayList<ContactInfo> witnessContactInfo; // TODO: Finne noe smart her
    private double appraisalAmount; // Takseringsbeøp av skaden
    private double dispersedCompensation; // utbetalt erstatning (kan være mindre enn appraisalAmount)

    public AccidentStatement(Date dateOfAccident, int accidentNr, String accidentType, String accidentDescription, double appraisalAmount, double dispersedCompensation) {
        this.dateOfAccident = dateOfAccident;
        this.accidentNr = accidentNr;
        this.accidentType = accidentType;
        this.accidentDescription = accidentDescription;
        this.appraisalAmount = appraisalAmount;
        this.dispersedCompensation = dispersedCompensation;
    }
}
