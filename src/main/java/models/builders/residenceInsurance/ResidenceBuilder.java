package models.builders.residenceInsurance;

import models.insurance.residenceInsurance.Residence;

import java.time.Year;

public class ResidenceBuilder {
    private String address; // fritidsbolig skal ha forskjellig adresse fra Customer.invoiceAdress
    private Year yearOfConstruction;
    private String type;
    private String constructionMaterial;
    private String condition;
    private int sqMeters;

    public ResidenceBuilder setYearOfConstruction(Year yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
        return this;
    }

    public ResidenceBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public ResidenceBuilder setConstructionMaterial(String constructionMaterial) {
        this.constructionMaterial = constructionMaterial;
        return this;
    }

    public ResidenceBuilder setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public ResidenceBuilder setSqMeters(int sqMeters) {
        this.sqMeters = sqMeters;
        return this;
    }

    public ResidenceBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Residence build(){
        return new Residence(
                address,
                yearOfConstruction,
                type,
                constructionMaterial,
                condition,
                sqMeters);
    }
}
