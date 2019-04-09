package models.filewriter;

import models.builders.boatInsurance.BoatBuilder;
import models.builders.boatInsurance.BoatInsuranceBuilder;
import models.customer.Customer;
import models.insurance.boatInsurance.*;
import models.travelInsurance.TravelInsurance;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvWriterTest {
    List<Customer> customerList;
    BoatInsurance boatInsurance;

    @Before
    public void setUp() {
        customerList = new ArrayList<>();
        Customer customer1 = new Customer("Bolle", "Marsvinsen", "Trondheimsveien 7");
        Customer customer2 = new Customer("Tore D.", "Hurt", "Ullevålsveien 2a");
        customerList.add(customer1);
        customerList.add(customer2);

        Boat boat1 = new Boat("regnr","fregatt", "Modell", new BoatOwner("Per", "Nilsen"), 25.5, "2004", "Svær", 500);
        BoatInsurance boatInsurance1 = new BoatInsurance(customer1.getInsuranceNr(), 30000.0, 50000.0, "Dekker masse rart", boat1);
        BoatInsurance boatInsurance2 = new BoatInsurance(customer2.getInsuranceNr(), 34000.0, 50000.0, "Dekker ikke like mye", boat1 );
        TravelInsurance travelInsurance1 = new TravelInsurance(customer2.getInsuranceNr(), 20000.0, 100_000.0, "Dekker hus og innbo", 200_000.0,true);

        Boat boat3 = new BoatBuilder("92100ne")
                .setBoatModel("S32hh")
                .setBoatType("Snekke")
                .setEngineHP(20)
                .setEngineType("Diesel")
                .setLengthInft(20)
                .setModelYear("1990")
                .setOwner(new BoatOwner("Pål", "Hansen"))
                .build();

        BoatInsurance boatInsurance3 = new BoatInsuranceBuilder()
                .setBoat(boat3)
                .setAnnualPremium(191313.33)
                .setCoverageDescription("Helt greit")
                .setRegisteredTo(customer2.getInsuranceNr())
                .setTotal(3133)
                .build();

        customer1.addInsurance(boatInsurance1);
        customer2.addInsurance(travelInsurance1);
        customer2.addInsurance(boatInsurance2);
        customer2.addInsurance(boatInsurance3);
    }

    @Test
    public void writeObject() {
        try {
            String path = "./src/test/resources/csvWriterTest.csv";
            CsvWriter csvWriter = new CsvWriter();
            csvWriter.writeCustomersData(customerList, path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}