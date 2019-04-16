package models.builders.residenceInsurance;

import models.insurance.residenceInsurance.PrimaryResidenceInsurance;
import models.insurance.residenceInsurance.Residence;

import java.time.LocalDate;

public class PrimaryResidenceInsuranceBuilder{
    private Residence residence;
    private double propertyInsuranceAmount; // forsikringsbeløp for bygning
    private double assetsInsuranceAmount; // forsikringsbeløp for innbo
    private int registeredTo;
    private double annualPremium;
    private double total; // TODO: forsikringsbeløp, kanskje annet navn + hva er det forno?
    private String coverageDescription; // forsikringsbetingelser, ev. annet navn.
    private LocalDate dateOfIssue = null;
    private int insuranceID;

    public PrimaryResidenceInsuranceBuilder setInsuranceID(String insuranceID) {
        this.insuranceID = Integer.parseInt(insuranceID);
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = LocalDate.parse(dateOfIssue);
        return this;
    }

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
        if (dateOfIssue == null) {
            dateOfIssue = LocalDate.now();
        }
        if(insuranceID == 0){
            return new PrimaryResidenceInsurance(
                    registeredTo,
                    annualPremium,
                    total,
                    coverageDescription,
                    residence,
                    propertyInsuranceAmount,
                    assetsInsuranceAmount,
                    dateOfIssue);
        }
        else{
            return new PrimaryResidenceInsurance(
                    registeredTo,
                    annualPremium,
                    total,
                    coverageDescription,
                    residence,
                    propertyInsuranceAmount,
                    assetsInsuranceAmount,
                    dateOfIssue,
                    insuranceID);

        }
    }

}


