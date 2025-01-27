package models.insurance.residenceInsurance;

import java.time.LocalDate;

public class SecondaryResidenceInsurance extends ResidenceInsurance {

    public static final String nameOfClass = "Fritidsboligforsikring"; //For CSV writer (Skille mellom classer ved lesing)

    public SecondaryResidenceInsurance(int registeredTo, double annualPremium, double total, String coverageDescription, Residence residence, double propertyInsuranceAmount, double assetsInsuranceAmount, LocalDate dateOfIssue) {
        super(registeredTo, annualPremium, total, coverageDescription, residence, propertyInsuranceAmount, assetsInsuranceAmount, dateOfIssue);
    }

    public SecondaryResidenceInsurance(int registeredTo, double annualPremium, double total, String coverageDescription, Residence residence, double propertyInsuranceAmount, double assetsInsuranceAmount, LocalDate dateOfIssue, int insuranceID) {
        super(registeredTo, annualPremium, total, coverageDescription, residence, propertyInsuranceAmount, assetsInsuranceAmount, dateOfIssue, insuranceID);
    }

    //---------- CSVWritable metoder -----------

    @Override
    public String getNameOfClass() {
        return nameOfClass;
    }

    @Override
    public int getWriteIndex() {
        return 3;
    }
}
