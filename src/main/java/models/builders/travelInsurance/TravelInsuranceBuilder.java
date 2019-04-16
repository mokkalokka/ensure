package models.builders.travelInsurance;

import models.travelInsurance.TravelInsurance;

import java.time.LocalDate;

public class TravelInsuranceBuilder {
    private boolean isPremium;
    private double maxCoverage; // forsikringssum på norsk

    //Fra superklassen Insurance
    private int registeredTo;
    private double annualPremium;
    private LocalDate dateOfIssue = null;
    private double total; // TODO: forsikringsbeløp, kanskje annet navn + hva er det forno?
    private String coverageDescription; // forsikringsbetingelser, ev. annet navn.
    private int insuranceID;

    public TravelInsuranceBuilder setInsuranceID(String insuranceID) {
        this.insuranceID = Integer.parseInt(insuranceID);
        return this;
    }

    public TravelInsuranceBuilder setPremium(String isPremium) {
        this.isPremium = Boolean.parseBoolean(isPremium);
        return this;
    }

    public TravelInsuranceBuilder setMaxCoverage(String maxCoverage) {
        this.maxCoverage = Double.parseDouble(maxCoverage);
        return this;
    }

    public TravelInsuranceBuilder setRegisteredTo(String registeredTo) {
        this.registeredTo = Integer.parseInt(registeredTo);
        return this;
    }

    public TravelInsuranceBuilder setAnnualPremium(String annualPremium) {
        this.annualPremium = Double.parseDouble(annualPremium);
        return this;
    }

    public TravelInsuranceBuilder setDateOfIssue(String dateOfIssue) {
        //TODO: Kan endre date format i parse her
        this.dateOfIssue = LocalDate.parse(dateOfIssue);
        return this;
    }

    public TravelInsuranceBuilder setTotal(String total) {
        this.total = Double.parseDouble(total);
        return this;
    }

    public TravelInsuranceBuilder setCoverageDescription(String coverageDescription) {
        this.coverageDescription = coverageDescription;
        return this;
    }

    public TravelInsurance build(){
        if (dateOfIssue == null) {
            dateOfIssue = LocalDate.now();
        }
        if(insuranceID == 0){
            return new TravelInsurance(
                    registeredTo,
                    annualPremium,
                    total,
                    coverageDescription,
                    maxCoverage,
                    isPremium,
                    dateOfIssue);
        }
        else{
            return new TravelInsurance(
                    registeredTo,
                    annualPremium,
                    total,
                    coverageDescription,
                    maxCoverage,
                    isPremium,
                    dateOfIssue,
                    insuranceID);
        }

    }
}
