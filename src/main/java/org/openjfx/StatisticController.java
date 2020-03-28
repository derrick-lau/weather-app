package org.openjfx;

import java.io.IOException;
import javafx.fxml.FXML;

public class StatisticController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}