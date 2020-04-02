package org.openjfx.controller;


import org.openjfx.Factory;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController extends BaseMenuController
{

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        Factory.sideMenuController().sideMenuInitialization(mainPane, sidePane, menuButton);

    }

}
