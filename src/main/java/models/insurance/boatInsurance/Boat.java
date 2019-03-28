package models.insurance.boatInsurance;

import java.time.Year;

public class Boat {
    private String registrationNr;
    private String boatType;
    private String boatModel;
    //private BoatOwner owner;
    private double lengthInft;
    private Year modelYear;
    private String engineType;
    private int engineHP;

    public Boat(String registrationNr, String boatType, String boatModel, /*BoatOwnerowner,*/  double lengthInfeet, Year modelYear, String engineType, int engineHP) {
        this.registrationNr = registrationNr;
        this.boatType = boatType;
        this.boatModel = boatModel;
        //this.owner = owner;
        this.lengthInft = lengthInft;
        this.modelYear = modelYear;
        this.engineType = engineType;
        this.engineHP = engineHP;
    }
}
