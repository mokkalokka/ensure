package models.fileReader.parsers;

import models.builders.boatInsurance.BoatBuilder;
import models.builders.boatInsurance.BoatInsuranceBuilder;
import models.customer.CustomerList;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.insurance.boatInsurance.BoatInsurance;
import models.insurance.boatInsurance.BoatOwner;

public class ParseBoatInsurance {
    public void parseBoatInsurance(String[] lineArray) {
        // Fornavn og etternavn for båteier
        String[] boatOwner = lineArray[5].split(",");
        String lastName = boatOwner[0];
        String firstName = boatOwner[1];

        //Opprett forsikringen
        BoatInsurance boatInsurance = new BoatInsuranceBuilder()
                .setRegisteredTo(lineArray[0])
                .setAnnualPremium(lineArray[1])
                .setDateOfIssue(lineArray[2])
                .setTotal(lineArray[3])
                .setCoverageDescription(lineArray[4])
                .setBoat( new BoatBuilder()
                        .setOwner( new BoatOwner(firstName, lastName))
                        .setRegistrationNr(lineArray[6])
                        .setBoatType(lineArray[7])
                        .setBoatType(lineArray[8])
                        .setLengthInft(lineArray[9])
                        .setModelYear(lineArray[10])
                        .setEngineType(lineArray[11])
                        .setEngineHP(lineArray[12])
                        .build())
                .build();

        try {
            CustomerList.addInsuranceToCustomer(boatInsurance);
        } catch (NoSuchCustomerException e) {
            e.printStackTrace();
        }
    }


}
