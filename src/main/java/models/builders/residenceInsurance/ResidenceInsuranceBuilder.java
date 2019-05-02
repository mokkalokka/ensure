package models.builders.residenceInsurance;

import models.builders.InsuranceBuilder;
import models.builders.StringChecker;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.builderExceptions.EmptyFieldException;
import models.exceptions.builderExceptions.InvalidPositiveIntegerException;
import models.insurance.Insurance;


public abstract class ResidenceInsuranceBuilder extends InsuranceBuilder {

    protected double propertyInsuranceAmount; // forsikringsbeløp for bygning
    protected double assetsInsuranceAmount; // forsikringsbeløp for innbo
    private final StringChecker stringChecker = new StringChecker();


    public ResidenceInsuranceBuilder setPropertyInsuranceAmount(String propertyInsuranceAmount)throws BuilderInputException {
        String fieldName = "Forsikringsbeløp bygning";

        if(stringChecker.isEmptyOrNull(propertyInsuranceAmount)){
            throw new EmptyFieldException(fieldName);
        }

        try{
            this.propertyInsuranceAmount = Double.parseDouble(propertyInsuranceAmount);
            if (stringChecker.isNegative(propertyInsuranceAmount)) {
                throw new InvalidPositiveIntegerException(fieldName + ": "+ propertyInsuranceAmount + ",");
            }
        }
        catch (NumberFormatException e){
            throw new InvalidPositiveIntegerException(fieldName + ": "+ propertyInsuranceAmount + ",");
        }

        return this;
    }

    public ResidenceInsuranceBuilder setAssetsInsuranceAmount(String assetsInsuranceAmount)throws BuilderInputException {
        String fieldName = "Forsikringsbeløp innbo";

        if(stringChecker.isEmptyOrNull(assetsInsuranceAmount)){
            throw new EmptyFieldException(fieldName);
        }

        try{
            this.assetsInsuranceAmount = Double.parseDouble(assetsInsuranceAmount);
            if (stringChecker.isNegative(assetsInsuranceAmount)) {
                throw new InvalidPositiveIntegerException(fieldName + ": "+ assetsInsuranceAmount + ",");
            }
        }
        catch (NumberFormatException e){
            throw new InvalidPositiveIntegerException(fieldName + ": "+ assetsInsuranceAmount + ",");
        }
        return this;
    }
}
