package models.fileReader;

import models.accidentStatement.AccidentStatement;
import models.accidentStatement.Witness;
import models.customer.Customer;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.exceptions.customerExceptions.NoSuchAccidentStatementException;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.exceptions.fileExceptions.FileHandlingExceptions;
import models.exceptions.fileExceptions.InvalidClassDescriptionException;
import models.exceptions.fileExceptions.InvalidLineLengthException;
import models.fileReader.parsers.*;
import models.insurance.Insurance;
import models.insurance.boatInsurance.BoatInsurance;
import models.insurance.residenceInsurance.PrimaryResidenceInsurance;
import models.insurance.residenceInsurance.SecondaryResidenceInsurance;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVReader extends FileReaderStrategy {
    //Midlertidig liste som returneres dersom lesingen fullføres
    private ArrayList<Customer> loadedCustomers = new ArrayList<>();

    public CSVReader(String path) {
        super(path);
    }


    @Override
    public List<Customer> readFile() throws BuilderInputException, IOException, InvalidCustomerException,
            FileHandlingExceptions {

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        String currentClass = "Kunder";
        String skipLine;
        double currentLine = 0;

        //Hopper over linja som beskriver delimiter
        String skipLine = br.readLine();
        //currentLine brukes til å identifisere hvilke linje det har oppstått en feil
        double currentLine = 1;

        //Mens filen fortsatt har flere linjer
        while ((line = br.readLine()) != null) {

            currentLine += 1;

            //Liste med strenger fra den nåværende linja, splittet av semikolon ;
            String[] lineArray = line.split(";");

            //Hvis lengden på lista er 1, vil det si at denne linjen starter en ny klassse
            if (lineArray.length == 1) {

                //Setter currentClass for å identifisere hvilken klasse man parser
                currentClass = lineArray[0];

                // Hopper over klassebeskrivelsen
                skipLine = br.readLine();

                //Leser neste linje som deretter skal parses
                line = br.readLine();
                lineArray = line.split(";");
                currentLine += 2;

            }

            //Her sorteres hva som skal parses ut i fra current class
            switch (currentClass) {
                case Customer.nameOfClass:
                    //Dersom det er 6 strenger i lista, parse kunden
                    if (lineArray.length == 6) {
                        loadedCustomers.add(ParseCustomer.parseCustomer(lineArray));
                    } else {
                        //Dersom det er feil antall strenger, kast exception og legg ved nåværende klasse
                        //og linje for å vise dette i feilmeldingen
                        throw new InvalidLineLengthException("kunde", (int) currentLine);
                    }
                    break;

                case BoatInsurance.nameOfClass:
                    if (lineArray.length == 14) {
                        addInsuranceToLoadedCustomers(ParseBoatInsurance.parseBoatInsurance(lineArray));
                    } else {
                        throw new InvalidLineLengthException("båtforsikring", (int) currentLine);
                    }
                    break;


                case PrimaryResidenceInsurance.nameOfClass:
                    if (lineArray.length == 14) {
                        addInsuranceToLoadedCustomers(
                                ParsePrimaryResidenceInsurance.parsePrimaryResidenceInsurance(lineArray));
                    } else {
                        throw new InvalidLineLengthException("husforsikring", (int) currentLine);
                    }

                    break;

                case SecondaryResidenceInsurance.nameOfClass:
                    if (lineArray.length == 14) {
                        addInsuranceToLoadedCustomers(
                                ParseSecondaryResidenceInsurance.parseSecondaryResidenceInsurance(lineArray));
                    } else {
                        throw new InvalidLineLengthException("fritidsboligforsikring", (int) currentLine);
                    }

                    break;

                case TravelInsurance.nameOfClass:
                    if (lineArray.length == 8) {
                        addInsuranceToLoadedCustomers(
                                ParseTravelInsurance.parseTravelInsurance(lineArray));
                    } else {
                        throw new InvalidLineLengthException("reiseforsikring", (int) currentLine);
                    }
                    break;


                case AccidentStatement.nameOfClass:
                    if (lineArray.length == 7) {
                        addAccidentStatementToLoadedCustomers(
                                ParseAccidentStatement.parseAccidentStatement(lineArray));
                    } else {
                        throw new InvalidLineLengthException("skademelding", (int) currentLine);
                    }

                    break;

                case Witness.nameOfClass:
                    if (lineArray.length == 5) {
                        addWitnessToLoadedCustomers(
                                ParseWitness.parseWitness(lineArray));

                    } else {
                        throw new InvalidLineLengthException("vitner", (int) currentLine);
                    }
                    break;
            }

        }
        br.close();
        return loadedCustomers;
    }

    private void addInsuranceToLoadedCustomers(Insurance insurance) throws NoSuchCustomerException {
        for (Customer customer : loadedCustomers) {
            if (customer.getInsuranceNr() == insurance.getRegisteredTo()) {
                customer.addInsurance(insurance);
                return;
            }
        }
        throw (new NoSuchCustomerException());
    }

    private void addAccidentStatementToLoadedCustomers(AccidentStatement accidentStatement) throws NoSuchCustomerException {
        for (Customer customer : loadedCustomers) {
            if (customer.getInsuranceNr() == accidentStatement.getRegisteredTo()) {
                customer.addAccidentStatement(accidentStatement);
                return;
            }
        }
        throw (new NoSuchCustomerException());
    }

    private void addWitnessToLoadedCustomers(Witness witness) throws InvalidCustomerException {
        //Finner riktig kunde
        for (Customer customer : loadedCustomers) {
            if (customer.getInsuranceNr() == witness.getRegisteredTo()) {

                //Finner riktig Skademelding og legger til vitne
                for (AccidentStatement accidentStatement : customer.getListOfAccidentStatements()) {
                    if (accidentStatement.getAccidentNr() == witness.getForAccidentStatement()) {
                        accidentStatement.addWitnessContactInfo(witness);
                        return;
                    }
                }
                throw new NoSuchAccidentStatementException();

            }
        }
        throw new NoSuchCustomerException();

    }

}
