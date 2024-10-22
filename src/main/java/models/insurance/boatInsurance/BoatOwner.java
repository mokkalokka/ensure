package models.insurance.boatInsurance;

import java.io.Serializable;

public class BoatOwner implements Serializable {

    private String firstName;
    private String lastName;

    public BoatOwner(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return lastName + "," + firstName;
    }
}
