package models.Insurance;

public class BoatInsurance extends Insurance {
    private Boat boat;
    private String owner; // kanskje lage en Personklasse og bruke composition

    public BoatInsurance(double annualPremium, double total, String coverageDescription, Boat boat, String owner) {
        super(annualPremium, total, coverageDescription);
        this.boat = boat;
        this.owner = owner;
    }
}
