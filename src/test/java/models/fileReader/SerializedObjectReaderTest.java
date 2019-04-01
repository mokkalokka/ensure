package models.fileReader;

import org.junit.Test;
import static org.junit.Assert.*;

public class SerializedObjectReaderTest {

    @Test
    public void readObject() {
        SerializedObjectReader customerReader = new SerializedObjectReader();
        String path = "./src/test/resources/writeCustomersTest.jobj";

        try {
            Object customersObject = customerReader.readObject(path);
            assertNotNull(customersObject);

        } catch (Exception e) {
            System.err.println(e.getClass() + ", Error reading from file: " + e.getMessage());
        }

    }
}