package models.exceptions.builderExceptions;

public class InvalidPositiveIntegerException extends BuilderInputException {

    public InvalidPositiveIntegerException(String field) {
        super(field + " kan kunne inneholde positive heltall");
    }
}
