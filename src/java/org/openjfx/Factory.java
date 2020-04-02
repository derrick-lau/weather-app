package org.openjfx;

import org.openjfx.repository.IWeatherMath;
import org.openjfx.services.ReadCsv;
import org.openjfx.services.SideMenuScene;
import org.openjfx.repository.ISideMenuScene;
import org.openjfx.repository.IFxml;
import org.openjfx.services.Fxml;
import org.openjfx.repository.IReadFiles;
import org.openjfx.services.WeatherMath;

public class Factory
{

    public static IFxml fxmlServices () {
        return new Fxml();
    }

    public static IReadFiles fileServices () {
        return new ReadCsv();
    }

    public static ISideMenuScene sideMenuController () { return new SideMenuScene();}

    public static IWeatherMath weatherMath () { return new WeatherMath(); }
}



