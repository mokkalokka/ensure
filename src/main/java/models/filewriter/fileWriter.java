package models.filewriter;


import java.io.IOException;

interface fileWriter {

    void writeObject(Object object, String path) throws IOException;
}
