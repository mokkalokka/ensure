package models.boatInsurance;


import models.builders.boatInsurance.BoatBuilder;
import models.builders.boatInsurance.BoatInsuranceBuilder;
import models.customer.Customer;
import models.exceptions.builderExceptions.BuilderInputException;
import models.insurance.boatInsurance.*;
import org.junit.Test;

public class BoatInsuranceTest {
    BoatInsurance boatInsurance;

    public void setUp(){
        Customer customer = new Customer("Michael", "Larsen","Omt 550");
        try {

            Boat boat1 = new BoatBuilder()
                    .setRegistrationNr("NE2323")
                    .setBoatModel("S32hh")
                    .setBoatType("Snekke")
                    .setEngineHP("20")
                    .setEngineType("Diesel")
                    .setLengthInft("20")
                    .setModelYear("1990")
                    .setOwner(new BoatOwner("Pål", "Hansen"))
                    .build();

            boatInsurance = new BoatInsuranceBuilder()
                    .setBoat(boat1)
                    .setAnnualPremium("191313.33")
                    .setCoverageDescription("Helt greit")
                    .setRegisteredTo(String.valueOf(customer.getInsuranceNr()))
                    .setTotal("3133")
                    .build();
        } catch (BuilderInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void boatInsuranceValues() {

    }
}
