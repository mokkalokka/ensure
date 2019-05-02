package models.builders;

import models.customer.Customer;
import models.exceptions.builderExceptions.BuilderInputException;
import models.exceptions.builderExceptions.InvalidDateFormatException;
import models.exceptions.builderExceptions.InvalidPositiveDoubleException;
import models.exceptions.builderExceptions.InvalidPositiveIntegerException;
import models.exceptions.customerExceptions.EmptyFieldsException;
import models.exceptions.customerExceptions.InvalidFirstNameException;
import models.exceptions.customerExceptions.InvalidLastNameException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CustomerBuilder {

    private final StringChecker sc = new StringChecker();

    private int insuranceNr = 0;
    private String lastName;
    private String firstName;
    private LocalDate customerSince;
    private String invoiceAddress;
    private double pendingCompensation = 0.0;

    public CustomerBuilder setInsuranceNr(String insuranceNr) throws BuilderInputException, EmptyFieldsException {
        String fieldName = "Forsikringsnummer";

        if (sc.isEmptyOrNull(insuranceNr)) {
            throw new EmptyFieldsException();
        }
        try {
            this.insuranceNr = Integer.parseInt(insuranceNr);
        } catch (NumberFormatException e) {
            throw new InvalidPositiveIntegerException(fieldName + ": "+ insuranceNr + ",");
        }
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

    public CustomerBuilder setCustomerSince(String customerSince) throws BuilderInputException, EmptyFieldsException {
        String fieldName = "Kunde siden";

        if (sc.isEmptyOrNull(customerSince)) {
            throw new EmptyFieldsException();
        }
        try {
            this.customerSince = LocalDate.parse(customerSince);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(fieldName + ": "+ customerSince + ",");
        }
        return this;
    }

    public CustomerBuilder setInvoiceAddress(String invoiceAddress) throws EmptyFieldsException {
        if (sc.isEmptyOrNull(invoiceAddress)) {
            throw new EmptyFieldsException();
        }
        this.invoiceAddress = invoiceAddress;
        return this;
    }

    public CustomerBuilder setPendingCompensation(String pendingCompensation) throws BuilderInputException, EmptyFieldsException {
        String fieldName = "Ubetalte erstatninger";

        if (sc.isEmptyOrNull(pendingCompensation)) {
            throw new EmptyFieldsException();
        }
        try {
            this.pendingCompensation = Double.parseDouble(pendingCompensation);
            if (sc.isNegative(pendingCompensation)) {
                throw new InvalidPositiveDoubleException(fieldName + ": "+ pendingCompensation + ",");
            }
        } catch (NumberFormatException e) {
            throw new InvalidPositiveDoubleException(fieldName + ": "+ pendingCompensation + ",");
        }
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
