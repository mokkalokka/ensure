package models.insurance;


import models.customer.Customer;

import java.io.Serializable;
import java.util.Date;

public abstract class Insurance implements Serializable {

    private int registeredTo;
    private double annualPremium;
    private Date dateOfIssue;
    private double total; // TODO: forsikringsbeløp, kanskje annet navn + hva er det forno?
    private String coverageDescription; // forsikringsbetingelser, ev. annet navn.

    public Insurance(Customer customer, double annualPremium, double total, String coverageDescription) {
        registeredTo = customer.getInsuranceNr();
        this.annualPremium = annualPremium;
        this.total = total;
        this.coverageDescription = coverageDescription;
        dateOfIssue = new Date();
    }


    // TODO: Legg til abstrakte metoder som må implementeres i subklasser.

    public int getRegisteredTo() {
        return registeredTo;
    }
}
