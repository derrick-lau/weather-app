package org.openjfx;

import org.openjfx.services.SideMenuScene;
import org.openjfx.repository.ISideMenuScene;
import org.openjfx.repository.IFxml;
import org.openjfx.services.Files;
import org.openjfx.services.Fxml;
import org.openjfx.repository.IFiles;

public class Factory
{

    public static IFxml fxmlServices () {
        return new Fxml();
    }

    public static IFiles fileServices () {
        return new Files();
    }

    public static ISideMenuScene sideMenuController () { return new SideMenuScene();}
}



