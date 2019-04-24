package models.builders;

import models.builders.boatInsurance.BoatInsuranceBuilderMedExceptions;
import models.builders.residenceInsurance.PrimaryResidenceInsuranceBuilderMedExceptions;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.builderExceptions.EmptyFieldException;
import models.exceptions.builderExceptions.InvalidDateFormatException;
import models.exceptions.builderExceptions.NotANumberException;
import models.insurance.Insurance;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class InsuranceBuilder {
    protected int registeredTo;
    protected double annualPremium;
    protected double total; // TODO: forsikringsbeløp, kanskje annet navn + hva er det forno?
    protected String coverageDescription; // forsikringsbetingelser, ev. annet navn.
    protected LocalDate dateOfIssue = null;
    protected int insuranceID;


    private final StringChecker stringChecker = new StringChecker();

    public InsuranceBuilder setRegisteredTo(String registeredTo) throws BuilderInputException {
        String fieldName = "Registrert til";

        if (stringChecker.isEmptyOrNull(registeredTo)) {
            throw new EmptyFieldException(fieldName);
        }

        try {
            this.registeredTo = Integer.parseInt(registeredTo);
        } catch (NumberFormatException e) {
            throw new NotANumberException(fieldName);
        }

        return this;
    }


    public InsuranceBuilder setAnnualPremium(String annualPremium) throws BuilderInputException {
        String fieldName = "Årlig forsikringspremie";

        if (stringChecker.isEmptyOrNull(annualPremium)) {
            throw new EmptyFieldException(fieldName);
        }

        try {
            this.annualPremium = Double.parseDouble(annualPremium);
        } catch (NumberFormatException e) {
            throw new NotANumberException(fieldName);
        }
        return this;
    }

    public InsuranceBuilder setDateOfIssue(String dateOfIssue) throws BuilderInputException {
        String fieldName = "Dato opprettet";

        if (stringChecker.isEmptyOrNull(dateOfIssue)) {
            throw new EmptyFieldException(fieldName);
        }
        try {
            this.dateOfIssue = LocalDate.parse(dateOfIssue);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(fieldName);
        }
        return this;
    }


    public InsuranceBuilder setTotal(String total) throws BuilderInputException {
        String fieldName = "Forsikringsbeløp";

        if (stringChecker.isEmptyOrNull(total)) {
            throw new EmptyFieldException(fieldName);
        }

        try {
            this.total = Double.parseDouble(total);
        } catch (NumberFormatException e) {
            throw new NotANumberException(fieldName);
        }

        return this;
    }

    public InsuranceBuilder setCoverageDescription(String coverageDescription) throws BuilderInputException {
        String fieldName = "Forsikringsbetingelser";

        if (stringChecker.isEmptyOrNull(coverageDescription)) {
            throw new EmptyFieldException(fieldName);
        }

        this.coverageDescription = coverageDescription;
        return this;
    }

    public InsuranceBuilder setInsuranceID(int insuranceID) throws BuilderInputException {
        if (stringChecker.isEmptyOrNull(String.valueOf(insuranceID))) {
            throw new EmptyFieldException("Forsikrings ID");
        }
        this.insuranceID = insuranceID;
        return this;
    }

    public InsuranceBuilder setInsuranceID(String insuranceID) throws BuilderInputException {
        String fieldName = "Forsikrings ID";

        if (stringChecker.isEmptyOrNull(insuranceID)) {
            throw new EmptyFieldException(fieldName);
        }
        try {
            this.insuranceID = Integer.parseInt(insuranceID);
        } catch (NumberFormatException e) {
            throw new NotANumberException(fieldName);
        }
        return this;
    }

    public abstract Insurance build();
}

