package models.fileReader;


import models.customer.CustomerList;
import models.exceptions.customerExceptions.InvalidCustomerException;
import org.junit.Test;
import java.io.IOException;
public class CsvReaderTest {

    @Test
    public void readCsv(){
        CsvReader csvReader = new CsvReader();
        String pathToCsv = "./src/test/resources/csvWriterTest.csv";
        System.out.println(CustomerList.getCustomerCount());
        try {
            csvReader.readCsv(pathToCsv);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidCustomerException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.println(CustomerList.getCustomerCount());

    }
}
