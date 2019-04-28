package models.accidentStatement;

public class Witness {
    private int registeredTo;
    private String firstName;
    private String lastName;
    private String contactInformation;
    private int forAccidentStatement;

    public Witness(int registeredTo, String firstName, String lastName, String contactInformation) {
        this.registeredTo = registeredTo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInformation = contactInformation;
    }

    public Witness(int registeredTo, String firstName, String lastName, String contactInformation,
                   int forAccidentStatement) {
        this.registeredTo = registeredTo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInformation = contactInformation;
        this.forAccidentStatement = forAccidentStatement;
    }

    public int getRegisteredTo() {
        return registeredTo;
    }

    public int getForAccidentStatement() {
        return forAccidentStatement;
    }

    public void setForAccidentStatement(int forAccidentStatement) {
        this.forAccidentStatement = forAccidentStatement;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }
}
