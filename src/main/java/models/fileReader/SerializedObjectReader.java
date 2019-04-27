package models.fileReader;

import models.customer.Customer;
import models.exceptions.fileExceptions.WrongSerialVersionIDException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class SerializedObjectReader extends FileReaderStrategy {

    public SerializedObjectReader(String path) {
        super(path);
    }

    @Override
    public List<Customer> readFile() throws Exception {
        try{
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream oin = new ObjectInputStream(fin);
            List<Customer> loadedCustomers = (List<Customer>) oin.readObject();

            return loadedCustomers;
        }
        catch (IOException e){
            throw new WrongSerialVersionIDException();
        }
    }
}
