package models.customer;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import models.insurance.AccidentStatement;
import models.insurance.Insurance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Serializable {

    private static final AtomicInteger NEXT_INSURANCE_NR = new AtomicInteger(10000); // TODO: finn en robust måte å hente indeks på, fiks dette i AccidentStatement.accidentNr også.

    @CsvBindByName(column = "Forsikringsnummer", required = true)
    @CsvBindByPosition(position = 0, required = true)
    private int insuranceNr;

    @CsvBindByName(column = "Etternavn", required = true)
    @CsvBindByPosition(position = 1)
    private String lastName;

    @CsvBindByName(column = "Fornavn", required = true)
    @CsvBindByPosition(position = 2, required = true)
    private String firstName;

    @CsvBindByName(column = "Kunde siden", required = true)
    @CsvBindByPosition(position = 3, required = true)
    private Date customerSince;

    @CsvBindByName(column = "Fakturaadresse", required = true)
    @CsvBindByPosition(position = 4, required = true)
    private String invoiceAddress;

    @CsvBindByName(column = "Forsikringer")
    @CsvBindByPosition(position = 5)
    private ArrayList<Insurance> listOfInsurances;

    @CsvBindByName(column = "Skademeldinger")
    @CsvBindByPosition(position = 6)
    private ArrayList<AccidentStatement> accidentStatements; // skademelding

    @CsvBindByName(column = "Ubetalte erstatninger")
    @CsvBindByPosition(position = 7)
    private String pendingCompensation; // TODO: finn en måte å strukturere data på.

    public Customer(String firstName, String lastName, String invoiceAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.invoiceAddress = invoiceAddress;
        this.customerSince = new Date();
        this.invoiceAddress = invoiceAddress;
        this.insuranceNr = NEXT_INSURANCE_NR.getAndIncrement();
    }
    public Date getCustomerSince() {
        return customerSince;
    }

    public int getInsuranceNr() {
        return insuranceNr;
    }


    //Disse blir brukt for å oppdatere tablet
    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public ArrayList<Insurance> getListOfInsurances() {
        return listOfInsurances;
    }

    public ArrayList<AccidentStatement> getAccidentStatements() {
        return accidentStatements;
    }

    public String getPendingCompensation() {
        return pendingCompensation;
    }

    public String searchData() {
        return (insuranceNr + firstName + lastName + invoiceAddress).toLowerCase();
    }

}
