package org.openjfx;

import javafx.fxml.FXML;

import java.io.IOException;

public class PrimaryController {

    @FXML
    private void switchToStatistic() throws IOException {
        App.setRoot("statistic");
    }
}
