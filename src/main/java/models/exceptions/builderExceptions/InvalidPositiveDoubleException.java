package models.exceptions.builderExceptions;

public class InvalidPositiveDoubleException extends BuilderInputException {

    public InvalidPositiveDoubleException(String field) {
        super(field + " kan kunne inneholde positive desimaltall");
    }
}
