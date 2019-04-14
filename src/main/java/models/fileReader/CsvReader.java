package models.fileReader;

import models.builders.CustomerBuilder;
import models.builders.boatInsurance.BoatBuilder;
import models.builders.boatInsurance.BoatInsuranceBuilder;
import models.builders.residenceInsurance.PrimaryResidenceInsuranceBuilder;
import models.builders.travelInsurance.TravelInsuranceBuilder;
import models.customer.Customer;
import models.customer.CustomerList;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.exceptions.customerExceptions.InvalidFirstNameException;
import models.exceptions.customerExceptions.InvalidLastNameException;
import models.insurance.boatInsurance.BoatInsurance;
import models.insurance.boatInsurance.BoatOwner;
import models.insurance.residenceInsurance.PrimaryResidenceInsurance;
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
                    customerParser(lineArray);
                    System.out.println("kunde " + lineArray[0]);
                    break;

                case "Batforsikringer":
                    //boatInsuranceParser(lineArray);
                    System.out.println("båt " + lineArray[0]);
                    break;

                case "Husforsikringer":
                    //primaryResidenceInsuranceParser(lineArray);
                    System.out.println("Hus" + lineArray[0]);
                    //Todo: parser
                    break;

                case "Fritidsboligforsikringer":
                    //Todo: parser
                    break;

                case "Reiseforsikringer":
                    //Todo: parser
                    System.out.println("Reise " + lineArray[0]);
                    //parseTravelInsurance(lineArray);
                    break;

                case "Skademeldinger":
                    //Todo: parser
                    break;
            }

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
    }


    private void customerParser(String[] lineArray) throws InvalidCustomerException {
        Customer parsedCustomer = new CustomerBuilder()
                .setInsuranceNr(lineArray[0])
                .setLastName(lineArray[1])
                .setFirstName(lineArray[2])
                .setCustomerSince(lineArray[3])
                .setInvoiceAddress(lineArray[4])
                .build();

        CustomerList.addCustomer(parsedCustomer);

    }

    private void boatInsuranceParser(String[] lineArray) {
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
        //TODO: Må legge forsikringen i lista over forsikringer

    }

}


