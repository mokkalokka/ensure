package models.exceptions.customerExceptions;

public class NoSuchAccidentStatementException extends InvalidCustomerException {
    public NoSuchAccidentStatementException() {
        super("Ingen skademelding med dette skadenummeret");
    }
}
