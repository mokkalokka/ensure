package models.builders.residenceInsurance;

import models.insurance.residenceInsurance.PrimaryResidenceInsurance;
import models.insurance.residenceInsurance.Residence;

public class PrimaryResidenceInsuranceBuilder{
    private Residence residence;
    private double propertyInsuranceAmount; // forsikringsbeløp for bygning
    private double assetsInsuranceAmount; // forsikringsbeløp for innbo
    private int registeredTo;
    private double annualPremium;
    private double total; // TODO: forsikringsbeløp, kanskje annet navn + hva er det forno?
    private String coverageDescription; // forsikringsbetingelser, ev. annet navn.

    public PrimaryResidenceInsuranceBuilder setResidence(Residence residence) {
        this.residence = residence;
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setPropertyInsuranceAmount(double propertyInsuranceAmount) {
        this.propertyInsuranceAmount = propertyInsuranceAmount;
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setAssetsInsuranceAmount(double assetsInsuranceAmount) {
        this.assetsInsuranceAmount = assetsInsuranceAmount;
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setRegisteredTo(int registeredTo) {
        this.registeredTo = registeredTo;
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setAnnualPremium(double annualPremium) {
        this.annualPremium = annualPremium;
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setTotal(double total) {
        this.total = total;
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setCoverageDescription(String coverageDescription) {
        this.coverageDescription = coverageDescription;
        return this;
    }

    public PrimaryResidenceInsurance build(){
        return new PrimaryResidenceInsurance(
                registeredTo,
                annualPremium,
                total,
                coverageDescription,
                residence,
                propertyInsuranceAmount,
                assetsInsuranceAmount);
    }
}

