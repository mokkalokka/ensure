package models.insurance.boatInsurance;

import java.io.Serializable;

public class Boat implements Serializable {

    private String registrationNr;
    private BoatOwner owner;
    private String boatType;
    private String boatModel;
    private double lengthInft;
    private String modelYear;
    private String engineType;
    private int engineHP;

    public Boat(String registrationNr, String boatType, String boatModel, BoatOwner owner, double lengthInft, String modelYear, String engineType, int engineHP) {
        this.registrationNr = registrationNr;
        this.boatType = boatType;
        this.boatModel = boatModel;
        this.owner = owner;
        this.lengthInft = lengthInft;
        this.modelYear = modelYear;
        this.engineType = engineType;
        this.engineHP = engineHP;
    }

    public String getRegistrationNr() {
        return registrationNr;
    }

    public String getBoatType() {
        return boatType;
    }

    public String getBoatModel() {
        return boatModel;
    }

    public BoatOwner getOwner() {
        return owner;
    }

    public double getLengthInft() {
        return lengthInft;
    }

    public String getModelYear() {
        return modelYear;
    }

    public String getEngineType() {
        return engineType;
    }

    public int getEngineHP() {
        return engineHP;
    }
}
