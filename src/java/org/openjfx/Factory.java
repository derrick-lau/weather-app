package org.openjfx;

import org.openjfx.repository.IWeatherMath;
import org.openjfx.services.CsvFile;
import org.openjfx.services.SideMenuScene;
import org.openjfx.repository.ISideMenuScene;
import org.openjfx.repository.IFxml;
import org.openjfx.services.Fxml;
import org.openjfx.repository.IFiles;
import org.openjfx.services.WeatherMath;

public class Factory
{

    public static IFxml fxmlServices () {
        return new Fxml();
    }

    public static IFiles fileServices () {
        return new CsvFile();
    }

    public static ISideMenuScene sideMenuController () { return new SideMenuScene();}

    public static IWeatherMath weatherMath () { return new WeatherMath(); }
}



