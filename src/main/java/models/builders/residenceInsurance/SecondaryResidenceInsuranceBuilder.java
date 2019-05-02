package models.builders.residenceInsurance;

import models.exceptions.builderExceptions.BuilderInputException;
import models.insurance.residenceInsurance.Residence;
import models.insurance.residenceInsurance.SecondaryResidenceInsurance;

import java.time.LocalDate;

public class SecondaryResidenceInsuranceBuilder extends ResidenceInsuranceBuilder {
    private Residence residence;

    public SecondaryResidenceInsuranceBuilder setRegisteredTo(String registeredTo) throws BuilderInputException {
        super.setRegisteredTo(registeredTo);
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setAnnualPremium(String annualPremium) throws BuilderInputException {
        super.setAnnualPremium(annualPremium);
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setDateOfIssue(String dateOfIssue) throws BuilderInputException {
        super.setDateOfIssue(dateOfIssue);
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setTotal(String total) throws BuilderInputException {
        super.setTotal(total);
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setCoverageDescription(String coverageDescription) throws BuilderInputException {
        super.setCoverageDescription(coverageDescription);
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setInsuranceID(int insuranceID) throws BuilderInputException {
        super.setInsuranceID(insuranceID);
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setInsuranceID(String insuranceID) throws BuilderInputException {
        super.setInsuranceID(insuranceID);
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setPropertyInsuranceAmount(String propertyInsuranceAmount) throws BuilderInputException {
        super.setPropertyInsuranceAmount(propertyInsuranceAmount);
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setAssetsInsuranceAmount(String assetsInsuranceAmount) throws BuilderInputException {
        super.setAssetsInsuranceAmount(assetsInsuranceAmount);
        return this;
    }

    public SecondaryResidenceInsuranceBuilder setResidence(Residence residence) {
        this.residence = residence;
        return this;
    }


    public SecondaryResidenceInsurance build() {
        if (dateOfIssue == null) {
            dateOfIssue = LocalDate.now();
        }
        if (insuranceID == 0) {
            return new SecondaryResidenceInsurance(
                    registeredTo,
                    annualPremium,
                    total,
                    coverageDescription,
                    residence,
                    propertyInsuranceAmount,
                    assetsInsuranceAmount,
                    dateOfIssue);
        } else {
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
