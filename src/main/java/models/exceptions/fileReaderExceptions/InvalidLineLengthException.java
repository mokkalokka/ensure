package models.exceptions.fileReaderExceptions;

public class InvalidLineLengthException extends FileReaderInputException {

    public InvalidLineLengthException(String message) {
        super(message);
    }
}
