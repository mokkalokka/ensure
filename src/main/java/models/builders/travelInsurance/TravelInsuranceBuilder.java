package models.builders.travelInsurance;

import models.builders.InsuranceBuilder;
import models.builders.StringChecker;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.builderExceptions.EmptyFieldException;
import models.exceptions.builderExceptions.InvalidBooleanStringFormatException;
import models.exceptions.builderExceptions.InvalidPositiveDoubleException;
import models.insurance.travelInsurance.TravelInsurance;

import java.time.LocalDate;

public class TravelInsuranceBuilder extends InsuranceBuilder {
    private boolean isPremium;
    private double maxCoverage; // forsikringssum på norsk
    private StringChecker stringChecker = new StringChecker();

    public TravelInsuranceBuilder setRegisteredTo(String registeredTo) throws BuilderInputException {
        super.setRegisteredTo(registeredTo);
        return this;
    }

    public TravelInsuranceBuilder setAnnualPremium(String annualPremium) throws BuilderInputException {
        super.setAnnualPremium(annualPremium);
        return this;
    }

    public TravelInsuranceBuilder setDateOfIssue(String dateOfIssue) throws BuilderInputException {
        super.setDateOfIssue(dateOfIssue);
        return this;
    }

    public TravelInsuranceBuilder setTotal(String total) throws BuilderInputException {
        super.setTotal(total);
        return this;
    }

    public TravelInsuranceBuilder setCoverageDescription(String coverageDescription) throws BuilderInputException {
        super.setCoverageDescription(coverageDescription);
        return this;
    }

    public TravelInsuranceBuilder setInsuranceID(int insuranceID) throws BuilderInputException {
        super.setInsuranceID(insuranceID);
        return this;
    }

    public TravelInsuranceBuilder setInsuranceID(String insuranceID) throws BuilderInputException {
        super.setInsuranceID(insuranceID);
        return this;
    }


    public TravelInsuranceBuilder setPremium(String isPremium) throws BuilderInputException {
        String fieldName = "Premium";

        if (stringChecker.isEmptyOrNull(isPremium)) {
            throw new EmptyFieldException(fieldName);
        } else if (!stringChecker.validBooleanString(isPremium)) {
            throw new InvalidBooleanStringFormatException();
        }

        this.isPremium = Boolean.parseBoolean(isPremium);
        return this;
    }

    public TravelInsuranceBuilder setPremium(boolean isPremium) {
        this.isPremium = isPremium;
        return this;
    }

    public TravelInsuranceBuilder setMaxCoverage(String maxCoverage) throws BuilderInputException {
        String fieldName = "Forsikringssum";

        if (stringChecker.isEmptyOrNull(maxCoverage)) {
            throw new EmptyFieldException(fieldName);
        }
        try {
            this.maxCoverage = Double.parseDouble(maxCoverage);
            if (stringChecker.isNegative(maxCoverage)) {
                throw new InvalidPositiveDoubleException(fieldName + ": " + maxCoverage + ",");
            }
        } catch (NumberFormatException e) {
            throw new InvalidPositiveDoubleException(fieldName + ": " + maxCoverage + ",");
        }

        return this;
    }

    public TravelInsurance build() {
        if (dateOfIssue == null) {
            dateOfIssue = LocalDate.now();
        }
        if (insuranceID == 0) {
            return new TravelInsurance(
                    registeredTo,
                    annualPremium,
                    total,
                    coverageDescription,
                    maxCoverage,
                    isPremium,
                    dateOfIssue);
        } else {
            return new TravelInsurance(
                    registeredTo,
                    annualPremium,
                    total,
                    coverageDescription,
                    maxCoverage,
                    isPremium,
                    dateOfIssue,
                    insuranceID);
        }

    }
}
