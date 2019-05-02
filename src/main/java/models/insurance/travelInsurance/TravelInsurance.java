package models.insurance.travelInsurance;

import models.insurance.Insurance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class TravelInsurance extends Insurance {

    public static final String nameOfClass = "Reiseforsikring"; //For CSV writer (Skille mellom classer ved lesing)

    private boolean isPremium;
    private double maxCoverage; // forsikringssum på norsk

    public TravelInsurance(int registeredTo, double annualPremium, double total, String coverageDescription,
                           double maxCoverage, boolean isPremium, LocalDate dateOfIssue) {
        super(registeredTo, annualPremium, total, coverageDescription, dateOfIssue);
        //this.coverageType = CoverageType.STANDARD;
        this.isPremium = isPremium;
        this.maxCoverage = maxCoverage;
    }

    public TravelInsurance(int registeredTo, double annualPremium, double total, String coverageDescription,
                           double maxCoverage, boolean isPremium, LocalDate dateOfIssue, int insuranceID) {
        super(registeredTo, annualPremium, total, coverageDescription, dateOfIssue, insuranceID);
        //this.coverageType = CoverageType.STANDARD;
        this.isPremium = isPremium;
        this.maxCoverage = maxCoverage;
    }

    public double getMaxCoverage() {
        return maxCoverage;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public void setMaxCoverage(double maxCoverage) {
        this.maxCoverage = maxCoverage;
    }

    //---------- CSVWritable metoder -----------

    @Override
    public ArrayList<String> getFieldNamesAsStrings() {
        ArrayList<String> fieldNames = new ArrayList<>(
                Arrays.asList("Premiumforsikring", "Forsikringssum"));
        fieldNames.addAll(0, super.getFieldNamesAsStrings());
        return fieldNames;
    }

    @Override
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
    public String getNameOfClass() {
        return nameOfClass;
    }

    @Override
    public int getWriteIndex() {
        return 4;
    }
}
