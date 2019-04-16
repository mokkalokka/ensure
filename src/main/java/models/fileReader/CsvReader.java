package models.fileReader;

import models.exceptions.customerExceptions.InvalidCustomerException;
import models.fileReader.parsers.*;
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
                    ParseCustomer parseCustomer = new ParseCustomer();
                    parseCustomer.parseCustomer(lineArray);
                    break;

                case "Batforsikringer":
                    ParseBoatInsurance parseBoatInsurance = new ParseBoatInsurance();
                    parseBoatInsurance.parseBoatInsurance(lineArray);
                    break;

                case "Husforsikringer":
                    ParsePrimaryResidenceInsurance parsePrimaryResidenceInsurance = new ParsePrimaryResidenceInsurance();
                    parsePrimaryResidenceInsurance.parsePrimaryResidenceInsurance(lineArray);
                    break;

                case "Fritidsboligforsikringer":
                    ParseSecondaryResidenceInsurance parseSecondaryResidenceInsurance = new ParseSecondaryResidenceInsurance();
                    parseSecondaryResidenceInsurance.parseSecondaryResidenceInsurance(lineArray);
                    break;

                case "Reiseforsikringer":
                    ParseTravelInsurance parseTravelInsurance = new ParseTravelInsurance();
                    parseTravelInsurance.parseTravelInsurance(lineArray);
                    break;

                case "Skademeldinger":
                    ParseAccidentStatement parseAccidentStatement = new ParseAccidentStatement();
                    parseAccidentStatement.parseAccidentStatement(lineArray);
                    break;
            }

        }

    }









}



