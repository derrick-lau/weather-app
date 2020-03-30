package org.openjfx.controller;


import org.openjfx.Factory;
import org.openjfx.controller.abstractions.AController;
import org.openjfx.controller.abstractions.IController;

import java.net.URL;
import java.util.ResourceBundle;


public class StatisticController extends AController
{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Factory.sideMenuController().sideMenuInitialization(mainPane, sidePane, menuButton);
    }

}