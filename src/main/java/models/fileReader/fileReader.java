package models.fileReader;

import java.io.IOException;

interface fileReader {

     Object readObject(String path) throws IOException, ClassNotFoundException;
}
