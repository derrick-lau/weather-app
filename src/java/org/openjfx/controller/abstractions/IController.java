package org.openjfx.controller.abstractions;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public interface IController extends Initializable
{
    @Override
    void initialize(URL location, ResourceBundle resources);

    @FXML
    void switchToStatistic() throws IOException;

    @FXML
    void switchToPrimary() throws IOException;

    @FXML
    void switchToReport() throws IOException;
}
