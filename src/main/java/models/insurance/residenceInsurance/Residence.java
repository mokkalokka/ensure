package models.insurance.residenceInsurance;

import java.time.Year;

public class Residence {
    private String adress; // fritidsbolig skal ha forskjellig adresse fra Customer.invoiceAdress
    private Year yearOfConstruction;
    private String type;
    private String constructionMaterial;
    private String condition;
    private int sqMeters;

    public Residence(String adress, Year yearOfConstruction, String type, String constructionMaterial, String condition, int sqMeters) {
        this.adress = adress;
        this.yearOfConstruction = yearOfConstruction;
        this.type = type;
        this.constructionMaterial = constructionMaterial;
        this.condition = condition;
        this.sqMeters = sqMeters;
    }
}
