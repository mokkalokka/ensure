package models.filewriter;

import models.accidentStatement.AccidentStatement;
import models.accidentStatement.Witness;
import models.customer.Customer;
import models.exceptions.fileExceptions.NoCustomersFoundException;
import models.filewriter.classwriter.CSVWritable;
import models.filewriter.classwriter.CustomerWriter;
import models.insurance.Insurance;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CsvWriter extends FileWriterStrategy {

    private final List<AccidentStatement> listOfAllAccidentStatements = new ArrayList<>();
    private List<CSVWritable> listOfAllObjects = new ArrayList<>();

    public CsvWriter(String path, List<Customer> customerList) {
        super(path, customerList);
        fillList(customerList);
    }

    private void fillList(List<Customer> customerList) {
        customerList.forEach(customer -> {
            listOfAllObjects.add(customer);
            listOfAllObjects.addAll(customer.getListOfInsurances());
            customer.getListOfAccidentStatements().forEach(accidentStatement -> {
                listOfAllObjects.add(accidentStatement);
                listOfAllObjects.addAll(accidentStatement.getListOfWitnesses());
            });
        });
        // sorter kolleksjon her: Collections.sort(listOfAllObjects);
    }

    @Override
    public void writeFile() throws IOException {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(path, StandardCharsets.ISO_8859_1);

            for (int i = 0; i < listOfAllObjects.size(); i++) {
                CSVWritable writableObject = listOfAllObjects.get(i);
                if (i == 0) {
                    writer.println(writableObject.getNameOfClass());
                    writer.println(generateHeaderFromObject(writableObject));
                }
                else if (objectsDifferInClass(writableObject, listOfAllObjects.get(i-1))) {
                    writer.print(writableObject.getNameOfClass());
                    writer.println(generateHeaderFromObject(writableObject));
                }
                writer.println(writeObjectToCsvString(writableObject));
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    private boolean objectsDifferInClass(CSVWritable obj1, CSVWritable obj2) {
        return obj1.getClass() != obj2.getClass();
    }

    private String generateHeaderFromObject(CSVWritable object) {
        return String.join(";", object.getFieldNamesAsStrings());
    }

    private String writeObjectToCsvString(CSVWritable csvWritableObject) {
        return String.join(";", csvWritableObject.getFieldValuesAsStrings());
    }

}
