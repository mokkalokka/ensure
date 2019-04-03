package models.filewriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import models.customer.Customer;

public class ObjectCsvWriter implements fileWriter {

    @Override
    public void writeObject(Object object, String path) throws IOException {

        Writer writer = new FileWriter(path);
        CustomMappingStrategy<Customer> mappingStrategy = new CustomMappingStrategy<>();
        mappingStrategy.setType(Customer.class);

        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                .withMappingStrategy(mappingStrategy)
                .withSeparator(';')
                .build();

        List<Customer> customers = (ArrayList<Customer>) object;
        try {
            beanToCsv.write(customers);
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }

    }
}
