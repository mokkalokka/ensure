package models.customer;

import models.insurance.AccidentStatement;
import models.insurance.Insurance;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Serializable {

    private static final long serialVersionUID = 7374958920320110060L;
    private static final AtomicInteger NEXT_INSURANCE_NR = new AtomicInteger(10000); // TODO: finn en robust måte å hente indeks på, fiks dette i AccidentStatement.accidentNr også.

    private int insuranceNr;
    private String lastName;
    private String firstName;
    private LocalDate customerSince;
    private String invoiceAddress;
    private ArrayList<Insurance> listOfInsurances;
    private ArrayList<AccidentStatement> listOfAccidentStatements; // skademelding
    private String pendingCompensation; // TODO: finn en måte å strukturere data på.

    public Customer(String firstName, String lastName, String invoiceAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.invoiceAddress = invoiceAddress;
        this.customerSince = LocalDate.now();
        this.insuranceNr = NEXT_INSURANCE_NR.getAndIncrement();
        listOfInsurances = new ArrayList<>();
        listOfAccidentStatements = new ArrayList<>();
    }

    public String searchData() {
        return (insuranceNr + firstName + lastName + invoiceAddress).toLowerCase();
    }

    public void addInsurance(Insurance insurance) throws InputMismatchException {
        if (insurance.getRegisteredTo() != insuranceNr) {
            throw new InputMismatchException("Insurance must be registered to this customer.");
        }
        listOfInsurances.add(insurance);
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

    public String getPendingCompensation() {
        return pendingCompensation;
    }

    public void setPendingCompensation(String pendingCompensation) {
        this.pendingCompensation = pendingCompensation;
    }

    public ArrayList<AccidentStatement> getListOfAccidentStatements() {
        return listOfAccidentStatements;
    }
}
