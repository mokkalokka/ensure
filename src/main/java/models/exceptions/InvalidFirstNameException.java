package models.exceptions;

public class InvalidFirstNameException extends InvalidCustomerException {
    public InvalidFirstNameException() {

        super("Fornavn kan ikke inneholde siffer");
    }
}
