package models.fileReader;

import models.fileReader.CsvReader;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;

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

    }
}
