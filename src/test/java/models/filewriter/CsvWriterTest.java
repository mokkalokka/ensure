package models.filewriter;

import models.builders.CustomerBuilder;
import models.builders.boatInsurance.BoatBuilder;
import models.builders.boatInsurance.BoatInsuranceBuilder;
import models.builders.residenceInsurance.PrimaryResidenceInsuranceBuilder;
import models.builders.residenceInsurance.ResidenceBuilder;
import models.builders.travelInsurance.TravelInsuranceBuilder;
import models.customer.Customer;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.insurance.boatInsurance.*;
import models.insurance.residenceInsurance.PrimaryResidenceInsurance;
import models.insurance.residenceInsurance.Residence;
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


        Customer customer1 = null;
        Customer customer2 = null;

        try {
            customer1 = new CustomerBuilder()
                    .setFirstName("Bolle")
                    .setLastName("Marsvinsen")
                    .setInvoiceAddress("Trondheimsveien 7")
                    .build();
            customer2 = new CustomerBuilder()
                    .setFirstName("Tore Davidson")
                    .setLastName("Hurt")
                    .setInvoiceAddress("Ullevålsveien 2a")
                    .build();
            customerList.add(customer1);
            customerList.add(customer2);
        } catch (InvalidCustomerException e) {
            e.printStackTrace();
        }

        if (customer1 != null && customer2 != null){
            TravelInsurance travelInsurance1 = new TravelInsuranceBuilder()
                    .setRegisteredTo(String.valueOf(customer1.getInsuranceNr()))
                    .setAnnualPremium("2000.0")
                    //Her kommer date of issue
                    .setMaxCoverage("100000.0")
                    .setCoverageDescription("Dekker hus og innbo")
                    .setPremium("true")
                    .setTotal("150000.0")
                    .build();

            BoatInsurance boatInsurance1 = new BoatInsuranceBuilder()
                    .setBoat(new BoatBuilder()
                            .setRegistrationNr("SS3214")
                            .setBoatModel("YL44")
                            .setBoatType("Rib")
                            .setEngineHP("9000")
                            .setEngineType("Diesel")
                            .setLengthInft("21")
                            .setModelYear("2018")
                            .setOwner(new BoatOwner("Fritjof", "Nansen"))
                            .build())
                    .setAnnualPremium("13222.00")
                    .setCoverageDescription("Ikke så mye")
                    .setRegisteredTo(String.valueOf(customer1.getInsuranceNr()))
                    .setTotal("5400")
                    .setInsuranceID("200000")
                    .build();


            BoatInsurance boatInsurance2 = new BoatInsuranceBuilder()
                    .setBoat(new BoatBuilder()
                            .setRegistrationNr("KHD73242")
                            .setBoatModel("X500")
                            .setBoatType("Fregatt")
                            .setEngineHP("300")
                            .setEngineType("Manpower!")
                            .setLengthInft("45")
                            .setModelYear("1650")
                            .setOwner(new BoatOwner("Gunnar", "Persen"))
                            .build())
                    .setAnnualPremium("131332.33")
                    .setCoverageDescription("Alt!")
                    .setRegisteredTo(String.valueOf(customer2.getInsuranceNr()))
                    .setTotal("5400")
                    .build();

            BoatInsurance boatInsurance3 = new BoatInsuranceBuilder()
                    .setBoat(new BoatBuilder()
                            .setRegistrationNr("NE23094872")
                            .setBoatModel("S32hh")
                            .setBoatType("Snekke")
                            .setEngineHP("20")
                            .setEngineType("Diesel")
                            .setLengthInft("20")
                            .setModelYear("1990")
                            .setOwner(new BoatOwner("Pål", "Hansen"))
                            .build())
                    .setAnnualPremium("191313.33")
                    .setCoverageDescription("Helt greit")
                    .setRegisteredTo(String.valueOf(customer2.getInsuranceNr()))
                    .setTotal("3133")
                    .build();

            PrimaryResidenceInsurance primaryResidenceInsurance1 = new PrimaryResidenceInsuranceBuilder()
                    .setRegisteredTo(String.valueOf(customer1.getInsuranceNr()))
                    .setAnnualPremium("244888")
                    //Her kommer kunden som date of issue (optional)
                    .setTotal("100000.00")
                    .setCoverageDescription("Dekker ikke naturkatastrofer")
                    .setResidence(new ResidenceBuilder()
                            .setAddress("Osloveien 50")
                            .setCondition("Middels")
                            .setConstructionMaterial("Tre")
                            .setSqMeters("200")
                            .setType("Enebolig")
                            .setYearOfConstruction("1965")
                            .build())
                    .setPropertyInsuranceAmount("2000000.00")
                    .setAssetsInsuranceAmount("500000")
                    .build();


            customer1.addInsurance(travelInsurance1);
            customer1.addInsurance(boatInsurance1);
            customer2.addInsurance(boatInsurance2);
            customer2.addInsurance(boatInsurance3);
            customer1.addInsurance(primaryResidenceInsurance1);

        }

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