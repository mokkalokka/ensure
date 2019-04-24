package models.exceptions.builderExceptions;

public class InvalidYearException extends BuilderInputException{

    public InvalidYearException(String field) {
        super(field + " er ikke ett gyldig årstall");
    }
}
