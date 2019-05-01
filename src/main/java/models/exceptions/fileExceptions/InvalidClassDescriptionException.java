package models.exceptions.fileExceptions;

public class InvalidClassDescriptionException extends FileHandlingExceptions{
    public InvalidClassDescriptionException(String currentClass, int currentLine) {
        super("Typen: " + currentClass + " på linje " + currentLine + " kan ikke gjenkjennes");
    }
}
