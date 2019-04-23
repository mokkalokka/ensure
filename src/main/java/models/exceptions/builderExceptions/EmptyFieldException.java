package models.exceptions.builderExceptions;

public class EmptyFieldException extends BuilderInputException {

    public EmptyFieldException(String field) {
        super(field + " må være fylt ut");
    }
}
