package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.openjfx.services.DataManipulation;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public class App extends Application
{

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException
    {
        scene = new Scene(Factory.fxmlServices().loadFXML("primary"));
        stage.setScene(scene);
        stage.show();

    }

    public static void setScene(String fxml) throws  IOException
    {
        scene.setRoot(Factory.fxmlServices().loadFXML(fxml));
    }

    public static void main(String[] args) {
        launch();
    }

}