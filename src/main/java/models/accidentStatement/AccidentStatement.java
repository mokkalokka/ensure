package models.accidentStatement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class AccidentStatement implements Serializable {
    private static final AtomicInteger NEXT_ACCIDENT_NR = new AtomicInteger(100);

    private int registeredTo;
    private LocalDate dateOfAccident;
    private int accidentNr; // skal inkrementeres.
    private String accidentType; // type skade, kanskje annet datafelt
    private String accidentDescription;
    // private ArrayList<ContactInfo> witnessContactInfo; // TODO: Finne noe smart her
    private double appraisalAmount; // Takseringsbeøp av skaden
    private double dispersedCompensation; // utbetalt erstatning (kan være mindre enn appraisalAmount)

    public AccidentStatement(int registeredTo, LocalDate dateOfAccident, String accidentType, String accidentDescription, double appraisalAmount, double dispersedCompensation) {
        this.accidentNr = NEXT_ACCIDENT_NR.getAndIncrement();
        this.registeredTo = registeredTo;
        this.dateOfAccident = dateOfAccident;
        this.accidentType = accidentType;
        this.accidentDescription = accidentDescription;
        this.appraisalAmount = appraisalAmount;
        this.dispersedCompensation = dispersedCompensation;
    }

    public AccidentStatement(int registeredTo, LocalDate dateOfAccident, String accidentType,
                             String accidentDescription, double appraisalAmount, double dispersedCompensation,
                             int accidentNr) {

        setAccidentNr(accidentNr);
        this.registeredTo = registeredTo;
        this.dateOfAccident = dateOfAccident;
        this.accidentType = accidentType;
        this.accidentDescription = accidentDescription;
        this.appraisalAmount = appraisalAmount;
        this.dispersedCompensation = dispersedCompensation;
    }


    public ArrayList<String> getFieldValuesAsStrings() {
        return new ArrayList<>(Arrays.asList(
                String.valueOf(registeredTo),
                String.valueOf(dateOfAccident),
                String.valueOf(accidentType),
                String.valueOf(accidentDescription),
                String.valueOf(appraisalAmount),
                String.valueOf(dispersedCompensation),
                String.valueOf(accidentNr)
        ));
    }
    public ArrayList<String> getFieldNamesAsStrings() {
        return new ArrayList<>(Arrays.asList(
                "Registrert til",
                "Dato for skade",
                "Type skade",
                "Beskrivelse av skade",
                "Takseringsbeløp",
                "Utbetalt erstatningsbeløp",
                "Skadenummer"));
    }


    public String getInsuranceName(){
        return "Skademeldinger";
    }

    public int getRegisteredTo() {
        return registeredTo;
    }

    private void setAccidentNr(int accidentNr) {
        if (accidentNr >= NEXT_ACCIDENT_NR.get()) {
            NEXT_ACCIDENT_NR.lazySet(accidentNr + 1);
        }
        this.accidentNr = accidentNr;
    }


    public int getAccidentNr() { return accidentNr; }

    public LocalDate getDateOfAccident() {
        return dateOfAccident;
    }

    public double getAppraisalAmount() {
        return appraisalAmount;
    }

    public double getDispersedCompensation() {
        return dispersedCompensation;
    }

    public String getAccidentDescription() {
        return accidentDescription;
    }

    public String getAccidentType() {
        return accidentType;
    }


    public void setDateOfAccident(LocalDate dateOfAccident) {
        this.dateOfAccident = dateOfAccident;
    }

    public void setAccidentType(String accidentType) {
        this.accidentType = accidentType;
    }

    public void setAccidentDescription(String accidentDescription) {
        this.accidentDescription = accidentDescription;
    }

    public void setAppraisalAmount(double appraisalAmount) {
        this.appraisalAmount = appraisalAmount;
    }

    public void setDispersedCompensation(double dispersedCompensation) {
        this.dispersedCompensation = dispersedCompensation;
    }
}
