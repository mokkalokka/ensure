package models.builders.boatInsurance;

import models.insurance.boatInsurance.Boat;
import models.insurance.boatInsurance.BoatOwner;

public class BoatBuilder {

    private String registrationNr;
    private String boatType;
    private String boatModel;
    private BoatOwner owner;
    private double lengthInft;
    private String modelYear;
    private String engineType;
    private int engineHP;

    public BoatBuilder setRegistrationNr(String registrationNr) {
        this.registrationNr = registrationNr;
        return this;
    }

    public BoatBuilder setBoatType(String boatType) {
        this.boatType = boatType;
        return this;
    }

    public BoatBuilder setBoatModel(String boatModel) {
        this.boatModel = boatModel;
        return this;
    }

    public BoatBuilder setOwner(BoatOwner owner) {
        this.owner = owner;
        return this;
    }

    public BoatBuilder setLengthInft(String lengthInft) {
        //TODO: Exceptions!
        this.lengthInft = Double.parseDouble(lengthInft);
        return this;
    }

    public BoatBuilder setModelYear(String modelYear) {
        this.modelYear = modelYear;
        return this;
    }

    public BoatBuilder setEngineType(String engineType) {
        this.engineType = engineType;
        return this;
    }

    public BoatBuilder setEngineHP(String engineHP) {
        //TODO: Exceptions!
        this.engineHP = Integer.parseInt(engineHP);
        return this;
    }

    public Boat build(){
        return new Boat(
                registrationNr,
                boatType,
                boatModel,
                owner,
                lengthInft,
                modelYear,
                engineType,
                engineHP);
    }

}