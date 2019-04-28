package models.fileReader;

import models.accidentStatement.AccidentStatement;
import models.accidentStatement.Witness;
import models.customer.Customer;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.exceptions.customerExceptions.NoSuchAccidentStatementException;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.exceptions.fileExceptions.InvalidLineLengthException;
import models.fileReader.parsers.*;
import models.insurance.Insurance;
import models.insurance.residenceInsurance.PrimaryResidenceInsurance;
import models.insurance.residenceInsurance.SecondaryResidenceInsurance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CsvReader extends FileReaderStrategy {
    private ArrayList<Customer> loadedCustomers = new ArrayList<>();


    public CsvReader(String path) {
        super(path);
    }


    @Override
    public List<Customer> readFile() throws Exception {
        //Tømmer loaded customers
        loadedCustomers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line; // = br.readLine();
        String currentClass = "Kunder";
        String skipLine;
        double currentLine = 0;


        while ((line = br.readLine()) != null) {

            currentLine += 1;
            String[] lineArray = line.split(";");

            //Hvis lengden på linjen er 1, vil det si at denne linjen starter en ny klassse
            if (lineArray.length == 1) {
                currentClass = lineArray[0];

                skipLine = br.readLine(); // Hopper over klassebeskrivelsen
                line = br.readLine();
                lineArray = line.split(";");
                currentLine += 2;

            }

            switch (currentClass) {
                case "Kunder":
                    if (lineArray.length == 6) {
                        loadedCustomers.add(ParseCustomer.parseCustomer(lineArray));
                    } else {
                        throw new InvalidLineLengthException("kunde", (int) currentLine);
                    }
                    break;

                case "Batforsikringer":
                    if (lineArray.length == 14) {
                        addInsuranceToLoadedCustomers(ParseBoatInsurance.parseBoatInsurance(lineArray));
                    } else {
                        throw new InvalidLineLengthException("båtforsikring", (int) currentLine);
                    }
                    break;


                case PrimaryResidenceInsurance.insuranceName:
                    if (lineArray.length == 14) {
                        addInsuranceToLoadedCustomers(
                                ParsePrimaryResidenceInsurance.parsePrimaryResidenceInsurance(lineArray));
                    } else {
                        throw new InvalidLineLengthException("husforsikring", (int) currentLine);
                    }

                    break;

                case SecondaryResidenceInsurance.insuranceName:
                    if (lineArray.length == 14) {
                        addInsuranceToLoadedCustomers(
                                ParseSecondaryResidenceInsurance.parseSecondaryResidenceInsurance(lineArray));
                    } else {
                        throw new InvalidLineLengthException("fritidsboligforsikring", (int) currentLine);
                    }

                    break;

                case "Reiseforsikringer":
                    if (lineArray.length == 8) {
                        addInsuranceToLoadedCustomers(
                                ParseTravelInsurance.parseTravelInsurance(lineArray));
                    } else {
                        throw new InvalidLineLengthException("reiseforsikring", (int) currentLine);
                    }
                    break;


                case "Skademeldinger":
                    if (lineArray.length == 7) {
                        addAccidentStatementToLoadedCustomers(
                                ParseAccidentStatement.parseAccidentStatement(lineArray));
                    } else {
                        throw new InvalidLineLengthException("skademelding", (int) currentLine);
                    }

                    break;

                case "Vitner":
                    if (lineArray.length == 5) {
                        addWitnessToLoadedCustomers(
                                ParseWitness.parseWitness(lineArray)
                        );

                    } else {
                        throw new InvalidLineLengthException("vitner", (int) currentLine);
                    }
                    break;
            }

        }
        br.close();
        return loadedCustomers;
    }

    private Double getTotalLines(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        Double totalLines = 0.0;
        while ((line = br.readLine()) != null) {
            totalLines++;
        }
        return totalLines;
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
