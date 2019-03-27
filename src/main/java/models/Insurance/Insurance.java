package models.Insurance;

import java.util.Date;

public abstract class Insurance {
    private double annualPremium;
    private Date dateOfIssue;
    private double total; // TODO: forsikringsbeløp, kanskje annet navn + hva er det forno?
    private String coverageDescription; // forsikringsbetingelser, ev. annet navn.

    public Insurance(double annualPremium, double total, String coverageDescription) {
        this.annualPremium = annualPremium;
        this.total = total;
        this.coverageDescription = coverageDescription;
        dateOfIssue = new Date();
    }

    // TODO: Legg til abstrakte metoder som må implementeres i subklasser.
    // TODO: implementere Serializable?

}
