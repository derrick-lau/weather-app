package org.openjfx.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.openjfx.App;
import org.openjfx.repository.IFxml;

import java.io.IOException;

public class Fxml implements IFxml
{

    @Override
    public Parent load(String fxml) throws IOException
    {
        return new FXMLLoader(App.class.getResource(fxml + ".fxml")).load();
    }
}
