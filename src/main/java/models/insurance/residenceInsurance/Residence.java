package models.insurance.residenceInsurance;

import java.time.Year;

public class Residence {
    private String address; // fritidsbolig skal ha forskjellig adresse fra Customer.invoiceAdress
    private Year yearOfConstruction;
    private String type;
    private String constructionMaterial;
    private String condition;
    private int sqMeters;

    public Residence(String address, Year yearOfConstruction, String type, String constructionMaterial, String condition, int sqMeters) {
        this.address = address;
        this.yearOfConstruction = yearOfConstruction;
        this.type = type;
        this.constructionMaterial = constructionMaterial;
        this.condition = condition;
        this.sqMeters = sqMeters;
    }
}
