package org.openjfx.controller;

import org.openjfx.Factory;
import org.openjfx.controller.abstractions.AController;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportController extends AController
{



    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Factory.sideMenuController().sideMenuInitialization(mainPane, sidePane, menuButton);

    }
}
