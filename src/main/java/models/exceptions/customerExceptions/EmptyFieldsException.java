package models.exceptions.customerExceptions;

public class EmptyFieldsException extends InvalidCustomerException {

    public EmptyFieldsException() {
        super("Alle felt må være fylt ut");
    }
}
