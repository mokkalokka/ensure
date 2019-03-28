package models.insurance.residenceInsurance;

import models.insurance.Insurance;
import models.insurance.residenceInsurance.Residence;

public class PrimaryResidenceInsurance extends Insurance {
    private Residence residence;
    private double propertyInsuranceAmount; // forsikringsbeløp for bygning
    private double assetsInsuranceAmount; // forsikringsbeløp for innbo

    public PrimaryResidenceInsurance(double annualPremium, double total, String coverageDescription, Residence residence) {
        super(annualPremium, total, coverageDescription);
        this.residence = residence;
    }
}
