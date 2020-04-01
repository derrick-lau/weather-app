package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application
{

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException
    {
        scene = new Scene(Factory.fxmlServices().load("primary"));
        stage.setScene(scene);
        stage.show();
        Factory.fileServices().readFiles("/home/c1941440/Desktop/pj/weatherFX/src/resources/org/openjfx/__MACOSX");
    }

    public static void setScene(String fxml) throws  IOException
    {
        scene.setRoot(Factory.fxmlServices().load(fxml));
    }

    public static void main(String[] args) {
        launch();
    }

}