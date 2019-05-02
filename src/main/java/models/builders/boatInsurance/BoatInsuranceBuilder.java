package models.builders.boatInsurance;

import models.builders.InsuranceBuilder;
import models.exceptions.builderExceptions.BuilderInputException;
import models.insurance.boatInsurance.Boat;
import models.insurance.boatInsurance.BoatInsurance;

import java.time.LocalDate;


public class BoatInsuranceBuilder extends InsuranceBuilder {
    private Boat boat;


    public BoatInsuranceBuilder setRegisteredTo(String registeredTo) throws BuilderInputException {
        super.setRegisteredTo(registeredTo);
        return this;
    }

    public BoatInsuranceBuilder setAnnualPremium(String annualPremium) throws BuilderInputException {
        super.setAnnualPremium(annualPremium);
        return this;
    }

    public BoatInsuranceBuilder setDateOfIssue(String dateOfIssue) throws BuilderInputException {
        super.setDateOfIssue(dateOfIssue);
        return this;
    }


    public BoatInsuranceBuilder setTotal(String total) throws BuilderInputException {
        super.setTotal(total);
        return this;
    }

    public BoatInsuranceBuilder setCoverageDescription(String coverageDescription) throws BuilderInputException {
        super.setCoverageDescription(coverageDescription);
        return this;
    }

    public BoatInsuranceBuilder setInsuranceID(int insuranceID) throws BuilderInputException {
        super.setInsuranceID(insuranceID);
        return this;
    }

    public BoatInsuranceBuilder setInsuranceID(String insuranceID) throws BuilderInputException {
        super.setInsuranceID(insuranceID);
        return this;
    }

    public BoatInsuranceBuilder setBoat(Boat boat) {
        this.boat = boat;
        return this;
    }


    public BoatInsurance build() {
        if (dateOfIssue == null) {
            dateOfIssue = LocalDate.now();
        }
        if (insuranceID == 0) {
            return new BoatInsurance(
                    super.registeredTo,
                    super.annualPremium,
                    super.total,
                    super.coverageDescription,
                    boat,
                    super.dateOfIssue
            );
        } else {
            return new BoatInsurance(
                    super.registeredTo,
                    super.annualPremium,
                    super.total,
                    super.coverageDescription,
                    boat,
                    super.dateOfIssue,
                    super.insuranceID
            );
        }
    }

}

