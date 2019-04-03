package models.fileReader;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import models.customer.Customer;
import models.filewriter.CustomMappingStrategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class OpenCSVReader implements fileReader {

    @Override
    public Object readObject(String path) throws IOException {

        /*
        Reader reader = Files.newBufferedReader(Paths.get(path));
        CSVReader csvReader = new CSVReader(reader);

        String[] nextRow;
        while ((nextRow = csvReader.readNext()) != null) {
            for (String column : nextRow) {
                System.out.println(column);
            }
        }

        return null;
        */

        Path filePath = Paths.get(path);
        BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.UTF_8);

        CustomMappingStrategy<Customer> mappingStrategy = new CustomMappingStrategy<>();
        mappingStrategy.setType(Customer.class);

        CsvToBean csvToBean = new CsvToBeanBuilder(br)
                .withSkipLines(1)
                .withType(Customer.class)
                .withMappingStrategy(mappingStrategy)
                .withSeparator(';')
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        List<Customer> customerList = csvToBean.parse();
        return customerList;
    }
}
