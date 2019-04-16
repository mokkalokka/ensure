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
        String[] boatOwner = lineArray[6].split(",");
        String lastName = boatOwner[0];
        String firstName = boatOwner[1];

        //Opprett forsikringen
        BoatInsurance boatInsurance = new BoatInsuranceBuilder()
                .setRegisteredTo(lineArray[0])
                .setAnnualPremium(lineArray[1])
                .setDateOfIssue(lineArray[2])
                .setTotal(lineArray[3])
                .setCoverageDescription(lineArray[4])
                .setInsuranceID(lineArray[5])
                .setBoat( new BoatBuilder()
                        .setOwner( new BoatOwner(firstName, lastName))
                        .setRegistrationNr(lineArray[7])
                        .setBoatType(lineArray[8])
                        .setBoatType(lineArray[9])
                        .setLengthInft(lineArray[10])
                        .setModelYear(lineArray[11])
                        .setEngineType(lineArray[12])
                        .setEngineHP(lineArray[13])
                        .build())
                .build();

        try {
            CustomerList.addInsuranceToCustomer(boatInsurance);
        } catch (NoSuchCustomerException e) {
            e.printStackTrace();
        }
    }


}
