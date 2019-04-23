package models.builders.boatInsurance;

import models.builders.StringChecker;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.builderExceptions.EmptyFieldException;
import models.exceptions.builderExceptions.InvalidDateFormatException;
import models.exceptions.builderExceptions.NotANumberException;
import models.exceptions.customerExceptions.EmptyFieldsException;
import models.insurance.boatInsurance.Boat;
import models.insurance.boatInsurance.BoatInsurance;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.zip.DataFormatException;

public class BoatInsuranceBuilderMedExceptions {

    private int registeredTo;
    private double annualPremium;
    private double total; // TODO: forsikringsbeløp, kanskje annet navn + hva er det forno?
    private String coverageDescription; // forsikringsbetingelser, ev. annet navn.
    private LocalDate dateOfIssue = null;
    private int insuranceID;

    private Boat boat;
    private StringChecker stringChecker = new StringChecker();


    public BoatInsuranceBuilderMedExceptions setRegisteredTo(String registeredTo) throws BuilderInputException {
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

    public BoatInsuranceBuilderMedExceptions setAnnualPremium(String annualPremium) throws BuilderInputException {
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

    public BoatInsuranceBuilderMedExceptions setDateOfIssue(String dateOfIssue) throws BuilderInputException {
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




    public BoatInsuranceBuilderMedExceptions setTotal(String total) throws BuilderInputException {
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

    public BoatInsuranceBuilderMedExceptions setCoverageDescription(String coverageDescription) throws BuilderInputException {
        String fieldName = "Forsikringsbetingelser";

        if(stringChecker.isEmptyOrNull(coverageDescription)){
            throw new EmptyFieldException(fieldName);
        }

        this.coverageDescription = coverageDescription;
        return this;
    }

    public BoatInsuranceBuilderMedExceptions setInsuranceID(int insuranceID) throws BuilderInputException {
        if(stringChecker.isEmptyOrNull(String.valueOf(insuranceID))){
            throw new EmptyFieldException("Forsikrings ID");
        }
        this.insuranceID = insuranceID;
        return this;
    }

    public BoatInsuranceBuilderMedExceptions setInsuranceID(String insuranceID) throws BuilderInputException {
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

    public BoatInsuranceBuilderMedExceptions setBoat(Boat boat) {
        this.boat = boat;
        return this;
    }

    public BoatInsurance build() {
        if (dateOfIssue == null) {
            dateOfIssue = LocalDate.now();
        }
        if (insuranceID == 0) {
            return new BoatInsurance(
                    registeredTo,
                    annualPremium,
                    total,
                    coverageDescription,
                    boat,
                    dateOfIssue
            );
        }
        else {
            return new BoatInsurance(
                    registeredTo,
                    annualPremium,
                    total,
                    coverageDescription,
                    boat,
                    dateOfIssue,
                    insuranceID
            );
        }
    }

}

