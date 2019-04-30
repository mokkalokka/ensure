package models.accidentStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AccidentStatement implements Serializable {
    private static final AtomicInteger NEXT_ACCIDENT_NR = new AtomicInteger(100);

    private int registeredTo;
    private LocalDate dateOfAccident;
    private int accidentNr;
    private String accidentType; // type skade, kanskje annet datafelt
    private String accidentDescription;
    private double appraisalAmount; // Takseringsbeøp av skaden
    private double dispersedCompensation; // utbetalt erstatning (kan være mindre enn appraisalAmount)
    private ArrayList<Witness> listOfWitnesses = new ArrayList<>(); // TODO: Finne noe smart her

    public AccidentStatement(int registeredTo, LocalDate dateOfAccident, String accidentType, String accidentDescription, double appraisalAmount, double dispersedCompensation, List<Witness> witnessList) {
        this.accidentNr = NEXT_ACCIDENT_NR.getAndIncrement();
        this.registeredTo = registeredTo;
        this.dateOfAccident = dateOfAccident;
        this.accidentType = accidentType;
        this.accidentDescription = accidentDescription;
        this.appraisalAmount = appraisalAmount;
        this.dispersedCompensation = dispersedCompensation;
        setListOfWitnesses(witnessList);
    }

    public AccidentStatement(int registeredTo, LocalDate dateOfAccident, String accidentType,
                             String accidentDescription, double appraisalAmount, double dispersedCompensation,
                             int accidentNr, List<Witness> witnessList) {

        setAccidentNr(accidentNr);
        this.registeredTo = registeredTo;
        this.dateOfAccident = dateOfAccident;
        this.accidentType = accidentType;
        this.accidentDescription = accidentDescription;
        this.appraisalAmount = appraisalAmount;
        this.dispersedCompensation = dispersedCompensation;
        setListOfWitnesses(witnessList);
    }


    public void setListOfWitnesses(List<Witness> listOfWitnesses) {
        this.listOfWitnesses = new ArrayList<>();

        //Legger til riktig accidentNr for hver av vitnene for å kunne lese/skrive csv
        for(Witness witness : listOfWitnesses){
            witness.setForAccidentStatement(this.accidentNr);
            this.listOfWitnesses.add(witness);
        }
    }

    public void addWitnessContactInfo(Witness witness) {
        witness.setForAccidentStatement(accidentNr);
        this.listOfWitnesses.add(witness);
    }

    public void removeWitness(Witness witnessToRemove) {
        listOfWitnesses.remove(witnessToRemove);
    }

    public ObservableList<Witness> getListOfWitnesses() {
        return FXCollections.observableArrayList(listOfWitnesses);
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
