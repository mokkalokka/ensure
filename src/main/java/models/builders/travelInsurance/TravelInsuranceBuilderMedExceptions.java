package models.builders.travelInsurance;

import models.builders.StringChecker;
import models.builders.boatInsurance.BoatInsuranceBuilderMedExceptions;
import models.exceptions.builderExceptions.*;
import models.travelInsurance.TravelInsurance;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TravelInsuranceBuilderMedExceptions {
    private boolean isPremium;
    private double maxCoverage; // forsikringssum på norsk

    //Fra superklassen Insurance
    private int registeredTo;
    private double annualPremium;
    private LocalDate dateOfIssue = null;
    private double total; // TODO: forsikringsbeløp, kanskje annet navn + hva er det forno?
    private String coverageDescription; // forsikringsbetingelser, ev. annet navn.
    private int insuranceID;
    private StringChecker stringChecker = new StringChecker();

    public TravelInsuranceBuilderMedExceptions setRegisteredTo(String registeredTo) throws BuilderInputException {
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

    public TravelInsuranceBuilderMedExceptions setAnnualPremium(String annualPremium) throws BuilderInputException {
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

    public TravelInsuranceBuilderMedExceptions setDateOfIssue(String dateOfIssue) throws BuilderInputException {
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




    public TravelInsuranceBuilderMedExceptions setTotal(String total) throws BuilderInputException {
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

    public TravelInsuranceBuilderMedExceptions setCoverageDescription(String coverageDescription) throws BuilderInputException {
        String fieldName = "Forsikringsbetingelser";

        if(stringChecker.isEmptyOrNull(coverageDescription)){
            throw new EmptyFieldException(fieldName);
        }

        this.coverageDescription = coverageDescription;
        return this;
    }

    public TravelInsuranceBuilderMedExceptions setInsuranceID(int insuranceID) throws BuilderInputException {
        if(stringChecker.isEmptyOrNull(String.valueOf(insuranceID))){
            throw new EmptyFieldException("Forsikrings ID");
        }
        this.insuranceID = insuranceID;
        return this;
    }

    public TravelInsuranceBuilderMedExceptions setInsuranceID(String insuranceID) throws BuilderInputException {
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


    public TravelInsuranceBuilderMedExceptions setPremium(String isPremium) throws BuilderInputException {
        String fieldName = "Premium";

        if(stringChecker.isEmptyOrNull(isPremium)){
            throw new EmptyFieldException(fieldName);
        }
        else if(!stringChecker.validBooleanString(isPremium)){
            throw new InvalidBooleanStringFormatException();
        }

        this.isPremium = Boolean.parseBoolean(isPremium);
        return this;
    }

    public TravelInsuranceBuilderMedExceptions setPremium(boolean isPremium)  {
        this.isPremium = isPremium;
        return this;
    }

    public TravelInsuranceBuilderMedExceptions setMaxCoverage(String maxCoverage) throws BuilderInputException {
        String fieldName = "Forsikringssum";

        if(stringChecker.isEmptyOrNull(maxCoverage)){
            throw new EmptyFieldException(fieldName);
        }
        try{

            this.maxCoverage = Double.parseDouble(maxCoverage);
        }
        catch (NumberFormatException e){
            throw new NotANumberException(fieldName);
        }

        return this;
    }

    public TravelInsurance build(){
        if (dateOfIssue == null) {
            dateOfIssue = LocalDate.now();
        }
        if(insuranceID == 0){
            return new TravelInsurance(
                    registeredTo,
                    annualPremium,
                    total,
                    coverageDescription,
                    maxCoverage,
                    isPremium,
                    dateOfIssue);
        }
        else{
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
