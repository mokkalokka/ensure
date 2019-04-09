package models.insurance;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class AccidentStatement implements Serializable {
    private static final AtomicInteger NEXT_ACCIDENT_NR = new AtomicInteger(100);

    private Date dateOfAccident;
    private int accidentNr; // skal inkrementeres.
    private String accidentType; // type skade, kanskje annet datafelt
    private String accidentDescription;
    // private ArrayList<ContactInfo> witnessContactInfo; // TODO: Finne noe smart her
    private double appraisalAmount; // Takseringsbeøp av skaden
    private double dispersedCompensation; // utbetalt erstatning (kan være mindre enn appraisalAmount)

    public AccidentStatement(Date dateOfAccident, String accidentType, String accidentDescription, double appraisalAmount, double dispersedCompensation) {
        this.dateOfAccident = dateOfAccident;
        this.accidentNr = NEXT_ACCIDENT_NR.getAndIncrement();
        this.accidentType = accidentType;
        this.accidentDescription = accidentDescription;
        this.appraisalAmount = appraisalAmount;
        this.dispersedCompensation = dispersedCompensation;
    }
}
