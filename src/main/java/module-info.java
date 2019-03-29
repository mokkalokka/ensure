module ensure {
    requires javafx.controls;
    requires javafx.fxml;

    //Gir tilgang til jFoenix i modulen
    requires com.jfoenix;

    opens main to javafx.fxml;
    exports main;
}