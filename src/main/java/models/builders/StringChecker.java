package models.builders;

public class StringChecker {

    public boolean containsNumbers(String string){
        return string.matches(".*\\d.*");
    }

    public boolean isEmptyOrNull(String string) {
        return (string == null || string.trim().isEmpty());
    }


}
