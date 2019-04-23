/*
package models.fileReader;


import javafx.concurrent.Task;
import models.customer.Customer;
import models.customer.CustomerList;
import models.exceptions.customerExceptions.InvalidCustomerException;
import models.gui.ErrorDialog;
import org.junit.Test;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CsvReaderTest {

  @Test
    public void readCsv(){
        String pathToCsv = "./src/test/resources/csvWriterTest.csv";
        CsvReaderTask csvReader = new CsvReaderTask(pathToCsv);
        System.out.println(CustomerList.getCustomerCount());

        try {
            csvReader.call();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidCustomerException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(CustomerList.getCustomerCount());

    }
}



*/
