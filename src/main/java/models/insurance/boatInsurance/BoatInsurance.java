package models.insurance.boatInsurance;

import models.customer.Customer;
import models.insurance.Insurance;

import java.util.ArrayList;
import java.util.Arrays;

public class BoatInsurance extends Insurance {
    private Boat boat;

    public BoatInsurance(Customer customer, double annualPremium, double total, String coverageDescription, Boat boat) {
        super(customer, annualPremium, total, coverageDescription);
        this.boat = boat;
    }

    @Override
    public ArrayList<String> getFieldNamesAsStrings() {
        ArrayList<String> fieldNames = new ArrayList<>(
                Arrays.asList("Eier", "Registreringsnummer","Type","Modell",
                        "Lengde i fot", "Årsmodell", "Motortype", "Motorstyrke (HP)")
        );
        fieldNames.addAll(0, super.getFieldNamesAsStrings());
        return fieldNames;
    }

    @Override
    public ArrayList<String> getFieldValuesAsStrings() {
        ArrayList<String> fieldValues = new ArrayList<>(
                Arrays.asList(String.valueOf(boat.getOwner()),
                boat.getRegistrationNr(),
                boat.getBoatType(),
                boat.getBoatModel(),
                String.valueOf(boat.getLengthInft()),
                boat.getModelYear(),
                boat.getEngineType(),
                String.valueOf(boat.getEngineHP()))
        );
        fieldValues.addAll(0, super.getFieldValuesAsStrings());
        return fieldValues;
    }

    public Boat getBoat() {
        return boat;
    }

}
