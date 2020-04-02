package org.openjfx.controller;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.openjfx.Factory;
import org.openjfx.model.AnnualWeather;
import org.openjfx.model.MonthlyWeather;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReportController extends BaseMenuController
{
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Factory.sideMenuController().sideMenuInitialization(mainPane, sidePane, menuButton);
    }

    //onAction
    public void save() {

        String title = "          " + "Annual Weather Report\n\n\n\n";
        List<String> annualWeathers = getWeatherStringList();

        FileChooser fileChooser = new FileChooser();
        setFileChooserExtension(fileChooser);

        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            writeTextsToFile(title, annualWeathers, file);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private List<String> getWeatherStringList ()
    {
        List<List<MonthlyWeather>> weatherLists = Factory.fileServices().readFiles(Factory.fileServices().getResourcesPath("org/openjfx/__MACOSX"));
        List<String> annualWeatherList = Factory.weatherMath().getCalculatedStrings(weatherLists);
        return annualWeatherList;
    }

    private void setFileChooserExtension(FileChooser fileChooser)
    {
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
    }

    private void writeTextsToFile(String title, List<String> contents, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(title);
            for(String content: contents )
            {
                writer.println(content);
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}


