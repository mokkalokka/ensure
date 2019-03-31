package models.customer;

import models.insurance.AccidentStatement;
import models.insurance.Insurance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Serializable {
    private static final AtomicInteger NEXT_INSURANCE_NR = new AtomicInteger(10000); // TODO: finn en robust måte å hente indeks på, fiks dette i AccidentStatement.accidentNr også.
    private String firstName;
    private String lastName;
    private Date customerSince;
    private String invoiceAdress;
    private int insuranceNr;
    private ArrayList<Insurance> listOfInsurances;
    private ArrayList<AccidentStatement> accidentStatements; // skademelding
    private String pendingCompensation; // TODO: finn en måte å strukturere data på.

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerSince = new Date();
        this.insuranceNr = NEXT_INSURANCE_NR.getAndIncrement();
    }
    public Date getCustomerSince() {
        return customerSince;
    }

    public int getInsuranceNr() {
        return insuranceNr;
    }

}
