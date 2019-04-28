package models.exceptions.fileExceptions;

public class UnsuportedFileExtensionException extends FileHandlingExceptions {
    public UnsuportedFileExtensionException() {
        super("Filformatet er ikke støttet");
    }
}
