package models.fileReader;

import models.customer.Customer;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.fileReader.parsers.*;
import models.insurance.AccidentStatement;
import models.insurance.Insurance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CsvReader implements fileReader{
    private static ArrayList<Customer> loadedCustomers = new ArrayList<>();

    @Override
    public List<Customer> readFile(String path) throws IOException, ClassNotFoundException, InvalidCustomerException {
        //Tømmer loaded customers
        loadedCustomers = new ArrayList<>();


        BufferedReader br = new BufferedReader(new FileReader(path));
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
                    loadedCustomers.add(ParseCustomer.parseCustomer(lineArray));
                    break;

                case "Batforsikringer":
                    addInsuranceToLoadedCustomers(ParseBoatInsurance.parseBoatInsurance(lineArray));
                    break;

                case "Husforsikringer":
                    addInsuranceToLoadedCustomers(
                            ParsePrimaryResidenceInsurance.parsePrimaryResidenceInsurance(lineArray));
                    break;

                case "Fritidsboligforsikringer":
                    addInsuranceToLoadedCustomers(
                            ParseSecondaryResidenceInsurance.parseSecondaryResidenceInsurance(lineArray));
                    break;

                case "Reiseforsikringer":
                    addInsuranceToLoadedCustomers(
                            ParseTravelInsurance.parseTravelInsurance(lineArray));
                    break;

                case "Skademeldinger":
                    addAccidentStatementToLoadedCustomers(
                            ParseAccidentStatement.parseAccidentStatement(lineArray));
                    break;
            }

        }
        return loadedCustomers;
    }

    private static void addInsuranceToLoadedCustomers(Insurance insurance) throws NoSuchCustomerException {
        for (Customer customer : loadedCustomers) {
            if (customer.getInsuranceNr() == insurance.getRegisteredTo()) {
                customer.addInsurance(insurance);
                return;
            }
        }
        throw (new NoSuchCustomerException());
    }
    private static void addAccidentStatementToLoadedCustomers(AccidentStatement accidentStatement) throws NoSuchCustomerException {
        for (Customer customer : loadedCustomers) {
            if (customer.getInsuranceNr() == accidentStatement.getRegisteredTo()) {
                customer.addAccidentStatement(accidentStatement);
                return;
            }
        }
        throw (new NoSuchCustomerException());
    }
}



