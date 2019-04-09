package models.travelInsurance;

import models.customer.Customer;
import models.insurance.Insurance;

import java.util.ArrayList;
import java.util.Arrays;

public class TravelInsurance extends Insurance {

    private enum CoverageType {
            STANDARD, PREMIUM
    }
    private CoverageType coverageType; // TODO: -> enum Det her må nok være et annet datafelt. HashMap? tvunget til å velge 1?
    private double maxCoverage; // forsikringssum på norsk

    public TravelInsurance(Customer customer, double annualPremium, double total, String coverageDescription, double maxCoverage) {
        super(customer, annualPremium, total, coverageDescription);
        this.coverageType = CoverageType.STANDARD;
        this.maxCoverage = maxCoverage;
    }

    // Overloaded constructor om man ønsker premium type reiseforsikring.
    public TravelInsurance(Customer customer, double annualPremium, double total, String coverageDescription, double maxCoverage, CoverageType coverageType) {
        super(customer, annualPremium, total, coverageDescription);
        this.coverageType = coverageType;
        this.maxCoverage = maxCoverage;
    }

    public ArrayList<String> getFieldNamesAsStrings() {
        ArrayList<String> fieldNames = new ArrayList<>(
                Arrays.asList("Forsikringsområde", "Forsikringssum"));
        fieldNames.addAll(0, super.getFieldNamesAsStrings());
        return fieldNames;
    }

    public ArrayList<String> getFieldValuesAsStrings() {
        ArrayList<String> fieldValues = new ArrayList<>(
                Arrays.asList(
                        String.valueOf(coverageType),
                        String.valueOf(maxCoverage)
                ));
        fieldValues.addAll(0, super.getFieldValuesAsStrings());
        return fieldValues;
    }

    public double getMaxCoverage() {
        return maxCoverage;
    }

    public CoverageType getCoverageType() {
        return coverageType;
    }

}
