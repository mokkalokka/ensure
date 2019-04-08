package models.exceptions.customerExceptions;

public class InvalidLastNameException extends InvalidCustomerException{
    public InvalidLastNameException() {

        super("Etternavn kan ikke inneholde siffer");
    }
}
