package models.customer;

import com.opencsv.bean.*;
import models.filewriter.LocalDateConverter;
import models.insurance.AccidentStatement;
import models.insurance.Insurance;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Serializable {

    private static final AtomicInteger NEXT_INSURANCE_NR = new AtomicInteger(10000); // TODO: finn en robust måte å hente indeks på, fiks dette i AccidentStatement.accidentNr også.

    @CsvBindByName(column = "Forsikringsnummer")
    @CsvBindByPosition(position = 0, required = true)
    private int insuranceNr;

    @CsvBindByName(column = "Etternavn", required = true)
    @CsvBindByPosition(position = 1)
    private String lastName;

    @CsvBindByName(column = "Fornavn", required = true)
    @CsvBindByPosition(position = 2, required = true)
    private String firstName;

    @CsvBindByName(column = "Kunde siden", required = true)
    @CsvCustomBindByPosition(position = 3,converter = LocalDateConverter.class)
    private LocalDate customerSince;

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

    public Customer() {
        // tom konstruktør for å følge bean-standard
        // (nødvendig for OpenCsv)
    }

    public Customer(String firstName, String lastName, String invoiceAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.invoiceAddress = invoiceAddress;
        this.customerSince = LocalDate.now();
        this.invoiceAddress = invoiceAddress;
        this.insuranceNr = NEXT_INSURANCE_NR.getAndIncrement();
    }

    public String searchData() {
        return (insuranceNr + firstName + lastName + invoiceAddress).toLowerCase();
    }

    //---------- Getters & setters -----------


    public int getInsuranceNr() {
        return insuranceNr;
    }

    public void setInsuranceNr(int insuranceNr) {
        this.insuranceNr = insuranceNr;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getCustomerSince() {
        return customerSince;
    }

    public void setCustomerSince(LocalDate customerSince) {
        this.customerSince = customerSince;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public ArrayList<Insurance> getListOfInsurances() {
        return listOfInsurances;
    }

    public void setListOfInsurances(ArrayList<Insurance> listOfInsurances) {
        this.listOfInsurances = listOfInsurances;
    }

    public ArrayList<AccidentStatement> getAccidentStatements() {
        return accidentStatements;
    }

    public void setAccidentStatements(ArrayList<AccidentStatement> accidentStatements) {
        this.accidentStatements = accidentStatements;
    }

    public String getPendingCompensation() {
        return pendingCompensation;
    }

    public void setPendingCompensation(String pendingCompensation) {
        this.pendingCompensation = pendingCompensation;
    }
}
