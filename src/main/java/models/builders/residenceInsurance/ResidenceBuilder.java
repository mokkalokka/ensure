package models.builders.residenceInsurance;

import models.builders.StringChecker;
import models.exceptions.builderExceptions.*;
import models.insurance.residenceInsurance.Residence;

import java.time.DateTimeException;
import java.time.Year;

public class ResidenceBuilder {
    private String address; // fritidsbolig skal ha forskjellig adresse fra Customer.invoiceAdress
    private Year yearOfConstruction;
    private String type;
    private String constructionMaterial;
    private String condition;
    private int sqMeters;
    private final StringChecker stringChecker = new StringChecker();

    public ResidenceBuilder setYearOfConstruction(String yearOfConstruction) throws BuilderInputException {
        String fieldName = "Byggeår";
        if(stringChecker.isEmptyOrNull(yearOfConstruction)){
            throw new EmptyFieldException(fieldName);
        }
        try{
            int yearOfConstructionInt = Integer.parseInt(yearOfConstruction);
            this.yearOfConstruction = Year.parse(yearOfConstruction);
            if(stringChecker.validYear(yearOfConstructionInt)){
                throw new InvalidYearException(fieldName);
            }
        }
        catch (NumberFormatException e){
            throw new InvalidPositiveIntegerException(fieldName);
        }
        catch (DateTimeException e){
            throw new InvalidYearException(fieldName);
        }
        return this;
    }

    public ResidenceBuilder setType(String type) throws BuilderInputException  {
        String fieldName = "Type";
        if(stringChecker.isEmptyOrNull(type)){
            throw new EmptyFieldException(fieldName);
        }
        this.type = type;
        return this;
    }

    public ResidenceBuilder setConstructionMaterial(String constructionMaterial) throws BuilderInputException  {
        String fieldName = "Byggmatriale";
        if(stringChecker.isEmptyOrNull(constructionMaterial)){
            throw new EmptyFieldException(fieldName);
        }
        this.constructionMaterial = constructionMaterial;
        return this;
    }

    public ResidenceBuilder setCondition(String condition) throws BuilderInputException  {
        String fieldName = "Betingelser";
        if(stringChecker.isEmptyOrNull(condition)){
            throw new EmptyFieldException(fieldName);
        }
        this.condition = condition;
        return this;
    }

    public ResidenceBuilder setSqMeters(String sqMeters) throws BuilderInputException  {
        String fieldName = "Kvadratmeter";
        if(stringChecker.isEmptyOrNull(sqMeters)){
            throw new EmptyFieldException(fieldName);
        }
        try{
            this.sqMeters = Integer.parseInt(sqMeters);
            if(stringChecker.isNegative(sqMeters)){
                throw new InvalidPositiveIntegerException(fieldName);
            }
        }
        catch (NumberFormatException e){
            throw new InvalidPositiveIntegerException(fieldName);
        }
        return this;
    }

    public ResidenceBuilder setAddress(String address) throws BuilderInputException  {
        String fieldName = "Adresse";
        if(stringChecker.isEmptyOrNull(address)){
            throw new EmptyFieldException(fieldName);
        }
        this.address = address;
        return this;
    }

    public Residence build(){
        return new Residence(
                address,
                yearOfConstruction,
                type,
                constructionMaterial,
                condition,
                sqMeters);
    }
}
