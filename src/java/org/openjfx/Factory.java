package org.openjfx;

import org.openjfx.repository.*;
import org.openjfx.services.*;

public class Factory
{

    public static IFxml fxmlServices () {
        return new Fxml();
    }

    public static IReadFiles fileServices () {
        return new ReadCsv();
    }

    public static ISideMenuScene sideMenuController () { return new SideMenuScene();}

    public static IFileOut fileOut () { return new FileOut();}

    public static IWeatherMath weatherMath () { return new WeatherMath(); }
}



