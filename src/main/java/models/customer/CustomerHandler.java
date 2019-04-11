package models.customer;

import javafx.collections.ObservableList;
import models.exceptions.customerExceptions.*;
import models.insurance.Insurance;

import java.time.LocalDate;

public class CustomerHandler {

    //Midlertidig fiks:
    private LocalDate customerSince = null;
    private int insuranceNr = 0;

    public void setCustomerSince(String customerSince) {
        this.customerSince = LocalDate.parse(customerSince);
    }

    public void setInsuranceNr(String insuranceNr) {
        this.insuranceNr = Integer.parseInt(insuranceNr);
    }

    // Tror denne metode heller burde returnere true/false slik at man kan handle alt som skal vises i controller.
    public Customer createNewCustomer(String firstName, String lastName, String invoiceAddress) throws InvalidCustomerException {
        if (stringContainsNumbers(firstName)) {
            throw new InvalidFirstNameException();
        } else if (stringContainsNumbers(lastName)) {
            throw new InvalidLastNameException();
        } else if (firstName.isEmpty() || lastName.isEmpty() || invoiceAddress.isEmpty()) {
            throw new EmptyFieldsException();
        }

        //Sjekker om kunden ligger i listen allerede
        else if (duplicateCustomer(firstName, lastName, invoiceAddress)) {
            throw new DuplicateCustomerException();
        }

        //Dersom ingen feil har oppstått opprett kunden og legg kunden i lista
        else {
            //Dersom kunden ikke er lagt til fra CSV legg til date.now og generer forsikringsnr
            if(customerSince == null && insuranceNr == 0) {
                this.customerSince = LocalDate.now();
                this.insuranceNr = Customer.NEXT_INSURANCE_NR.getAndIncrement();
            }
            Customer customer = new Customer(firstName, lastName, invoiceAddress,insuranceNr, customerSince);
            addToCustomersList(customer);
            return customer;
        }
    }

    //Legger til kunden i CustomerList
    private void addToCustomersList(Customer customer) {
        CustomerList.addCustomer(customer);
    }

    //Sjekker om en String inneholder nummer
    public boolean stringContainsNumbers(String string){
        return string.matches(".*\\d.*");
    }

    //Sjekker om kunden ligger i systemet allerede
    public boolean duplicateCustomer(String firstName, String lastName, String invoiceAddress){
        ObservableList<Customer> customerObservableList = CustomerList.getCustomerList();
        for (Customer customer : customerObservableList){
            if(customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName) &&
                    customer.getInvoiceAddress().equals(invoiceAddress)){
                return true;
            }
        }
        return false;
    }

    public static void addInsuranceToCustomer(Insurance insurance) throws NoSuchCustomerException {
        for (Customer customer : CustomerList.getCustomerList()) {
            if (customer.getInsuranceNr() == insurance.getRegisteredTo()) {
                customer.addInsurance(insurance);
                return;
            }
        }
        throw (new NoSuchCustomerException());
    }


}
