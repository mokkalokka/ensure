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

    public SecondaryResidenceInsuranceBuilder setPropertyInsuranceAmount(String propertyInsuranceAmount) {
        this.propertyInsuranceAmount = Double.parseDouble(propertyInsuranceAmount);
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setAssetsInsuranceAmount(String assetsInsuranceAmount) {
        this.assetsInsuranceAmount = Double.parseDouble(assetsInsuranceAmount);
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setRegisteredTo(String registeredTo) {
        this.registeredTo = Integer.parseInt(registeredTo);
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setAnnualPremium(String annualPremium) {
        this.annualPremium = Double.parseDouble(annualPremium);
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setTotal(String total) {
        this.total = Double.parseDouble(total);
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
