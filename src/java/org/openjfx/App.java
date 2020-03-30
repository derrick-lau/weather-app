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