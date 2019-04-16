package models.insurance.residenceInsurance;

import models.customer.Customer;
import models.insurance.Insurance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class PrimaryResidenceInsurance extends Insurance {
    private final String insuranceName = "Husforsikringer"; //For CSV writer (Skille mellom classer ved lesing)
    private Residence residence;
    private double propertyInsuranceAmount; // forsikringsbeløp for bygning
    private double assetsInsuranceAmount; // forsikringsbeløp for innbo

    public PrimaryResidenceInsurance(int registeredTo, double annualPremium, double total, String coverageDescription,
                                     Residence residence, double propertyInsuranceAmount, double assetsInsuranceAmount,
                                     LocalDate dateOfIssue) {
        super(registeredTo, annualPremium, total, coverageDescription, dateOfIssue);
        this.residence = residence;
        this.propertyInsuranceAmount = propertyInsuranceAmount;
        this.assetsInsuranceAmount = assetsInsuranceAmount;
    }

    public PrimaryResidenceInsurance(int registeredTo, double annualPremium, double total, String coverageDescription,
                                     Residence residence, double propertyInsuranceAmount, double assetsInsuranceAmount,
                                     LocalDate dateOfIssue, int insuranceID) {
        super(registeredTo, annualPremium, total, coverageDescription, dateOfIssue, insuranceID);
        this.residence = residence;
        this.propertyInsuranceAmount = propertyInsuranceAmount;
        this.assetsInsuranceAmount = assetsInsuranceAmount;
    }

    public Residence getResidence() {
        return residence;
    }

    public double getPropertyInsuranceAmount() {
        return propertyInsuranceAmount;
    }

    public double getAssetsInsuranceAmount() {
        return assetsInsuranceAmount;
    }

    @Override
    public ArrayList<String> getFieldNamesAsStrings() {
        ArrayList<String> fieldNames = new ArrayList<>(
                Arrays.asList("Forsikringsbeløp bygning", "Forsikringsbeløp innbo", "Adresse", "Byggeår",
                        "Boligtype", "Boligmateriale", "Standard", "Kvadratmeter")
        );
        fieldNames.addAll(0, super.getFieldNamesAsStrings());
        return fieldNames;
    }

    @Override
    public ArrayList<String> getFieldValuesAsStrings() {
        ArrayList<String> fieldValues = new ArrayList<>(
                Arrays.asList(
                String.valueOf(propertyInsuranceAmount),
                String.valueOf(assetsInsuranceAmount),
                residence.getAddress(),
                String.valueOf(residence.getYearOfConstruction()),
                residence.getResidenceType(),
                residence.getConstructionMaterial(),
                residence.getCondition(),
                String.valueOf(residence.getSqMeters())
                )
        );
        fieldValues.addAll(0, super.getFieldValuesAsStrings());
        return fieldValues;
    }

    @Override
    public String getInsuranceName() {
        return insuranceName;
    }
}
