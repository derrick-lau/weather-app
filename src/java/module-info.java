module weatherFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;
    requires java.sql;


    opens org.openjfx.services to javafx.graphics;
    opens org.openjfx to javafx.fxml;
    opens org.openjfx.controller to javafx.fxml;
    opens org.openjfx.model to javafx.base, opencsv;
    exports org.openjfx;
}