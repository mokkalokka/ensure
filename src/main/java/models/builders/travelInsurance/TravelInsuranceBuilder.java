package models.builders.travelInsurance;

import models.travelInsurance.TravelInsurance;

import java.time.LocalDate;

public class TravelInsuranceBuilder {
    private boolean isPremium;
    private double maxCoverage; // forsikringssum på norsk

    //Fra superklassen Insurance
    private int registeredTo;
    private double annualPremium;
    private LocalDate dateOfIssue;
    private double total; // TODO: forsikringsbeløp, kanskje annet navn + hva er det forno?
    private String coverageDescription; // forsikringsbetingelser, ev. annet navn.

    public TravelInsuranceBuilder setPremium(boolean premium) {
        isPremium = premium;
        return this;
    }

    public TravelInsuranceBuilder setMaxCoverage(double maxCoverage) {
        this.maxCoverage = maxCoverage;
        return this;
    }

    public TravelInsuranceBuilder setRegisteredTo(int registeredTo) {
        this.registeredTo = registeredTo;
        return this;
    }

    public TravelInsuranceBuilder setAnnualPremium(double annualPremium) {
        this.annualPremium = annualPremium;
        return this;
    }

    public TravelInsuranceBuilder setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
        return this;
    }

    public TravelInsuranceBuilder setTotal(double total) {
        this.total = total;
        return this;
    }

    public TravelInsuranceBuilder setCoverageDescription(String coverageDescription) {
        this.coverageDescription = coverageDescription;
        return this;
    }

    public TravelInsurance build(){
        return new TravelInsurance(
                registeredTo,
                annualPremium,
                total,
                coverageDescription,
                maxCoverage,
                isPremium);
    }
}
