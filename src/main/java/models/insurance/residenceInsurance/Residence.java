package models.insurance.residenceInsurance;

import java.time.Year;

public class Residence {
    private String address; // fritidsbolig skal ha forskjellig adresse fra Customer.invoiceAdress
    private Year yearOfConstruction;
    private String residenceType;
    private String constructionMaterial;
    private String condition;
    private int sqMeters;

    public Residence(String address, Year yearOfConstruction, String type, String constructionMaterial, String condition, int sqMeters) {
        this.address = address;
        this.yearOfConstruction = yearOfConstruction;
        this.residenceType = type;
        this.constructionMaterial = constructionMaterial;
        this.condition = condition;
        this.sqMeters = sqMeters;
    }

    public String getAddress() {
        return address;
    }

    public Year getYearOfConstruction() {
        return yearOfConstruction;
    }

    public String getResidenceType() {
        return residenceType;
    }

    public String getConstructionMaterial() {
        return constructionMaterial;
    }

    public String getCondition() {
        return condition;
    }

    public int getSqMeters() {
        return sqMeters;
    }
}
