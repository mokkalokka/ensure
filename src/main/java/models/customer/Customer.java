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
    public static final AtomicInteger NEXT_INSURANCE_NR = new AtomicInteger(10000); // TODO: finn en robust måte å hente indeks på, fiks dette i AccidentStatement.accidentNr også.

    private int insuranceNr;
    private String lastName;
    private String firstName;
    private LocalDate customerSince;
    private String invoiceAddress;
    private ArrayList<Insurance> listOfInsurances;
    private ArrayList<AccidentStatement> listOfAccidentStatements; // skademelding
    private String pendingCompensation; // TODO: finn en måte å strukturere data på.

    //TODO: Denne burde fjernes til fordel for den andre konstruktøren

    public Customer(String firstName, String lastName, String invoiceAddress) {
        this.insuranceNr = NEXT_INSURANCE_NR.getAndIncrement();
        this.firstName = firstName;
        this.lastName = lastName;
        this.invoiceAddress = invoiceAddress;
        this.customerSince = LocalDate.now();
        listOfInsurances = new ArrayList<>();
        listOfAccidentStatements = new ArrayList<>();
    }

    public Customer(String firstName, String lastName, String invoiceAddress, LocalDate customerSince) {
        this.insuranceNr = NEXT_INSURANCE_NR.getAndIncrement();
        this.firstName = firstName;
        this.lastName = lastName;
        this.invoiceAddress = invoiceAddress;
        this.customerSince = customerSince;
        listOfInsurances = new ArrayList<>();
        listOfAccidentStatements = new ArrayList<>();
    }

    public Customer(int insuranceNr, String firstName, String lastName, String invoiceAddress, LocalDate customerSince) {
        setInsuranceNr(insuranceNr);
        this.firstName = firstName;
        this.lastName = lastName;
        this.invoiceAddress = invoiceAddress;
        this.customerSince = customerSince;
        listOfInsurances = new ArrayList<>();
        listOfAccidentStatements = new ArrayList<>();
    }

    public void addInsurance(Insurance insurance) throws InputMismatchException {
        if (!isRegisteredToThisCustomer(insurance)) {
            throw new InputMismatchException("Insurance must be registered to this customer.");
        }
        listOfInsurances.add(insurance);
    }

    public void addAccidentStatement(AccidentStatement accidentStatement) {
        if (!isRegisteredToThisCustomer(accidentStatement)) {
            throw new InputMismatchException("Accident Statement must be registered to this customer.");
        }
        listOfAccidentStatements.add(accidentStatement);
    }

    private boolean isRegisteredToThisCustomer(Insurance insurance) {
        return insuranceNr == insurance.getRegisteredTo();
    }

    private boolean isRegisteredToThisCustomer(AccidentStatement accidentStatement) {
        return insuranceNr == accidentStatement.getRegisteredTo();
    }

    public String searchData() {
        return (insuranceNr + firstName + lastName + invoiceAddress + customerSince).toLowerCase();
    }

    //---------- Getters & setters -----------

    public int getInsuranceNr() {
        return insuranceNr;
    }

    public void setInsuranceNr(int insuranceNr) {
        if (insuranceNr >= NEXT_INSURANCE_NR.get()) {
            NEXT_INSURANCE_NR.lazySet(insuranceNr + 1);
        }
        this.insuranceNr = insuranceNr;
    }

    public static int getNextInsuranceNr() {
        return NEXT_INSURANCE_NR.get();
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

    public void overwriteInsurance(int insuranceToReplace, Insurance insurance) {
        listOfInsurances.set(insuranceToReplace, insurance);
    }
}
