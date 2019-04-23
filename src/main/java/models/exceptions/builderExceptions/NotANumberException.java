package models.exceptions.builderExceptions;

public class NotANumberException extends BuilderInputException {

    public NotANumberException(String field) {
        super(field + " kan kunne inneholde tall og desimaltall");
    }
}
