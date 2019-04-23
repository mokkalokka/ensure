/*
package models.fileReader;

import javafx.concurrent.Task;
import models.customer.Customer;
import models.exceptions.customerExceptions.InvalidCustomerException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;


public class fileReaderTask extends Task<List<Customer>> {
    private final String path;
    private final String fileExtension;

    public fileReaderTask(String path, String fileExtension) {
        this.path = path;
        this.fileExtension = fileExtension;

    }

    @Override
    protected List<Customer> call() throws Exception {
        if (fileExtension.equals("csv")) {
            CsvReader csvReader = new CsvReader();
            try {
                return csvReader.readFile(path);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InvalidCustomerException e) {
                e.printStackTrace();
            }
        }

        else if(fileExtension.equals("jobj")){
            try {
                SerializedObjectReader serializedObjectReader = new SerializedObjectReader();
                return serializedObjectReader.readFile(path);
            }
            catch (IOException e){
                e.getMessage();
            }
        }
        else {
            throw new InputMismatchException("Feil filformat");
        }
        return null;
    }

}
*/
