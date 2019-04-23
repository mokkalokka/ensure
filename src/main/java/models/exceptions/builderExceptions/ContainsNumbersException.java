package models.exceptions.builderExceptions;

public class ContainsNumbersException extends BuilderInputException {

    public ContainsNumbersException(String message) {
        super("Ett eller fler av feltene inneholder siffer");
    }
}
