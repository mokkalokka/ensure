package models.filewriter;

import models.customer.Customer;
import models.filewriter.classwriter.CustomerWriter;
import models.insurance.AccidentStatement;
import models.insurance.Insurance;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CsvWriter {

    private List<Insurance> listOfAllInsurances;
    private List<AccidentStatement> listOfAllAccidentStatements;

    public CsvWriter() {
        listOfAllInsurances = new ArrayList<>();
        listOfAllAccidentStatements = new ArrayList<>();
    }
    
    public void writeCustomersData(List<Customer> customerList, String path) throws IOException {
        PrintWriter writer = null;
        path.concat(".csv");

        try {
            CustomerWriter customerWriter = new CustomerWriter();
            writer = new PrintWriter(path, "UTF-8");

            // Lag en header og skriv så rader for hver kunde
            writer.println("Kunder");
            writer.println(customerWriter.generateHeader());
            for (Customer customer : customerList) {
                writer.println(customerWriter.write(customer));
                listOfAllInsurances.addAll(customer.getListOfInsurances());
                listOfAllAccidentStatements.addAll(customer.getListOfAccidentStatements());
            }

            // TODO: listOfAllInsurances må her være sortert etter klasse, dette må implementeres med en compareTo e.l.
            Collections.sort(listOfAllInsurances);
            for (int i = 0; i < listOfAllInsurances.size(); i++) {
                Insurance insurance = listOfAllInsurances.get(i);

                if (i == 0) {
                    writer.println(insurance.getInsuranceName());
                    writer.println(String.join(";", insurance.getFieldNamesAsStrings()));
                }
                else if (currentInsuranceIsDifferentTypeFromPrevious(insurance, listOfAllInsurances.get(i-1))) {
                    writer.println(insurance.getInsuranceName());
                    writer.println("\n" + String.join(";", insurance.getFieldNamesAsStrings()));
                }

                writer.println(String.join(";", insurance.getFieldValuesAsStrings()));
            }

            // TODO: skriv alle skademeldinger.
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private boolean currentInsuranceIsDifferentTypeFromPrevious(Insurance currentInsurance, Insurance previousInsurance) {
        return currentInsurance.getClass() != previousInsurance.getClass();
    }
}
