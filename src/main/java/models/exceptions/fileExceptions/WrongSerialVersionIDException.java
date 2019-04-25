package models.exceptions.fileExceptions;

public class WrongSerialVersionIDException extends FileHandlingExceptions {
    public WrongSerialVersionIDException() {
        super("Utdadert filversjon");
    }
}
