package models.filewriter;

import models.accidentStatement.AccidentStatement;
import models.company.InsuranceCompany;
import models.customer.Customer;
import models.customer.CustomerList;
import models.exceptions.fileExceptions.NoCustomersFoundException;
import models.filewriter.classwriter.CustomerWriter;
import models.insurance.Insurance;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CsvWriter extends FileWriterStrategy {
    private static final InsuranceCompany INS_COMP = InsuranceCompany.getInstance();
    private final List<Insurance> listOfAllInsurances;
    private final List<AccidentStatement> listOfAllAccidentStatements;

    public CsvWriter(String path, List<Customer> customerList) {
        super(path, customerList);
        listOfAllInsurances = new ArrayList<>();
        listOfAllAccidentStatements = new ArrayList<>();
    }

    @Override
    public void writeFile() throws Exception {
        PrintWriter writer = null;

        if(INS_COMP.getCustomerCount() == 0){
            throw new NoCustomersFoundException();
        }

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

            //Skriver ut alle skademeldinger
            for(int i = 0; i< listOfAllAccidentStatements.size(); i++){
                AccidentStatement accidentStatement = listOfAllAccidentStatements.get(i);
                if(i == 0){
                    writer.println(accidentStatement.getInsuranceName());
                    writer.println(String.join(";",accidentStatement.getFieldNamesAsStrings()));
                }
                writer.println(String.join(";", accidentStatement.getFieldValuesAsStrings()));
            }

            // TODO: skriv alle skademeldinger.
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }


    private boolean currentInsuranceIsDifferentType(Insurance currentInsurance, Insurance previousInsurance) {
        return currentInsurance.getClass() != previousInsurance.getClass();
    }

}
