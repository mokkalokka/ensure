package models.filewriter;

import models.customer.Customer;
import models.insurance.boatInsurance.Boat;
import models.insurance.boatInsurance.BoatInsurance;
import models.insurance.boatInsurance.BoatOwner;
import models.travelInsurance.TravelInsurance;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
        BoatInsurance boatInsurance1 = new BoatInsurance(customer1, 30000.0, 50000.0, "Dekker masse rart", boat1);
        BoatInsurance boatInsurance2 = new BoatInsurance(customer2, 34000.0, 50000.0, "Dekker ikke like mye", boat1 );
        TravelInsurance travelInsurance1 = new TravelInsurance(customer2, 20000.0, 100_000.0, "Dekker hus og innbo", 200_000.0);
        customer1.addInsurance(boatInsurance1);
        customer2.addInsurance(travelInsurance1);
        customer2.addInsurance(boatInsurance2);
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