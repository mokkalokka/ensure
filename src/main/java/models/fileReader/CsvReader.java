package models.fileReader;

import models.builders.boatInsurance.BoatBuilder;
import models.builders.boatInsurance.BoatInsuranceBuilder;
import models.builders.residenceInsurance.PrimaryResidenceInsuranceBuilder;
import models.builders.residenceInsurance.ResidenceBuilder;
import models.builders.residenceInsurance.SecondaryResidenceInsuranceBuilder;
import models.builders.travelInsurance.TravelInsuranceBuilder;
import models.customer.CustomerHandler;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.insurance.boatInsurance.BoatInsurance;
import models.insurance.boatInsurance.BoatOwner;
import models.insurance.residenceInsurance.PrimaryResidenceInsurance;
import models.insurance.residenceInsurance.SecondaryResidenceInsurance;
import models.travelInsurance.TravelInsurance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class CsvReader {

    public void readCsv(String pathToCsv) throws IOException {


        BufferedReader br = new BufferedReader(new FileReader(pathToCsv));
        String line; // = br.readLine();
        String currentClass = "Kunder";
        String skipLine;


        while ((line = br.readLine()) != null) {
            String[] lineArray = line.split(";");

            //Hvis lengden på linjen er 1, vil det si at denne linjen starter en ny klassse
            if (lineArray.length == 1) {
                currentClass = lineArray[0];

                skipLine = br.readLine(); // Hopper over klassebeskrivelsen
                line = br.readLine();
                lineArray = line.split(";");

            }

            switch (currentClass) {
                case "Kunder":
                    parseCustomer(lineArray);
                    //System.out.println("kunde " + lineArray[0]);
                    break;

                case "Batforsikringer":
                    parseBoatInsurance(lineArray);
                    System.out.println("båt " + lineArray[0]);
                    break;

                case "Husforsikringer":
                    parsePrimaryResidenceInsurance(lineArray);
                    System.out.println("Hus" + lineArray[0]);
                    //Todo: parser
                    break;

                case "Fritidsboligforsikringer":
                    //Todo: parser
                    parseSecondaryResidenceInsurance(lineArray);
                    break;

                case "Reiseforsikringer":
                    //Todo: parser
                    System.out.println("Reise " + lineArray[0]);
                    parseTravelInsurance(lineArray);
                    break;

                case "Skademeldinger":
                    //Todo: parser
                    parseAccidentStatement(lineArray);
                    break;
            }

        }


    }

    private void parseAccidentStatement(String[] lineArray) {

    }

    private void parseSecondaryResidenceInsurance(String[] lineArray) {
        SecondaryResidenceInsurance secondaryResidenceInsurance = new SecondaryResidenceInsuranceBuilder()
                .setRegisteredTo(lineArray[0])
                .setAnnualPremium(lineArray[1])
                .setDateOfIssue(lineArray[2])
                .setTotal(lineArray[3])
                .setCoverageDescription(lineArray[4])
                .setPropertyInsuranceAmount(lineArray[5])
                .setAssetsInsuranceAmount(lineArray[6])
                .setResidence(new ResidenceBuilder()
                        .setAddress(lineArray[7])
                        .setYearOfConstruction(lineArray[8])
                        .setType(lineArray[9])
                        .setConstructionMaterial(lineArray[10])
                        .setCondition(lineArray[11])
                        .setSqMeters(lineArray[12])
                        .build())
                .build();
        try {
            CustomerHandler.addInsuranceToCustomer(secondaryResidenceInsurance);
        } catch (NoSuchCustomerException e) {
            e.printStackTrace();
        }

    }

    private void parsePrimaryResidenceInsurance(String[] lineArray) {

        PrimaryResidenceInsurance primaryResidenceInsurance = new PrimaryResidenceInsuranceBuilder()
                .setRegisteredTo(lineArray[0])
                .setAnnualPremium(lineArray[1])
                .setDateOfIssue(lineArray[2])
                .setTotal(lineArray[3])
                .setCoverageDescription(lineArray[4])
                .setPropertyInsuranceAmount(lineArray[5])
                .setAssetsInsuranceAmount(lineArray[6])
                .setResidence(new ResidenceBuilder()
                        .setAddress(lineArray[7])
                        .setYearOfConstruction(lineArray[8])
                        .setType(lineArray[9])
                        .setConstructionMaterial(lineArray[10])
                        .setCondition(lineArray[11])
                        .setSqMeters(lineArray[12])
                        .build())
                .build();

        try {
            CustomerHandler.addInsuranceToCustomer(primaryResidenceInsurance);
        } catch (NoSuchCustomerException e) {
            e.printStackTrace();
        }
    }

    private void parseTravelInsurance(String[] lineArray) {
        TravelInsurance travelInsurance = new TravelInsuranceBuilder()
                .setRegisteredTo(lineArray[0])
                .setAnnualPremium(lineArray[1])
                .setDateOfIssue(lineArray[2])
                .setMaxCoverage(lineArray[3])
                .setCoverageDescription(lineArray[4])
                .setPremium(lineArray[5])
                .setTotal(lineArray[6])
                .build();

        try {
            CustomerHandler.addInsuranceToCustomer(travelInsurance);
        } catch (NoSuchCustomerException e) {
            e.printStackTrace();
        }
    }


    private void parseCustomer(String[] lineArray) {
        CustomerHandler customerHandler = new CustomerHandler();
        customerHandler.setInsuranceNr(lineArray[0]);
        customerHandler.setCustomerSince(lineArray[3]);

        //TODO: Customer builder? & CustomerID
        try {
            customerHandler.createNewCustomer(lineArray[2], lineArray[1],lineArray[4]);
        } catch (InvalidCustomerException e) {
            e.printStackTrace();
        }

    }

    private void parseBoatInsurance(String[] lineArray) {
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
            CustomerHandler.addInsuranceToCustomer(boatInsurance);
        } catch (NoSuchCustomerException e) {
            e.printStackTrace();
        }

        //TODO: Må legge forsikringen i lista over forsikringer

    }

}


