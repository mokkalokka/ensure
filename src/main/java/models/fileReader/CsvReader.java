package models.fileReader;

import models.builders.CustomerBuilder;
import models.builders.AccidentStatementBuilder;
import models.builders.boatInsurance.BoatBuilder;
import models.builders.boatInsurance.BoatInsuranceBuilder;
import models.builders.residenceInsurance.PrimaryResidenceInsuranceBuilder;
import models.builders.residenceInsurance.ResidenceBuilder;
import models.builders.residenceInsurance.SecondaryResidenceInsuranceBuilder;
import models.builders.travelInsurance.TravelInsuranceBuilder;
import models.customer.Customer;
import models.customer.CustomerList;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.insurance.AccidentStatement;
import models.insurance.boatInsurance.BoatInsurance;
import models.insurance.boatInsurance.BoatOwner;
import models.insurance.residenceInsurance.PrimaryResidenceInsurance;
import models.insurance.residenceInsurance.SecondaryResidenceInsurance;
import models.travelInsurance.TravelInsurance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class CsvReader {

    public void readCsv(String pathToCsv) throws IOException, InvalidCustomerException {


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
                    break;

                case "Batforsikringer":
                    parseBoatInsurance(lineArray);
                    break;

                case "Husforsikringer":
                    parsePrimaryResidenceInsurance(lineArray);
                    break;

                case "Fritidsboligforsikringer":
                    parseSecondaryResidenceInsurance(lineArray);
                    break;

                case "Reiseforsikringer":
                    parseTravelInsurance(lineArray);
                    break;

                case "Skademeldinger":
                    parseAccidentStatement(lineArray);
                    break;
            }

        }


    }

    private void parseAccidentStatement(String[] lineArray) {
        AccidentStatement accidentStatement = new AccidentStatementBuilder()
                .setRegisteredTo(lineArray[0])
                .setAccidentNr(lineArray[1])
                .setDateOfAccident(lineArray[2])
                .setAppraisalAmount(lineArray[3])
                .setAccidentDescription(lineArray[4])
                .setAccidentType(lineArray[5])
                .setDispersedCompensation(lineArray[6])
                .build();
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
            CustomerList.addInsuranceToCustomer(secondaryResidenceInsurance);
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
            CustomerList.addInsuranceToCustomer(primaryResidenceInsurance);
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
            CustomerList.addInsuranceToCustomer(travelInsurance);
        } catch (NoSuchCustomerException e) {
            e.printStackTrace();
        }
    }


    private void parseCustomer(String[] lineArray) throws InvalidCustomerException {
        Customer parsedCustomer = new CustomerBuilder()
                .setInsuranceNr(lineArray[0])
                .setLastName(lineArray[1])
                .setFirstName(lineArray[2])
                .setCustomerSince(lineArray[3])
                .setInvoiceAddress(lineArray[4])
                .build();

        CustomerList.addCustomer(parsedCustomer);

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
            CustomerList.addInsuranceToCustomer(boatInsurance);
        } catch (NoSuchCustomerException e) {
            e.printStackTrace();
        }
    }

}



