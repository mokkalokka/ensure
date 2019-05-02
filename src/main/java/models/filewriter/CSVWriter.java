package models.filewriter;

import models.customer.Customer;
import models.exceptions.fileExceptions.NoCustomersFoundException;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CSVWriter extends FileWriterStrategy {

    private List<CSVWritable> objectsToBeWritten = new ArrayList<>();

    public CSVWriter(String path, List<Customer> customerList) {
        super(path, customerList);
        addObjectsToBeWritten(customerList);
    }

    private void addObjectsToBeWritten(List<Customer> customerList) {
        customerList.forEach(customer -> {
            objectsToBeWritten.add(customer);
            objectsToBeWritten.addAll(customer.getListOfInsurances());

            customer.getListOfAccidentStatements().forEach(accidentStatement -> {
                objectsToBeWritten.add(accidentStatement);
                objectsToBeWritten.addAll(accidentStatement.getListOfWitnesses());
            });
        });

        objectsToBeWritten.sort(Comparator.comparingInt(CSVWritable::getWriteIndex));
    }

    @Override
    public void writeFile() throws IOException, NoCustomersFoundException {
        PrintWriter writer = null;

        if (objectsToBeWritten.size() == 0) {
            throw new NoCustomersFoundException();
        }

        try {
            writer = new PrintWriter(path, StandardCharsets.UTF_8);
            writer.println("sep=;");

            for (int i = 0; i < objectsToBeWritten.size(); i++) {
                CSVWritable writableObject = objectsToBeWritten.get(i);
                if (i == 0) {
                    writer.println(writableObject.getNameOfClass());
                    writer.println(generateHeaderFromObject(writableObject));
                } else if (objectsDifferInClass(writableObject, objectsToBeWritten.get(i - 1))) {
                    writer.println(writableObject.getNameOfClass());
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
