package models.exceptions.customerExceptions;

public class InvalidFirstNameException extends InvalidCustomerException {
    public InvalidFirstNameException() {

        super("Fornavn kan ikke inneholde siffer");
    }
}
