package models.builders.residenceInsurance;

import models.exceptions.builderExceptions.BuilderInputException;
import models.insurance.residenceInsurance.PrimaryResidenceInsurance;
import models.insurance.residenceInsurance.Residence;
import java.time.LocalDate;


public class PrimaryResidenceInsuranceBuilderMedExceptions extends ResidenceInsuranceBuilder {
    private Residence residence;


    public PrimaryResidenceInsuranceBuilderMedExceptions setRegisteredTo(String registeredTo) throws BuilderInputException {
        super.setRegisteredTo(registeredTo);
        return this;
    }

    public PrimaryResidenceInsuranceBuilderMedExceptions setAnnualPremium(String annualPremium) throws BuilderInputException {
        super.setAnnualPremium(annualPremium);
        return this;
    }

    public PrimaryResidenceInsuranceBuilderMedExceptions setDateOfIssue(String dateOfIssue) throws BuilderInputException {
        super.setDateOfIssue(dateOfIssue);
        return this;
    }

    public PrimaryResidenceInsuranceBuilderMedExceptions setTotal(String total) throws BuilderInputException {
        super.setTotal(total);
        return this;
    }

    public PrimaryResidenceInsuranceBuilderMedExceptions setCoverageDescription(String coverageDescription) throws BuilderInputException {
        super.setCoverageDescription(coverageDescription);
        return this;
    }

    public PrimaryResidenceInsuranceBuilderMedExceptions setInsuranceID(int insuranceID) throws BuilderInputException {
        super.setInsuranceID(insuranceID);
        return this;
    }

    public PrimaryResidenceInsuranceBuilderMedExceptions setInsuranceID(String insuranceID) throws BuilderInputException {
        super.setInsuranceID(insuranceID);
        return this;
    }

    public PrimaryResidenceInsuranceBuilderMedExceptions setPropertyInsuranceAmount(String propertyInsuranceAmount)throws BuilderInputException  {
        super.setPropertyInsuranceAmount(propertyInsuranceAmount);
        return this;
    }

    public PrimaryResidenceInsuranceBuilderMedExceptions setAssetsInsuranceAmount(String assetsInsuranceAmount)throws BuilderInputException {
        super.setAssetsInsuranceAmount(assetsInsuranceAmount);
        return this;
    }

    public PrimaryResidenceInsuranceBuilderMedExceptions setResidence(Residence residence) {
        this.residence = residence;
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


