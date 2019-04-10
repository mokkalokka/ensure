package models.fileReader;

import models.builders.boatInsurance.BoatBuilder;
import models.builders.boatInsurance.BoatInsuranceBuilder;
import models.customer.Customer;
import models.customer.CustomerHandler;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.insurance.boatInsurance.Boat;
import models.insurance.boatInsurance.BoatInsurance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {

    public void readCsv(String pathToCsv) throws IOException {


        BufferedReader br = new BufferedReader(new FileReader(pathToCsv));
        String line = br.readLine();
        String skipLine;


        while (line != null) {
            String[] lineArray = line.split(";");

            //Hvis lengden på linjen er 1, vil det si at denne linjen er en ny klasse
            if (lineArray.length == 1) {
                switch (lineArray[0]) {
                    case "Kunder":

                        //Todo: kunde parser

                        skipLine = br.readLine(); // Hopper over linjen med headere

                        //Så lenge det er fler linjer og det ikke er en ny klasse
                        while ((line = br.readLine()) != null &&
                                ((lineArray = line.split(";")).length) != 1) {

                            customerParser(lineArray);
                        }

                        break;

                    case "Batforsikringer":
                        //Todo: parser
                        skipLine = br.readLine(); // Hopper over linjen med headere

                        //Så lenge det er fler linjer og det ikke er en ny klasse
                        while ((line = br.readLine()) != null &&
                                ((lineArray = line.split(";")).length) != 1) {

                            System.out.println(lineArray[0]);
                            //boatInsuranceParser(lineArray);
                        }
                        break;

                    case "Husforsikringer":
                        //Todo: parser
                        break;

                    case "Fritidsboligforsikringer":
                        //Todo: parser
                        break;

                    case "Reiseforsikringer":
                        //Todo: parser
                        break;

                    case "Skademeldinger":
                        //Todo: parser
                        break;
                }


            }
        }


    }

    private void customerParser(String[] lineArray) {
        CustomerHandler customerHandler = new CustomerHandler();
        try {
            customerHandler.createNewCustomer(lineArray[2], lineArray[1],lineArray[3]);
        } catch (InvalidCustomerException e) {
            e.printStackTrace();
        }

    }

/*
    private void boatInsuranceParser(String[] lineArray) {
        BoatInsurance boatInsurance = new BoatInsuranceBuilder()
                .setRegisteredTo(lineArray[0])
                .setAnnualPremium(lineArray[1])
                //TODO:Dato opprettet
                .setTotal(lineArray[3])
                .setCoverageDescription(lineArray[4]);

        //TODO: Denne metoden er ikke opprettet
    }
    */


}


