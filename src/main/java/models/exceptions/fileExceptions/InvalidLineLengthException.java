package models.exceptions.fileExceptions;

public class InvalidLineLengthException extends FileHandlingExceptions {

    public InvalidLineLengthException(String insuranceType, int currentLine) {
        super("Feil antal felt i fil for " + insuranceType + " på linje " + currentLine);
    }
}
