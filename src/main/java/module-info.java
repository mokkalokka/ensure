module ensure {
    requires javafx.controls;
    requires javafx.fxml;

    //Gir tilgang til jFoenix i modulen
    requires com.jfoenix;
    requires opencsv;
    requires org.apache.commons.lang3;

    opens main to javafx.fxml;
    exports main;
}