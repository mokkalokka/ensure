package models.Insurance;

import java.time.Year;

public class Boat {
    private String registrationNr;
    private String boatType;
    private String boatModel;
    private double lengthInft;
    private Year modelYear;
    private String engineType;
    private int engineHP;

    public Boat(String registrationNr, String boatType, String boatModel, double lengthInft, Year modelYear, String engineType, int engineHP) {
        this.registrationNr = registrationNr;
        this.boatType = boatType;
        this.boatModel = boatModel;
        this.lengthInft = lengthInft;
        this.modelYear = modelYear;
        this.engineType = engineType;
        this.engineHP = engineHP;
    }
}
