package models.builders;

import java.util.Calendar;

public class StringChecker {

    public boolean containsNumbers(String string) {
        return string.matches(".*\\d.*");
    }

    public boolean isEmptyOrNull(String string) {
        return (string == null || string.trim().isEmpty());
    }

    public boolean validYear(int year) {
        Calendar now = Calendar.getInstance();

        if (year > 1500 && now.get(Calendar.YEAR) >= year) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validBooleanString(String string) {
        if (string.toLowerCase().equals("true") || string.toLowerCase().equals("false")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNegative(String string) {
        if (Double.parseDouble(string) < 0) {
            return true;
        } else {
            return false;
        }
    }

}
