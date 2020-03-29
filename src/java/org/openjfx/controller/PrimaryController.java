package org.openjfx.controller;


import org.openjfx.Factory;
import org.openjfx.controller.abstractions.AController;
import org.openjfx.controller.abstractions.IPrimaryController;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController extends AController implements IPrimaryController
{
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Factory.sideMenuController().sideMenuInitialization(mainPane, sidePane, menuButton);
    }

}
