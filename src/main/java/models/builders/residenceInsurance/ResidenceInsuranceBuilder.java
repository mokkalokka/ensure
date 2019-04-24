package models.builders.residenceInsurance;

import models.builders.InsuranceBuilder;
import models.builders.StringChecker;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.builderExceptions.EmptyFieldException;
import models.exceptions.builderExceptions.NotANumberException;
import models.insurance.Insurance;
import models.insurance.residenceInsurance.Residence;



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
        }
        catch (NumberFormatException e){
            throw new NotANumberException(fieldName);
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
        }
        catch (NumberFormatException e){
            throw new   NotANumberException(fieldName);
        }

        return this;
    }


    @Override
    public Insurance build() {
        return null;
    }
}
