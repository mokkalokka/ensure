package models.builders.residenceInsurance;

import models.exceptions.builderExceptions.BuilderInputException;
import models.insurance.residenceInsurance.PrimaryResidenceInsurance;
import models.insurance.residenceInsurance.Residence;
import java.time.LocalDate;


public class PrimaryResidenceInsuranceBuilder extends ResidenceInsuranceBuilder {
    private Residence residence;


    public PrimaryResidenceInsuranceBuilder setRegisteredTo(String registeredTo) throws BuilderInputException {
        super.setRegisteredTo(registeredTo);
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setAnnualPremium(String annualPremium) throws BuilderInputException {
        super.setAnnualPremium(annualPremium);
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setDateOfIssue(String dateOfIssue) throws BuilderInputException {
        super.setDateOfIssue(dateOfIssue);
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setTotal(String total) throws BuilderInputException {
        super.setTotal(total);
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setCoverageDescription(String coverageDescription) throws BuilderInputException {
        super.setCoverageDescription(coverageDescription);
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setInsuranceID(int insuranceID) throws BuilderInputException {
        super.setInsuranceID(insuranceID);
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setInsuranceID(String insuranceID) throws BuilderInputException {
        super.setInsuranceID(insuranceID);
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setPropertyInsuranceAmount(String propertyInsuranceAmount)throws BuilderInputException  {
        super.setPropertyInsuranceAmount(propertyInsuranceAmount);
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setAssetsInsuranceAmount(String assetsInsuranceAmount)throws BuilderInputException {
        super.setAssetsInsuranceAmount(assetsInsuranceAmount);
        return this;
    }

    public PrimaryResidenceInsuranceBuilder setResidence(Residence residence) {
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


