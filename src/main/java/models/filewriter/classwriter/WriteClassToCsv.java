package models.filewriter.classwriter;

public interface WriteClassToCsv<T> {

    String generateHeader();

    String write(T t);
}
