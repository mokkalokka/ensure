package models.filewriter.classwriter;

import models.customer.Customer;

public class CustomerWriter implements WriteClassToCsv<Customer> {
    private final String[] CUSTOMER_HEADER =
            {"Forsikringsnummer","Etternavn","Fornavn","Kunde siden","Fakturaadresse"};

    @Override
    public String generateHeader() {
        return String.join(";", CUSTOMER_HEADER);
    }

    @Override
    public String write(Customer customer) {
        return String.format("%s;%s;%s;%s",
                customer.getInsuranceNr(),
                customer.getLastName(),
                customer.getFirstName(),
                customer.getInvoiceAddress()
        );
    }
}
