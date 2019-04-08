package models.insurance;


import models.customer.Customer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Insurance implements Serializable {

    private int registeredTo;
    private double annualPremium;
    private LocalDate dateOfIssue;
    private double total; // TODO: forsikringsbeløp, kanskje annet navn + hva er det forno?
    private String coverageDescription; // forsikringsbetingelser, ev. annet navn.

    public Insurance(Customer customer, double annualPremium, double total, String coverageDescription) {
        registeredTo = customer.getInsuranceNr();
        this.annualPremium = annualPremium;
        this.total = total;
        this.coverageDescription = coverageDescription;
        dateOfIssue = LocalDate.now();
    }

    public ArrayList<String> getFieldNamesAsStrings() {

        return new ArrayList<>(Arrays.asList("Registrert til",
                "Årlig forsikringspremie",
                "Dato opprettet",
                "Forsikringsbeløp",
                "Forsikringsbetingelser"));
    }

    public ArrayList<String> getFieldValuesAsStrings() {
        return new ArrayList<>(Arrays.asList(
                String.valueOf(registeredTo),
                String.valueOf(annualPremium),
                String.valueOf(dateOfIssue),
                String.valueOf(total),
                String.valueOf(coverageDescription)
        ));
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
}
