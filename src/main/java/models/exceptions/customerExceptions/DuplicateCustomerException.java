package models.exceptions.customerExceptions;

public class DuplicateCustomerException extends InvalidCustomerException {
    public DuplicateCustomerException() {
        super("Kunden ligger allerede i listen");
    }
}
