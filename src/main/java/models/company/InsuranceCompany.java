package models.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.accidentStatement.AccidentStatement;
import models.customer.Customer;
import models.exceptions.customerExceptions.DuplicateCustomerException;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.insurance.Insurance;

import java.util.List;

public class InsuranceCompany {

    private final String name;
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    private static InsuranceCompany ourInstance = new InsuranceCompany();

    public static InsuranceCompany getInstance() {
        return ourInstance;
    }

    private InsuranceCompany() {
        name = "Ensure";
    }

    public void addCustomer(Customer newCustomer) throws DuplicateCustomerException {
        if (duplicateCustomer(newCustomer)) {
            throw new DuplicateCustomerException();
        }
        customerList.add(newCustomer);
    }

    public void removeCustomer(Customer customerToRemove) {
        customerList.remove(customerToRemove);
    }

    public int getCustomerCount() {
        return customerList.size();
    }

    public ObservableList<Customer> getCustomerList() {
        return customerList;
    }

    public void initNewCustomerList(List<Customer> listOfCustomers) {
        customerList.clear();
        customerList.addAll(listOfCustomers);
        Customer.updateNextInsuranceNr(
                customerList.get(customerList.size() -1 ).getInsuranceNr());
    }

    private boolean duplicateCustomer(Customer source) {
        for (Customer customer : customerList) {
            if (customer.getFirstName().equals(source.getFirstName()) && customer.getLastName().equals(source.getLastName()) &&
                    customer.getInvoiceAddress().equals(source.getInvoiceAddress())) {
                return true;
            }
        }
        return false;
    }

    public void addInsuranceToCustomer(Insurance insurance) throws NoSuchCustomerException {
        for (Customer customer : getCustomerList()) {
            if (insuranceBelongsToCustomer(insurance, customer)) {
                customer.addInsurance(insurance);
                return;
            }
        }
        throw (new NoSuchCustomerException());
    }

    public void addAccidentStatementToCustomer(AccidentStatement accidentStatement) throws NoSuchCustomerException {
        for (Customer customer : getCustomerList()) {
            if (accidentStatementBelongsToCustomer(accidentStatement, customer)) {
                customer.addAccidentStatement(accidentStatement);
                return;
            }
        }
        throw (new NoSuchCustomerException());
    }


    private static boolean insuranceBelongsToCustomer(Insurance insurance, Customer customer) {
        return insurance.getRegisteredTo() == customer.getInsuranceNr();
    }

    private static boolean accidentStatementBelongsToCustomer(AccidentStatement accidentStatement, Customer customer) {
        return accidentStatement.getRegisteredTo() == customer.getInsuranceNr();
    }

    public String getName() {
        return name;
    }
}
