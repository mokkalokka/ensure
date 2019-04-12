package models.builders;

import models.customer.Customer;
import models.exceptions.customerExceptions.EmptyFieldsException;
import models.exceptions.customerExceptions.InvalidFirstNameException;
import models.exceptions.customerExceptions.InvalidLastNameException;
import models.insurance.AccidentStatement;
import models.insurance.Insurance;

import java.time.LocalDate;
import java.util.ArrayList;

public class CustomerBuilder {

    private int insuranceNr = 0;
    private String lastName;
    private String firstName;
    private LocalDate customerSince;
    private String invoiceAddress;
    private String pendingCompensation; // TODO: finn en måte å strukturere data på.

    public CustomerBuilder setInsuranceNr(String insuranceNr) throws EmptyFieldsException {
        if (isEmptyOrNull(insuranceNr)) {
            throw new EmptyFieldsException();
        }
        this.insuranceNr = Integer.parseInt(insuranceNr);
        return this;
    }

    public CustomerBuilder setLastName(String lastName) throws InvalidLastNameException, EmptyFieldsException {
        if (isEmptyOrNull(lastName)) {
            throw new EmptyFieldsException();
        }
        else if (containsNumbers(lastName)) {
            throw new InvalidLastNameException();
        }
        this.lastName = lastName;
        return this;
    }

    public CustomerBuilder setFirstName(String firstName) throws InvalidFirstNameException, EmptyFieldsException {
        if (isEmptyOrNull(firstName)) {
            throw new EmptyFieldsException();
        }
        else if (containsNumbers(firstName)) {
            throw new InvalidFirstNameException();
        }
        this.firstName = firstName;
        return this;
    }

    public boolean containsNumbers(String string){
        return string.matches(".*\\d.*");
    }


    public CustomerBuilder setCustomerSince(String customerSince) throws EmptyFieldsException {
        if (isEmptyOrNull(customerSince)) {
            throw new EmptyFieldsException();
        }
        this.customerSince = LocalDate.parse(customerSince);
        return this;
    }

    public CustomerBuilder setInvoiceAddress(String invoiceAddress) throws EmptyFieldsException {
        if (isEmptyOrNull(invoiceAddress)) {
            throw new EmptyFieldsException();
        }
        this.invoiceAddress = invoiceAddress;
        return this;
    }

    public CustomerBuilder setPendingCompensation(String pendingCompensation) throws EmptyFieldsException {
        if (isEmptyOrNull(pendingCompensation)) {
            throw new EmptyFieldsException();
        }
        this.pendingCompensation = pendingCompensation;
        return this;
    }

    private boolean isEmptyOrNull(String string) {
        return (string == null || string.trim().isEmpty());
    }

    public Customer build() {
        if (customerSince == null) {
            customerSince = LocalDate.now();
        }
        if (insuranceNr == 0) {
            return new Customer(
                    firstName,
                    lastName,
                    invoiceAddress,
                    customerSince
            );
        }
        return new Customer(
                insuranceNr,
                firstName,
                lastName,
                invoiceAddress,
                customerSince
        );
    }


}
