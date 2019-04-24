package models.exceptions.builderExceptions;

import java.util.Calendar;

public class InvalidYearException extends BuilderInputException{

    public InvalidYearException(String field) {
        super(field + " er ikke ett gyldig årstall.\n" +
                "Velg ett år mellom 1500 og " + Calendar.getInstance().get(Calendar.YEAR));
    }
}
