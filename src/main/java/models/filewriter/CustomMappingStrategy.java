package models.filewriter;

import com.opencsv.bean.BeanField;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Generisk Custom Mapping strategy for OpenCsv.
 * Sorterer først kolonner etter @CsvBindByPosition,
 * setter deretter headernavn etter annotasjonene @CsvBindByName eller @CsvCustomBindByName.
 * Løsning hentet fra https://stackoverflow.com/a/51928251
 */
public class CustomMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {

    /**
     * Returnerer en String[] fra et bean Object. Den returnerte listen
     * hentes fra datafeltene i beanen som har de gitte annotasjonene.
     */
    @Override
    public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {

        // Setter antall kolonner etter antall annoterte felt.
        super.setColumnMapping(new String[ getAnnotatedFields(bean) ]);
        final int numOfColumns = findMaxFieldIndex();
        if (!isAnnotationDriven() || numOfColumns == -1) {
            return super.generateHeader(bean);
        }

        String[] header = new String[numOfColumns + 1];
        BeanField<T> beanField;

        // Går gjennom hvert annoterte datafelt i bean objektet
        // og legger til det annoterte navnet i header.
        // this.findField(int col) returnerer T.Field fra gitt col nr.
        for (int i = 0; i <= numOfColumns; i++) {
            beanField = this.findField(i);
            String columnHeaderName = extractHeaderName(beanField);
            header[i] = columnHeaderName;
        }

        return header;
    }

    /**
     * Tar et bean Object som input og returnerer antall datafelt i beanen med de
     * gitte annotasjonene.
     */
    private int getAnnotatedFields(T bean) {
        return (int) Arrays.stream(FieldUtils.getAllFields(bean.getClass()))
                .filter(this::isFieldAnnotated)
                .count();
    }

    /**
     * Tar et Field (datafelt) som input og returnerer true om det er annotert med
     * enten @CsvBindByName eller @CsvBindByCustomName
     */
    private boolean isFieldAnnotated(Field field) {
        return field.isAnnotationPresent(CsvBindByName.class)
                || field.isAnnotationPresent(CsvCustomBindByName.class);
    }


    /**
     * Tar et BeanField og returnerer navnet angitt i annotasjonen @CsvBindByName
     * eller annotasjonen @CsvCustomBindByName.
     */
    private String extractHeaderName(BeanField<T> beanField) {
        if (beanField == null || beanField.getField() == null) {
            return StringUtils.EMPTY;
        }

        Field field = beanField.getField();

        if (field.getDeclaredAnnotationsByType(CsvBindByName.class).length != 0) {
            final CsvBindByName bindByNameAnnotation = field.getDeclaredAnnotationsByType(CsvBindByName.class)[0];
            return bindByNameAnnotation.column();
        }

        if (field.getDeclaredAnnotationsByType(CsvCustomBindByName.class).length != 0) {
            final CsvCustomBindByName bindByNameAnnotation = field.getDeclaredAnnotationsByType(CsvCustomBindByName.class)[0];
            return bindByNameAnnotation.column();
        }

        return StringUtils.EMPTY;

    }

}
