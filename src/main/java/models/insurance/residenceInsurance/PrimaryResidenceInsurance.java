package models.insurance.residenceInsurance;

import java.time.LocalDate;

public class PrimaryResidenceInsurance extends ResidenceInsurance {

    public static final String nameOfClass = "Hus- og innboforsikring"; //For CSV writer (Skille mellom classer ved lesing)

    public PrimaryResidenceInsurance(int registeredTo, double annualPremium, double total, String coverageDescription, Residence residence, double propertyInsuranceAmount, double assetsInsuranceAmount, LocalDate dateOfIssue) {
        super(registeredTo, annualPremium, total, coverageDescription, residence, propertyInsuranceAmount, assetsInsuranceAmount, dateOfIssue);
    }

    public PrimaryResidenceInsurance(int registeredTo, double annualPremium, double total, String coverageDescription, Residence residence, double propertyInsuranceAmount, double assetsInsuranceAmount, LocalDate dateOfIssue, int insuranceID) {
        super(registeredTo, annualPremium, total, coverageDescription, residence, propertyInsuranceAmount, assetsInsuranceAmount, dateOfIssue, insuranceID);
    }

    //---------- CSVWritable metoder -----------

    public String getNameOfClass() {
        return nameOfClass;
    }

    @Override
    public int getWriteIndex() {
        return 2;
    }
}
