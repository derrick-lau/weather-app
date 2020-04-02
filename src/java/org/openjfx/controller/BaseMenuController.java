package org.openjfx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.openjfx.App;

import java.io.IOException;

abstract public class BaseMenuController implements Initializable
{
    @FXML
    protected AnchorPane mainPane;

    @FXML
    protected AnchorPane sidePane;

    @FXML
    protected ImageView menuButton;

    public void switchToStatistic() throws IOException {
        App.setScene("statistic");
    }

    public void switchToPrimary() throws IOException {
        App.setScene("primary");
    }

    public void switchToReport() throws IOException {
        App.setScene("report");
    }

}
