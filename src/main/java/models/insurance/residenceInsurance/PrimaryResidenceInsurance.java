package models.insurance.residenceInsurance;

import java.time.LocalDate;

public class PrimaryResidenceInsurance extends ResidenceInsurance {

    public static final String insuranceName = "Hus- og innboforsikring"; //For CSV writer (Skille mellom classer ved lesing)

    public PrimaryResidenceInsurance(int registeredTo, double annualPremium, double total, String coverageDescription, Residence residence, double propertyInsuranceAmount, double assetsInsuranceAmount, LocalDate dateOfIssue) {
        super(registeredTo, annualPremium, total, coverageDescription, residence, propertyInsuranceAmount, assetsInsuranceAmount, dateOfIssue);
    }

    public PrimaryResidenceInsurance(int registeredTo, double annualPremium, double total, String coverageDescription, Residence residence, double propertyInsuranceAmount, double assetsInsuranceAmount, LocalDate dateOfIssue, int insuranceID) {
        super(registeredTo, annualPremium, total, coverageDescription, residence, propertyInsuranceAmount, assetsInsuranceAmount, dateOfIssue, insuranceID);
    }


    public String getInsuranceName() {
        return insuranceName;
    }
}
