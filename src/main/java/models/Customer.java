package models;

import java.util.Date;

public class Customer {
    private String firstName;
    private String lastName;
    private Date customerSince;
    private String invoiceAdress;
    private int insuranceNr;
    private static int nextInsuranceNr; // TODO: finn en robust måte å hente indeks på, fiks dette i AccidentStatement.accidentNr også.
    // private ArrayList<Insurance> insuranceList;
    // private String accidentStatement; // skademelding TODO: finn en måte å strukturere denne dataen på
    private String pendingCompensation; // TODO: finn en måte å strukturere data på.

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
