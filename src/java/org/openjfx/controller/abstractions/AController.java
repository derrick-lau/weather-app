package org.openjfx.controller.abstractions;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.openjfx.App;
import org.openjfx.controller.abstractions.IController;

import java.io.IOException;

abstract public class AController implements IController
{
    @FXML
    protected AnchorPane mainPane;

    @FXML
    protected AnchorPane sidePane;

    @FXML
    protected ImageView menuButton;

    @Override
    public void switchToStatistic() throws IOException {
        App.setScene("statistic");
    }

    @Override
    public void switchToPrimary() throws IOException {
        App.setScene("primary");
    }

    @Override
    public void switchToReport() throws IOException {
        App.setScene("report");
    }
}
