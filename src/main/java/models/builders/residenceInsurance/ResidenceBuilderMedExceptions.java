package models.builders.residenceInsurance;

import models.builders.StringChecker;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.builderExceptions.EmptyFieldException;
import models.exceptions.builderExceptions.InvalidYearException;
import models.exceptions.builderExceptions.NotANumberException;
import models.insurance.residenceInsurance.Residence;

import java.time.DateTimeException;
import java.time.Year;

public class ResidenceBuilderMedExceptions {
    private String address; // fritidsbolig skal ha forskjellig adresse fra Customer.invoiceAdress
    private Year yearOfConstruction;
    private String type;
    private String constructionMaterial;
    private String condition;
    private int sqMeters;
    private final StringChecker stringChecker = new StringChecker();

    public ResidenceBuilderMedExceptions setYearOfConstruction(String yearOfConstruction) throws BuilderInputException {
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
            throw new NotANumberException(fieldName);
        }
        catch (DateTimeException e){
            throw new InvalidYearException(fieldName);
        }
        return this;
    }

    public ResidenceBuilderMedExceptions setType(String type) throws BuilderInputException  {
        String fieldName = "Type";
        if(stringChecker.isEmptyOrNull(type)){
            throw new EmptyFieldException(fieldName);
        }
        this.type = type;
        return this;
    }

    public ResidenceBuilderMedExceptions setConstructionMaterial(String constructionMaterial) throws BuilderInputException  {
        String fieldName = "Byggmatriale";
        if(stringChecker.isEmptyOrNull(constructionMaterial)){
            throw new EmptyFieldException(fieldName);
        }
        this.constructionMaterial = constructionMaterial;
        return this;
    }

    public ResidenceBuilderMedExceptions setCondition(String condition) throws BuilderInputException  {
        String fieldName = "Betingelser";
        if(stringChecker.isEmptyOrNull(condition)){
            throw new EmptyFieldException(fieldName);
        }
        this.condition = condition;
        return this;
    }

    public ResidenceBuilderMedExceptions setSqMeters(String sqMeters) throws BuilderInputException  {
        String fieldName = "Kvadratmeter";
        if(stringChecker.isEmptyOrNull(sqMeters)){
            throw new EmptyFieldException(fieldName);
        }
        try{
            this.sqMeters = Integer.parseInt(sqMeters);
        }
        catch (NumberFormatException e){
            throw new NotANumberException(fieldName);
        }
        return this;
    }

    public ResidenceBuilderMedExceptions setAddress(String address) throws BuilderInputException  {
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
