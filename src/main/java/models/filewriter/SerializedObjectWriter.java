package models.filewriter;

import models.customer.Customer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class SerializedObjectWriter implements fileWriter {

    @Override
    public void writeObject(Object object, String path) throws IOException {
        String filepath = path + ".jobj"; // kanskje .jobj skal være direkte i input

        FileOutputStream fos = new FileOutputStream(filepath);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(object);
    }
}
