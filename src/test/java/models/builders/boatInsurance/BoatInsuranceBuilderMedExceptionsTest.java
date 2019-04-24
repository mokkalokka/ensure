package models.builders.boatInsurance;

import models.customer.Customer;
import models.exceptions.builderExceptions.BuilderInputException;
import models.insurance.boatInsurance.Boat;
import models.insurance.boatInsurance.BoatInsurance;
import models.insurance.boatInsurance.BoatOwner;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoatInsuranceBuilderMedExceptionsTest {

    @Test
    public void build() {

        Customer customer = new Customer("Michael", "Larsen","Omt 550");


        Boat boat1 = null;
        try {
            boat1 = new BoatBuilderMedExceptions()
                    .setRegistrationNr("NE2323")
                    .setBoatModel("S32hh")
                    .setBoatType("Snekke")
                    .setEngineHP("20")
                    .setEngineType("Diesel")
                    .setLengthInft("20")
                    .setModelYear("1990")
                    .setOwner(new BoatOwner("Pål", "Hansen"))
                    .build();
        } catch (BuilderInputException e) {
            e.printStackTrace();
        }

        try {
            BoatInsurance boatInsurance = new BoatInsuranceBuilderMedExceptions()
                    .setBoat(boat1)
                    .setAnnualPremium("132")
                    .setCoverageDescription("Helt greit")
                    .setRegisteredTo(String.valueOf(customer.getInsuranceNr()))
                    .setTotal("2423")
                    .build();
        } catch (BuilderInputException e) {
            e.printStackTrace();
        }


    }
}