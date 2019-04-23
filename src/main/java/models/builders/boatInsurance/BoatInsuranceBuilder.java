package models.builders.boatInsurance;

import models.insurance.boatInsurance.Boat;
import models.insurance.boatInsurance.BoatInsurance;

import java.time.LocalDate;

public class BoatInsuranceBuilder{

    private int registeredTo;
    private double annualPremium;
    private double total; // TODO: forsikringsbeløp, kanskje annet navn + hva er det forno?
    private String coverageDescription; // forsikringsbetingelser, ev. annet navn.
    private LocalDate dateOfIssue = null;
    private int insuranceID;

    private Boat boat;

    public BoatInsuranceBuilder setInsuranceID(int insuranceID) {
        this.insuranceID = insuranceID;
        return this;
    }

    public BoatInsuranceBuilder setInsuranceID(String insuranceID) {
        this.insuranceID = Integer.parseInt(insuranceID);
        return this;
    }

    public BoatInsuranceBuilder setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = LocalDate.parse(dateOfIssue);
        return this;
    }

    public BoatInsuranceBuilder setRegisteredTo(String registeredTo) {
        this.registeredTo = Integer.parseInt(registeredTo);
        return this;
    }

    public BoatInsuranceBuilder setAnnualPremium(String annualPremium) {
        this.annualPremium = Double.parseDouble(annualPremium);
        return this;
    }

    public BoatInsuranceBuilder setTotal(String total) {
        this.total = Double.parseDouble(total);
        return this;
    }

    public BoatInsuranceBuilder setCoverageDescription(String coverageDescription) {
        this.coverageDescription = coverageDescription;
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

