package models.exceptions.builderExceptions;

public class InvalidDateFormatException extends BuilderInputException {

    public InvalidDateFormatException(String field) {
        super(field + " er ikke på formen åååå-mm-dd");
    }
}
