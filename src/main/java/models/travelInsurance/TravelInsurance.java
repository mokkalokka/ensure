package models.travelInsurance;

import models.customer.Customer;
import models.insurance.Insurance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class TravelInsurance extends Insurance {
    private final String insuranceName = "Reiseforsikringer"; //For CSV writer (Skille mellom classer ved lesing)
    /*
    private enum CoverageType {
            STANDARD, PREMIUM
    }
    */
    private boolean isPremium;
    //private CoverageType coverageType; // TODO: -> enum Det her må nok være et annet datafelt. HashMap? tvunget til å velge 1?
    private double maxCoverage; // forsikringssum på norsk

    public TravelInsurance(int registeredTo, double annualPremium, double total, String coverageDescription,
                           double maxCoverage, boolean isPremium, LocalDate dateOfIssue ) {
        super(registeredTo, annualPremium, total, coverageDescription, dateOfIssue);
        //this.coverageType = CoverageType.STANDARD;
        this.isPremium = isPremium;
        this.maxCoverage = maxCoverage;
    }

    public TravelInsurance(int registeredTo, double annualPremium, double total, String coverageDescription,
                           double maxCoverage, boolean isPremium, LocalDate dateOfIssue, int insuranceID ) {
        super(registeredTo, annualPremium, total, coverageDescription, dateOfIssue, insuranceID);
        //this.coverageType = CoverageType.STANDARD;
        this.isPremium = isPremium;
        this.maxCoverage = maxCoverage;
    }

    public ArrayList<String> getFieldNamesAsStrings() {
        ArrayList<String> fieldNames = new ArrayList<>(
                Arrays.asList("Premiumforsikring", "Forsikringssum"));
        fieldNames.addAll(0, super.getFieldNamesAsStrings());
        return fieldNames;
    }

    public ArrayList<String> getFieldValuesAsStrings() {
        ArrayList<String> fieldValues = new ArrayList<>(
                Arrays.asList(
                        String.valueOf(isPremium),
                        String.valueOf(maxCoverage)
                ));
        fieldValues.addAll(0, super.getFieldValuesAsStrings());
        return fieldValues;
    }

    @Override
    public String getInsuranceName() {
        return insuranceName;
    }

    public double getMaxCoverage() {
        return maxCoverage;
    }

    /*
    public CoverageType getCoverageType() {
        return coverageType;
    }
    */

    public boolean isPremium() {
        return isPremium;
    }
}
