module weatherFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;


    opens org.openjfx to javafx.fxml;
    opens org.openjfx.controller to javafx.fxml;
    opens org.openjfx.controller.abstractions to javafx.fxml;
    exports org.openjfx;
}