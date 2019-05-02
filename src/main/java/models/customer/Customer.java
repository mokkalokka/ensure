package models.customer;

import models.accidentStatement.AccidentStatement;
import models.filewriter.CSVWritable;
import models.insurance.Insurance;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Serializable, CSVWritable {

    private static final long serialVersionUID = 7374958920320110060L;
    private static final AtomicInteger NEXT_INSURANCE_NR = new AtomicInteger(10000);
    public static final String nameOfClass = "Kunder";

    private int insuranceNr;
    private String lastName;
    private String firstName;
    private LocalDate customerSince;
    private String invoiceAddress;
    private ArrayList<Insurance> listOfInsurances;
    private ArrayList<AccidentStatement> listOfAccidentStatements; // skademelding
    private double pendingCompensation;

    public Customer(String firstName, String lastName, String invoiceAddress) {
        this.insuranceNr = NEXT_INSURANCE_NR.getAndIncrement();
        this.firstName = firstName;
        this.lastName = lastName;
        this.invoiceAddress = invoiceAddress;
        this.customerSince = LocalDate.now();
        listOfInsurances = new ArrayList<>();
        listOfAccidentStatements = new ArrayList<>();
        pendingCompensation = 0.0;
    }

    public Customer(String firstName, String lastName, String invoiceAddress, LocalDate customerSince, double pendingCompensation) {
        this.insuranceNr = NEXT_INSURANCE_NR.getAndIncrement();
        this.firstName = firstName;
        this.lastName = lastName;
        this.invoiceAddress = invoiceAddress;
        this.customerSince = customerSince;
        this.pendingCompensation = pendingCompensation;
        listOfInsurances = new ArrayList<>();
        listOfAccidentStatements = new ArrayList<>();
    }

    public Customer(int insuranceNr, String firstName, String lastName, String invoiceAddress, LocalDate customerSince, double pendingCompensation) {
        setInsuranceNr(insuranceNr);
        this.firstName = firstName;
        this.lastName = lastName;
        this.invoiceAddress = invoiceAddress;
        this.customerSince = customerSince;
        this.pendingCompensation = pendingCompensation;
        listOfInsurances = new ArrayList<>();
        listOfAccidentStatements = new ArrayList<>();
    }

    public void addInsurance(Insurance insurance) throws InputMismatchException {
        if (!isRegisteredToThisCustomer(insurance)) {
            throw new InputMismatchException("Insurance must be registered to this customer.");
        }
        listOfInsurances.add(insurance);
    }

    public void removeInsurance(Insurance insurance) {
        listOfInsurances.remove(insurance);
    }

    public void addAccidentStatement(AccidentStatement accidentStatement) {
        if (!isRegisteredToThisCustomer(accidentStatement)) {
            throw new InputMismatchException("Accident Statement must be registered to this customer.");
        }
        listOfAccidentStatements.add(accidentStatement);
    }

    public void removeAccidentStatement(AccidentStatement accidentStatement) {
        listOfAccidentStatements.remove(accidentStatement);
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

    //Oppdatering av siste forsikringsnummer ved lesing av jobj
    public static void updateNextInsuranceNr(int currentMaxInsuranceNr){
        NEXT_INSURANCE_NR.lazySet(currentMaxInsuranceNr + 1);

    }

    //---------- CSVWritable metoder -----------

    @Override
    public String getNameOfClass() {
        return nameOfClass;
    }

    @Override
    public List<String> getFieldNamesAsStrings() {
        return new ArrayList<>(Arrays.asList(
                "Forsikringsnummer",
                "Etternavn",
                "Fornavn",
                "Kunde siden",
                "Fakturaadresse",
                "Ubetalte erstatninger"));
    }

    @Override
    public List<String> getFieldValuesAsStrings() {
        return new ArrayList<>(Arrays.asList(
                String.valueOf(insuranceNr),
                lastName,
                firstName,
                String.valueOf(customerSince),
                invoiceAddress,
                String.valueOf(pendingCompensation)
        ));
    }

    @Override
    public int getWriteIndex() {
        return 0;
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

    public double getPendingCompensation() {
        return pendingCompensation;
    }

    public void setPendingCompensation(double pendingCompensation) {
        this.pendingCompensation = pendingCompensation;
    }

    public ArrayList<AccidentStatement> getListOfAccidentStatements() {
        return listOfAccidentStatements;
    }

    public void overwriteInsurance(int insuranceToReplace, Insurance insurance) {
        listOfInsurances.set(insuranceToReplace, insurance);
    }

    public void overwriteAccidentStatement(int accidentStatementToReplace, AccidentStatement accidentStatement) {
        listOfAccidentStatements.set(accidentStatementToReplace, accidentStatement);
    }
}
