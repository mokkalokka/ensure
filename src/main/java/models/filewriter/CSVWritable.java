package models.filewriter;

import java.util.List;

public interface CSVWritable {

    // Implementeres av klasser som skal kunne skrives til CSV.

    /***
     *
     * Returner et norsk navn for klassen (i.e. Customer -> Kunde)
     */
    String getNameOfClass();

    /***
     *
     * Returner norske navn for hvert datafelt i klassen som skal skrives til CSV.
     * Rekkefølgen må matche den i getFieldValuesAsStrings.
     */
    List<String> getFieldNamesAsStrings();

    /***
     *
     * Returner verdier i strengformat for de samme datafeltene som er navngitt i getFieldValuesAsStrings.
     * Rekkefølgen på verdiene må matche den i getFieldValuesAsStrings.
     */
    List<String> getFieldValuesAsStrings();

    /***
     *
     * Returner et tall som angir hvilken plass klassen blir skrevet til.
     * (0 er høyeste prioritet).
     */
    int getWriteIndex();
}
