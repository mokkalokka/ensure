package models;

import java.util.Date;

public class Customer {
    private String firstName;
    private String lastName;
    private Date customerSince;
    private String invoiceAdress;
    private int insuranceNr;
    private static int nextInsuranceNr;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerSince = new Date();
        this.insuranceNr = 10000 + nextInsuranceNr++;
    }

    public Date getCustomerSince() {
        return customerSince;
    }

    public int getInsuranceNr() {
        return insuranceNr;
    }

    public int getNextInsuranceNr() {
        return nextInsuranceNr + 10000;
    }
}
