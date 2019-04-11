package models.insurance.residenceInsurance;

import models.customer.Customer;
import models.insurance.Insurance;

import java.time.LocalDate;

public class SecondaryResidenceInsurance extends Insurance {
    private final String insuranceName = "Fritidsboligforsikringer"; //For CSV writer (Skille mellom classer ved lesing)
    private Residence residence;
    private double propertyInsuranceAmount; // forsikringsbeløp for bygning
    private double assetsInsuranceAmount; // forsikringsbeløp for innbo

    public SecondaryResidenceInsurance(int registeredTo, double annualPremium, double total, String coverageDescription,
                                       Residence residence, double propertyInsuranceAmount, double assetsInsuranceAmount,
                                       LocalDate dateOfIssue) {
        super(registeredTo, annualPremium, total, coverageDescription,dateOfIssue );
        this.residence = residence;
        this.propertyInsuranceAmount = propertyInsuranceAmount;
        this.assetsInsuranceAmount = assetsInsuranceAmount;
    }

    @Override
    public String getInsuranceName() {
        return null;
    }
}
