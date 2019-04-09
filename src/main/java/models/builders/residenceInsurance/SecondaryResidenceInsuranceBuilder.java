package models.builders.residenceInsurance;

import models.insurance.residenceInsurance.Residence;
import models.insurance.residenceInsurance.SecondaryResidenceInsurance;

public class SecondaryResidenceInsuranceBuilder{
    private Residence residence;
    private double propertyInsuranceAmount; // forsikringsbeløp for bygning
    private double assetsInsuranceAmount; // forsikringsbeløp for innbo

    //Felt fra Insurance
    private int registeredTo;
    private double annualPremium;
    private double total; // TODO: forsikringsbeløp, kanskje annet navn + hva er det forno?
    private String coverageDescription; // forsikringsbetingelser, ev. annet navn.

    public SecondaryResidenceInsuranceBuilder setResidence(Residence residence) {
        this.residence = residence;
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setPropertyInsuranceAmount(double propertyInsuranceAmount) {
        this.propertyInsuranceAmount = propertyInsuranceAmount;
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setAssetsInsuranceAmount(double assetsInsuranceAmount) {
        this.assetsInsuranceAmount = assetsInsuranceAmount;
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setRegisteredTo(int registeredTo) {
        this.registeredTo = registeredTo;
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setAnnualPremium(double annualPremium) {
        this.annualPremium = annualPremium;
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setTotal(double total) {
        this.total = total;
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setCoverageDescription(String coverageDescription) {
        this.coverageDescription = coverageDescription;
        return this;
    }

    public SecondaryResidenceInsurance build(){
        return new SecondaryResidenceInsurance(
                registeredTo,
                annualPremium,
                total,
                coverageDescription,
                residence,
                propertyInsuranceAmount,
                assetsInsuranceAmount);
    }
}
