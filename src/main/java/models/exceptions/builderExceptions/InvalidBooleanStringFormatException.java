package models.exceptions.builderExceptions;

public class InvalidBooleanStringFormatException extends BuilderInputException {

    public InvalidBooleanStringFormatException() {
        super("Den bolske variabelen premium er ikke korrekt");
    }
}
