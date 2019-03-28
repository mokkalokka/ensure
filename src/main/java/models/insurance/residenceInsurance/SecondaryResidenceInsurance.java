package models.insurance.residenceInsurance;

import models.insurance.Insurance;

public class SecondaryResidenceInsurance extends Insurance {
    private Residence residence;
    private double propertyInsuranceAmount; // forsikringsbeløp for bygning
    private double assetsInsuranceAmount; // forsikringsbeløp for innbo

    public SecondaryResidenceInsurance(double annualPremium, double total, String coverageDescription, Residence residence, double propertyInsuranceAmount, double assetsInsuranceAmount) {
        super(annualPremium, total, coverageDescription);
        this.residence = residence;
        this.propertyInsuranceAmount = propertyInsuranceAmount;
        this.assetsInsuranceAmount = assetsInsuranceAmount;
    }
}
