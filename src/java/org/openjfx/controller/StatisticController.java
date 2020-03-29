package org.openjfx.controller;


import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.openjfx.Factory;
import org.openjfx.controller.abstractions.IStatisticController;
import java.net.URL;
import java.util.ResourceBundle;


public class StatisticController extends Controller implements IStatisticController
{
    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane sidePane;

    @FXML
    private ImageView menuButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Factory.sideMenuController().sideMenuInitialization(mainPane, sidePane, menuButton);
    }

}