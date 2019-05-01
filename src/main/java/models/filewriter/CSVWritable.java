package models.filewriter;

import java.util.List;

public interface CSVWritable {

    // Implementeres av klasser som skal kunne skrives til CSV.

    String getNameOfClass();

    List<String> getFieldNamesAsStrings();

    List<String> getFieldValuesAsStrings();

    int getWriteIndex();
}
