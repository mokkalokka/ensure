package models.builders.residenceInsurance;

import models.builders.StringChecker;
import models.builders.boatInsurance.BoatInsuranceBuilderMedExceptions;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.builderExceptions.EmptyFieldException;
import models.exceptions.builderExceptions.InvalidDateFormatException;
import models.exceptions.builderExceptions.NotANumberException;
import models.insurance.residenceInsurance.PrimaryResidenceInsurance;
import models.insurance.residenceInsurance.Residence;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class PrimaryResidenceInsuranceBuilderMedExceptions {
    private Residence residence;
    private double propertyInsuranceAmount; // forsikringsbeløp for bygning
    private double assetsInsuranceAmount; // forsikringsbeløp for innbo
    private int registeredTo;
    private double annualPremium;
    private double total; // TODO: forsikringsbeløp, kanskje annet navn + hva er det forno?
    private String coverageDescription; // forsikringsbetingelser, ev. annet navn.
    private LocalDate dateOfIssue = null;
    private int insuranceID;

    private StringChecker stringChecker = new StringChecker();


    public PrimaryResidenceInsuranceBuilderMedExceptions setRegisteredTo(String registeredTo) throws BuilderInputException {
        String fieldName = "Registrert til";

        if(stringChecker.isEmptyOrNull(registeredTo)){
            throw new EmptyFieldException(fieldName);
        }

        try{
            this.registeredTo = Integer.parseInt(registeredTo);
        }
        catch (NumberFormatException e){
            throw new NotANumberException(fieldName);
        }

        return this;
    }

    public PrimaryResidenceInsuranceBuilderMedExceptions setAnnualPremium(String annualPremium) throws BuilderInputException {
        String fieldName = "Årlig forsikringspremie";

        if(stringChecker.isEmptyOrNull(annualPremium)){
            throw new EmptyFieldException(fieldName);
        }

        try{
            this.annualPremium = Double.parseDouble(annualPremium);
        }
        catch (NumberFormatException e){
            throw new NotANumberException(fieldName);
        }
        return this;
    }

    public PrimaryResidenceInsuranceBuilderMedExceptions setDateOfIssue(String dateOfIssue) throws BuilderInputException {
        String fieldName = "Dato opprettet";

        if(stringChecker.isEmptyOrNull(dateOfIssue)){
            throw new EmptyFieldException(fieldName);
        }
        try{
            this.dateOfIssue = LocalDate.parse(dateOfIssue);
        }

        catch (DateTimeParseException e){
            throw new InvalidDateFormatException(fieldName);
        }
        return this;
    }




    public PrimaryResidenceInsuranceBuilderMedExceptions setTotal(String total) throws BuilderInputException {
        String fieldName = "Forsikringsbeløp";

        if(stringChecker.isEmptyOrNull(total)){
            throw new EmptyFieldException(fieldName);
        }

        try{
            this.total = Double.parseDouble(total);
        }
        catch (NumberFormatException e){
            throw new NotANumberException(fieldName);
        }

        return this;
    }

    public PrimaryResidenceInsuranceBuilderMedExceptions setCoverageDescription(String coverageDescription) throws BuilderInputException {
        String fieldName = "Forsikringsbetingelser";

        if(stringChecker.isEmptyOrNull(coverageDescription)){
            throw new EmptyFieldException(fieldName);
        }

        this.coverageDescription = coverageDescription;
        return this;
    }

    public PrimaryResidenceInsuranceBuilderMedExceptions setInsuranceID(int insuranceID) throws BuilderInputException {
        if(stringChecker.isEmptyOrNull(String.valueOf(insuranceID))){
            throw new EmptyFieldException("Forsikrings ID");
        }
        this.insuranceID = insuranceID;
        return this;
    }

    public PrimaryResidenceInsuranceBuilderMedExceptions setInsuranceID(String insuranceID) throws BuilderInputException {
        String fieldName = "Forsikrings ID";

        if(stringChecker.isEmptyOrNull(insuranceID)){
            throw new EmptyFieldException(fieldName);
        }
        try{
            this.insuranceID = Integer.parseInt(insuranceID);
        }
        catch (NumberFormatException e){
            throw new NotANumberException(fieldName);
        }
        return this;
    }


    public PrimaryResidenceInsuranceBuilderMedExceptions setResidence(Residence residence) {
        this.residence = residence;
        return this;
    }

    public PrimaryResidenceInsuranceBuilderMedExceptions setPropertyInsuranceAmount(String propertyInsuranceAmount)throws BuilderInputException  {
        String fieldName = "Forsikringsbeløp bygning";

        if(stringChecker.isEmptyOrNull(propertyInsuranceAmount)){
            throw new EmptyFieldException(fieldName);
        }

        try{
        this.propertyInsuranceAmount = Double.parseDouble(propertyInsuranceAmount);
        }
        catch (NumberFormatException e){
            throw new NotANumberException(fieldName);
        }

        return this;
    }

    public PrimaryResidenceInsuranceBuilderMedExceptions setAssetsInsuranceAmount(String assetsInsuranceAmount)throws BuilderInputException {
        String fieldName = "Forsikringsbeløp innbo";

        if(stringChecker.isEmptyOrNull(assetsInsuranceAmount)){
            throw new EmptyFieldException(fieldName);
        }

        try{
        this.assetsInsuranceAmount = Double.parseDouble(assetsInsuranceAmount);
        }
        catch (NumberFormatException e){
            throw new   NotANumberException(fieldName);
        }

        return this;
    }

    public PrimaryResidenceInsurance build(){
        if (dateOfIssue == null) {
            dateOfIssue = LocalDate.now();
        }
        if(insuranceID == 0){
            return new PrimaryResidenceInsurance(
                    registeredTo,
                    annualPremium,
                    total,
                    coverageDescription,
                    residence,
                    propertyInsuranceAmount,
                    assetsInsuranceAmount,
                    dateOfIssue);
        }
        else{
            return new PrimaryResidenceInsurance(
                    registeredTo,
                    annualPremium,
                    total,
                    coverageDescription,
                    residence,
                    propertyInsuranceAmount,
                    assetsInsuranceAmount,
                    dateOfIssue,
                    insuranceID);

        }
    }

}


