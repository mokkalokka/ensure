package models.builders.boatInsurance;

import models.insurance.boatInsurance.Boat;
import models.insurance.boatInsurance.BoatInsurance;

public class BoatInsuranceBuilder{

    private int registeredTo;
    private double annualPremium;
    private double total; // TODO: forsikringsbeløp, kanskje annet navn + hva er det forno?
    private String coverageDescription; // forsikringsbetingelser, ev. annet navn.

    private Boat boat;


    public BoatInsuranceBuilder setRegisteredTo(int registeredTo) {
        this.registeredTo = registeredTo;
        return this;
    }

    public BoatInsuranceBuilder setAnnualPremium(double annualPremium) {
        this.annualPremium = annualPremium;
        return this;
    }

    public BoatInsuranceBuilder setTotal(double total) {
        this.total = total;
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

    public BoatInsurance build(){

        return new BoatInsurance(
                registeredTo,
                annualPremium,
                total,
                coverageDescription,
                boat
        );
    }
}
