package models.fileReader;


import models.customer.CustomerList;
import org.junit.Test;
import java.io.IOException;
public class CsvReaderTest {

    @Test
    public void readCsv(){
        CsvReader csvReader = new CsvReader();
        String pathToCsv = "./src/test/resources/csvWriterTest.csv";

        try {
            csvReader.readCsv(pathToCsv);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CustomerList.getCustomerCount();

    }
}
