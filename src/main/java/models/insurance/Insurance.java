package models.insurance;


import models.filewriter.CSVWritable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Insurance implements Serializable, Comparable<Insurance>, CSVWritable {

    private static final AtomicInteger NEXT_INSURANCE_ID = new AtomicInteger(200000);

    private int insuranceID;
    private int registeredTo;
    private double annualPremium;
    private LocalDate dateOfIssue;
    private double total;
    private String coverageDescription; // forsikringsbetingelser

    public Insurance(int registeredTo, double annualPremium, double total, String coverageDescription, LocalDate dateOfIssue) {
        this.registeredTo = registeredTo;
        this.annualPremium = annualPremium;
        this.total = total;
        this.coverageDescription = coverageDescription;
        this.dateOfIssue = dateOfIssue;
        this.insuranceID = NEXT_INSURANCE_ID.getAndIncrement();
    }

    public Insurance(int registeredTo, double annualPremium, double total, String coverageDescription, LocalDate dateOfIssue, int insuranceID) {
        this.registeredTo = registeredTo;
        this.annualPremium = annualPremium;
        this.total = total;
        this.coverageDescription = coverageDescription;
        this.dateOfIssue = dateOfIssue;
        setInsuranceID(insuranceID);
    }

    private void setInsuranceID(int insuranceID) {
        if (insuranceID >= NEXT_INSURANCE_ID.get()) {
            NEXT_INSURANCE_ID.set(insuranceID + 1);
        }
        this.insuranceID = insuranceID;
    }

    public ArrayList<String> getFieldNamesAsStrings() {

        return new ArrayList<>(Arrays.asList("Registrert til",
                "Årlig forsikringspremie",
                "Dato opprettet",
                "Forsikringsbeløp",
                "Forsikringsbetingelser",
                "ForsikringsID"));
    }

    public ArrayList<String> getFieldValuesAsStrings() {
        return new ArrayList<>(Arrays.asList(
                String.valueOf(registeredTo),
                String.valueOf(annualPremium),
                String.valueOf(dateOfIssue),
                String.valueOf(total),
                String.valueOf(coverageDescription),
                String.valueOf(insuranceID)
        ));
    }

    @Override
    public int compareTo(Insurance t) {
        String thisClassName = this.getClass().getSimpleName();
        String compareClassname = t.getClass().getSimpleName();

        //Hvis klassenavnene er like sammenlign registeredTo
        if (thisClassName.equals(compareClassname)) {
            return this.registeredTo - t.registeredTo;
        }

        return thisClassName.compareTo((compareClassname));
    }


    public int getRegisteredTo() {
        return registeredTo;
    }

    public double getAnnualPremium() {
        return annualPremium;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public double getTotal() {
        return total;
    }

    public String getCoverageDescription() {
        return coverageDescription;
    }

    public int getInsuranceID() {
        return insuranceID;
    }

    public void setRegisteredTo(int registeredTo) {
        this.registeredTo = registeredTo;
    }

    public void setAnnualPremium(double annualPremium) {
        this.annualPremium = annualPremium;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setCoverageDescription(String coverageDescription) {
        this.coverageDescription = coverageDescription;
    }
}
