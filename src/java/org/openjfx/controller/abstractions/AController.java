package org.openjfx.controller.abstractions;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.openjfx.App;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

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

    @Override
    public String getResourcesPath(String name) throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(name);
        return String.valueOf(Paths.get(resource.toURI()).toFile());
    }
}
