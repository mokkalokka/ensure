package models.exceptions.fileExceptions;

public class NoCustomersFoundException extends FileHandlingExceptions {

    public NoCustomersFoundException() {
        super("Ingen kunder funnet, kan ikke lagre til fil");
    }
}
