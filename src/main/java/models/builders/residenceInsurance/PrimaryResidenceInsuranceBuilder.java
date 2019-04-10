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

    public PrimaryResidenceInsuranceBuilder setPropertyInsuranceAmount(String propertyInsuranceAmount) {
        this.propertyInsuranceAmount = Double.parseDouble(propertyInsuranceAmount);
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setAssetsInsuranceAmount(String assetsInsuranceAmount) {
        this.assetsInsuranceAmount = Double.parseDouble(assetsInsuranceAmount);
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setRegisteredTo(String registeredTo) {
        this.registeredTo = Integer.parseInt(registeredTo);
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setAnnualPremium(String annualPremium) {
        this.annualPremium = Double.parseDouble(annualPremium);
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setTotal(String total) {
        this.total = Double.parseDouble(total);
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

