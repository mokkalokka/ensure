package models.builders;

import models.customer.Customer;
import models.exceptions.customerExceptions.EmptyFieldsException;
import models.exceptions.customerExceptions.InvalidFirstNameException;
import models.exceptions.customerExceptions.InvalidLastNameException;

import java.time.LocalDate;

public class CustomerBuilder {

    private final StringChecker sc = new StringChecker();

    private int insuranceNr = 0;
    private String lastName;
    private String firstName;
    private LocalDate customerSince;
    private String invoiceAddress;
    private double pendingCompensation = 0.0;

    public CustomerBuilder setInsuranceNr(String insuranceNr) throws EmptyFieldsException {
        if (sc.isEmptyOrNull(insuranceNr)) {
            throw new EmptyFieldsException();
        }
        this.insuranceNr = Integer.parseInt(insuranceNr);
        return this;
    }

    public CustomerBuilder setLastName(String lastName) throws InvalidLastNameException, EmptyFieldsException {
        if (sc.isEmptyOrNull(lastName)) {
            throw new EmptyFieldsException();
        }
        else if (sc.containsNumbers(lastName)) {
            throw new InvalidLastNameException();
        }
        this.lastName = lastName;
        return this;
    }

    public CustomerBuilder setFirstName(String firstName) throws InvalidFirstNameException, EmptyFieldsException {
        if (sc.isEmptyOrNull(firstName)) {
            throw new EmptyFieldsException();
        }
        else if (sc.containsNumbers(firstName)) {
            throw new InvalidFirstNameException();
        }
        this.firstName = firstName;
        return this;
    }

    public CustomerBuilder setCustomerSince(String customerSince) throws EmptyFieldsException {
        if (sc.isEmptyOrNull(customerSince)) {
            throw new EmptyFieldsException();
        }
        this.customerSince = LocalDate.parse(customerSince);
        return this;
    }

    public CustomerBuilder setInvoiceAddress(String invoiceAddress) throws EmptyFieldsException {
        if (sc.isEmptyOrNull(invoiceAddress)) {
            throw new EmptyFieldsException();
        }
        this.invoiceAddress = invoiceAddress;
        return this;
    }

    public CustomerBuilder setPendingCompensation(String pendingCompensation) throws EmptyFieldsException {
        if (sc.isEmptyOrNull(pendingCompensation)) {
            throw new EmptyFieldsException();
        }
        this.pendingCompensation = Double.parseDouble(pendingCompensation);
        return this;
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
                    customerSince,
                    pendingCompensation
            );
        }
        return new Customer(
                insuranceNr,
                firstName,
                lastName,
                invoiceAddress,
                customerSince,
                pendingCompensation
        );
    }


}
