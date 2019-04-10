package models.fileReader;

import models.builders.boatInsurance.BoatBuilder;
import models.builders.boatInsurance.BoatInsuranceBuilder;
import models.customer.Customer;
import models.insurance.boatInsurance.Boat;
import models.insurance.boatInsurance.BoatInsurance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {

    public void readCsv(String pathToCsv) throws IOException {


        BufferedReader br = new BufferedReader(new FileReader(pathToCsv));
        String line;
        String skipLine;

        while ((line = br.readLine()) != null) {
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

                            System.out.println(lineArray[0]);
                            //boatInsuranceParser(lineArray);
                        }

                        break;

                    case "Batforsikring":
                        //Todo: parser
                        skipLine = br.readLine(); // Hopper over linjen med headere

                        //Så lenge det er fler linjer og det ikke er en ny klasse
                        while ((line = br.readLine()) != null &&
                                ((lineArray = line.split(";")).length) != 1) {

                            System.out.println(lineArray[0]);
                            //boatInsuranceParser(lineArray);
                        }
                        break;

                    case "Husforsikring":
                        //Todo: parser
                        break;

                    case "Fritidsboligforsikring":
                        //Todo: parser
                        break;

                    case "Reiseforsikring":
                        //Todo: parser
                        break;

                    case "Skademeldinger":
                        //Todo: parser
                        break;
                }


            }
        }


    }
/*
    private void boatInsuranceParser(String[] lineArray) {
        BoatInsurance boatInsurance = new BoatInsuranceBuilder()
                .setRegisteredTo(lineArray[0])
                .setAnnualPremium(lineArray[1])
                .setCoverageDescription(lineArray[2])
                .setTotal(lineArray[4])
                .setBoat(new BoatBuilder()
                        .setBoatModel(lineArray[5])
                        .setBoatType(lineArray[6])
                        .setEngineHP(lineArray[7]))
                .build()
                .addToInsuranceList(); //TODO: Denne metoden er ikke opprettet
    }


}
 */
}
