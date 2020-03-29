package org.openjfx.repository;

import javafx.scene.Parent;

import java.io.IOException;

public interface IFxml
{
    Parent loadFXML(String fxml) throws IOException;
}
