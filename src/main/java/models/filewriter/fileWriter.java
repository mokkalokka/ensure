package models.filewriter;

import models.customer.Customer;

import java.io.IOException;
import java.util.List;

interface fileWriter {

    void writeObject(Object object, String path) throws IOException;
}
