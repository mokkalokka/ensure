package models.travelInsurance;

import models.insurance.Insurance;

public class TravelInsurance extends Insurance {
    private String coverageArea; // TODO: Det her må nok være et annet datafelt. HashMap? tvunget til å velge 1?
    private double maxCoverage; // forsikringssum på norsk

    public TravelInsurance(double annualPremium, double total, String coverageDescription, String coverageArea, double maxCoverage) {
        super(annualPremium, total, coverageDescription);
        this.coverageArea = coverageArea;
        this.maxCoverage = maxCoverage;
    }
}
