package models.fileReader;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


// Kilde: https://stackoverflow.com/a/54352078

public class LocalDateConverter extends AbstractBeanField {

    // Konverterer streng med yyyy-MM-dd datoformat til LocalDate objekt.
    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parse;
        try {
            parse = LocalDate.parse(s, formatter);
        } catch (DateTimeParseException e) {
            throw new CsvDataTypeMismatchException("Wrong date format: Must be yyyy-MM-dd.");
        }
        return parse;
    }
}
