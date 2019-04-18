package models.fileReader;

import javafx.concurrent.Task;
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


public class CsvReaderTask extends Task implements fileReaderTaskInterface{
    private static ArrayList<Customer> loadedCustomers = new ArrayList<>();
    private final String path;

    public CsvReaderTask(String path) {
        this.path = path;
    }

    @Override
    public List<Customer> call() throws IOException, ClassNotFoundException, InvalidCustomerException {
        //Tømmer loaded customers
        loadedCustomers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line; // = br.readLine();
        String currentClass = "Kunder";
        String skipLine;
        double totalLines = getTotalLines(path);
        double currentLine = 0;


        while ((line = br.readLine()) != null) {
            if(isCancelled()){
                updateMessage("Innlesing avbrutt");
                break;
            }

            currentLine += 1;
            updateProgress(currentLine,totalLines);
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

    private Double getTotalLines(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        Double totalLines = 0.0;
        while ((line = br.readLine()) != null) {
            totalLines ++;
        }
        return totalLines;
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





