package models.filewriter;

import javafx.concurrent.Task;
import models.customer.Customer;
import models.customer.CustomerList;
import models.filewriter.classwriter.CustomerWriter;
import models.insurance.AccidentStatement;
import models.insurance.Insurance;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CsvWriterTask extends Task implements fileWriterTaskInterface {

    private final List<Insurance> listOfAllInsurances;
    private final List<AccidentStatement> listOfAllAccidentStatements;
    private final List<Customer> customerList;
    private final String path;
    private final Double totalCustomers;

    public CsvWriterTask(List<Customer> customerList, String path) {
        listOfAllInsurances = new ArrayList<>();
        listOfAllAccidentStatements = new ArrayList<>();
        this.customerList = customerList;
        this.path = path;
        this.totalCustomers = Double.valueOf(CustomerList.getCustomerCount());
    }

    @Override public Void call() throws Exception {
        PrintWriter writer = null;
        double currentCustomerCount = 0;
        //path.concat(".csv");

        try {
            CustomerWriter customerWriter = new CustomerWriter();
            writer = new PrintWriter(path, "UTF-8");

            // Lag en header og skriv så rader for hver kunde
            writer.println("Kunder");
            writer.println(customerWriter.generateHeader());
            for (Customer customer : customerList) {
                currentCustomerCount += 1;
                updateProgress(currentCustomerCount,totalCustomers);
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
                else if (currentInsuranceIsDifferentType(insurance, listOfAllInsurances.get(i-1))) {
                    writer.println(insurance.getInsuranceName());
                    writer.println(String.join(";", insurance.getFieldNamesAsStrings()));
                }

                writer.println(String.join(";", insurance.getFieldValuesAsStrings()));
            }

            // TODO: skriv alle skademeldinger.
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return  null;
    }
    
    private boolean currentInsuranceIsDifferentType(Insurance currentInsurance, Insurance previousInsurance) {
        return currentInsurance.getClass() != previousInsurance.getClass();
    }
}
