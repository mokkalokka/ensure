package models.fileReader.parsers;

import models.builders.CustomerBuilder;
import models.customer.Customer;
import models.customer.CustomerList;
import models.exceptions.customerExceptions.InvalidCustomerException;

public class ParseCustomer {

    public static Customer parseCustomer(String[] lineArray) throws InvalidCustomerException {
        Customer parsedCustomer = new CustomerBuilder()
                .setInsuranceNr(lineArray[0])
                .setLastName(lineArray[1])
                .setFirstName(lineArray[2])
                .setCustomerSince(lineArray[3])
                .setInvoiceAddress(lineArray[4])
                .setPendingCompensation(lineArray[5])
                .build();

        return parsedCustomer;

    }


}
