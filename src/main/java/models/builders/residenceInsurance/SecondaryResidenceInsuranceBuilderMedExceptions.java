package models.builders.residenceInsurance;

import models.exceptions.builderExceptions.BuilderInputException;
import models.insurance.residenceInsurance.Residence;
import models.insurance.residenceInsurance.SecondaryResidenceInsurance;
import java.time.LocalDate;

public class SecondaryResidenceInsuranceBuilderMedExceptions extends ResidenceInsuranceBuilder {
    private Residence residence;

    public SecondaryResidenceInsuranceBuilderMedExceptions setRegisteredTo(String registeredTo) throws BuilderInputException {
        super.setRegisteredTo(registeredTo);
        return this;
    }

    public SecondaryResidenceInsuranceBuilderMedExceptions setAnnualPremium(String annualPremium) throws BuilderInputException {
        super.setAnnualPremium(annualPremium);
        return this;
    }

    public SecondaryResidenceInsuranceBuilderMedExceptions setDateOfIssue(String dateOfIssue) throws BuilderInputException {
        super.setDateOfIssue(dateOfIssue);
        return this;
    }

    public SecondaryResidenceInsuranceBuilderMedExceptions setTotal(String total) throws BuilderInputException {
        super.setTotal(total);
        return this;
    }

    public SecondaryResidenceInsuranceBuilderMedExceptions setCoverageDescription(String coverageDescription) throws BuilderInputException {
        super.setCoverageDescription(coverageDescription);
        return this;
    }

    public SecondaryResidenceInsuranceBuilderMedExceptions setInsuranceID(int insuranceID) throws BuilderInputException {
        super.setInsuranceID(insuranceID);
        return this;
    }

    public SecondaryResidenceInsuranceBuilderMedExceptions setInsuranceID(String insuranceID) throws BuilderInputException {
        super.setInsuranceID(insuranceID);
        return this;
    }

    public SecondaryResidenceInsuranceBuilderMedExceptions setPropertyInsuranceAmount(String propertyInsuranceAmount)throws BuilderInputException  {
        super.setPropertyInsuranceAmount(propertyInsuranceAmount);
        return this;
    }

    public SecondaryResidenceInsuranceBuilderMedExceptions setAssetsInsuranceAmount(String assetsInsuranceAmount)throws BuilderInputException {
        super.setAssetsInsuranceAmount(assetsInsuranceAmount);
        return this;
    }


    public SecondaryResidenceInsurance build(){
        if (dateOfIssue == null) {
            dateOfIssue = LocalDate.now();
        }
        if(insuranceID == 0){
            return new SecondaryResidenceInsurance(
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
            return new SecondaryResidenceInsurance(
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
