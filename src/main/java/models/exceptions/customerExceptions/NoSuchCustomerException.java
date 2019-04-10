package models.exceptions.customerExceptions;

public class NoSuchCustomerException extends InvalidCustomerException {
    public NoSuchCustomerException() {
        super("Feil i registringsnummer for forsikring: Finnes ingen kunde med dette forsikringsnummeret!");
    }
}
