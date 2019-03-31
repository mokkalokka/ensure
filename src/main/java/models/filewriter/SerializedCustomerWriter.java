package models.filewriter;

import models.customer.Customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SerializedCustomerWriter implements fileWriter {

    @Override
    public void writeCustomers(List<Customer> customers, String path) throws IOException {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(path, StandardCharsets.UTF_8);

            for (Customer customer : customers) {
                writer.println(customer);
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
