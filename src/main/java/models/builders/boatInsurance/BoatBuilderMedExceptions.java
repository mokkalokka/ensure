package models.builders.boatInsurance;

import models.builders.StringChecker;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.builderExceptions.EmptyFieldException;
import models.exceptions.builderExceptions.InvalidYearException;
import models.exceptions.builderExceptions.NotANumberException;
import models.insurance.boatInsurance.Boat;
import models.insurance.boatInsurance.BoatOwner;

public class BoatBuilderMedExceptions {

    private String registrationNr;
    private String boatType;
    private String boatModel;
    private BoatOwner owner;
    private double lengthInft;
    private String modelYear;
    private String engineType;
    private int engineHP;

    StringChecker stringChecker = new StringChecker();

    public BoatBuilderMedExceptions setRegistrationNr(String registrationNr) throws BuilderInputException {
        if(stringChecker.isEmptyOrNull(registrationNr)){
            throw new EmptyFieldException("RegistreringsNr");
        }

        this.registrationNr = registrationNr;
        return this;

    }

    public BoatBuilderMedExceptions setBoatType(String boatType) throws BuilderInputException {
        if(stringChecker.isEmptyOrNull(boatType)){
            throw new EmptyFieldException("Båttype");
        }

        this.boatType = boatType;
        return this;

    }

    public BoatBuilderMedExceptions setBoatModel(String boatModel) throws BuilderInputException {
        if(stringChecker.isEmptyOrNull(boatModel)){
            throw new EmptyFieldException("Båtmodell");
        }

        this.boatModel = boatModel;
        return this;

    }

    public BoatBuilderMedExceptions setOwner(BoatOwner owner) {
        this.owner = owner;
        return this;
    }

    public BoatBuilderMedExceptions setLengthInft(String lengthInft) throws BuilderInputException {
        if(stringChecker.isEmptyOrNull(lengthInft)){
            throw new EmptyFieldException("Lengde i fot");
        }
        else{
            try {
                this.lengthInft = Double.parseDouble(lengthInft);
            }
            catch (NumberFormatException e){
                throw new NotANumberException("Lengde i fot");
            }
        }
        return this;
    }


    public BoatBuilderMedExceptions setModelYear(String modelYear) throws BuilderInputException {
        int modelYearInt;
        String fieldName = "Årsmodell";

        if(stringChecker.isEmptyOrNull(modelYear)){
            throw new EmptyFieldException(fieldName);
        }
        try{
            modelYearInt = Integer.parseInt(modelYear);
            if(!stringChecker.validYear(modelYearInt)){
                throw new InvalidYearException(fieldName);
            }
        }
        catch (NumberFormatException e){
            throw new NotANumberException(fieldName);
        }
        this.modelYear = modelYear;
        return this;
    }

    public BoatBuilderMedExceptions setEngineType(String engineType) throws BuilderInputException {
        if(stringChecker.isEmptyOrNull(engineType)){
            throw new EmptyFieldException("Motortype");
        }
        this.engineType = engineType;
        return this;
    }

    public BoatBuilderMedExceptions setEngineHP(String engineHP) throws BuilderInputException {
        String fieldName = "Motorstyrke (HP)";

        if(stringChecker.isEmptyOrNull(engineHP)){
            throw new EmptyFieldException(fieldName);
        }
        try{
            this.engineHP = Integer.parseInt(engineHP);
        }
        catch (NumberFormatException e){
            throw new NotANumberException(fieldName);
        }
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