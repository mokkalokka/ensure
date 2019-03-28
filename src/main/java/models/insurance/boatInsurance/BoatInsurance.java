package models.insurance.boatInsurance;

import models.insurance.Insurance;

public class BoatInsurance extends Insurance {
    private Boat boat;

    public BoatInsurance(double annualPremium, double total, String coverageDescription, Boat boat, String owner) {
        super(annualPremium, total, coverageDescription);
        this.boat = boat;
    }
}
