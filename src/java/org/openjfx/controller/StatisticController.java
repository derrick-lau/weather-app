package org.openjfx.controller;


import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import org.openjfx.Factory;
import org.openjfx.model.MonthlyWeather;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;


public class StatisticController extends BaseMenuController
{

    @FXML private StackedBarChart<String, Number> stackedBarChart;
    @FXML private ChoiceBox<String> choiceBoxStation;
    @FXML private ChoiceBox<String> choiceBoxYear;
    @FXML private Button viewButton;

    private XYChart.Series<String, Number> tmax = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> tmin = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> af = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> rain = new XYChart.Series<String, Number>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Factory.sideMenuController().sideMenuInitialization(mainPane, sidePane, menuButton);

        initChoiceBox(Factory.fileServices().getResourcesPath("org/openjfx/__MACOSX"),  "Aberporth", "2019");
        initViewButton();
        initChartLegends();
    }

    //viewButton #onAction
    private void viewChoices(ChoiceBox<String> choiceBoxStation, ChoiceBox<String> choiceBoxYear)
    {
        String folder = Factory.fileServices().getResourcesPath("org/openjfx/__MACOSX");
        String chosenStation = choiceBoxStation.getValue().toLowerCase();
        String chosenYear = choiceBoxYear.getValue();
        addChosenDataToSeries(folder, chosenStation, chosenYear);
        addSeriesToXYChart(stackedBarChart);
    }


    /////////////////////////Business Logic///////////////////////////////////////////////////////////////////////////////////




    private void initViewButton()
    {
        viewButton.setOnAction(e -> {
            viewChoices(choiceBoxStation, choiceBoxYear);
        });
    }

    private void initChartLegends()
    {
        tmax.setName("Mean Maximum Temperature");
        tmin.setName("Mean Minimum Temperature");
        af.setName("Days of Air Frosts");
        rain.setName("Total Rainfall(Ten)");
    }

    private void initChoiceBox(String dataFolder, String defaultStation, String defaultYear)
    {
        choiceBoxStation.getItems().addAll(Factory.fileServices().getFilteredFileNames(dataFolder));
        choiceBoxStation.setValue(defaultStation);
        choiceBoxYear.getItems().addAll(getYearsRange());
        choiceBoxYear.setValue(defaultYear);
    }

    private List<String> getYearsRange()
    {
        List<String> years = new ArrayList<>();
        for(int i = 2000; i<= LocalDate.now().getYear(); i++)
        {
            years.add(Integer.toString(i));
        }

        return years;
    }

    private void addChosenDataToSeries (String dataPath, String chosenStation, String chosenYear)
    {
        clearSeries();
        List<MonthlyWeather> weatherList = Factory.fileServices().readFilesByFileName(dataPath, chosenStation);

        int month = 1;
        for (MonthlyWeather weather: weatherList)
        {
            if(weather.getYear().toString().equals(chosenYear))
            {
                tmax.getData().add(new XYChart.Data<>(Integer.toString(month), weather.getTmax()));
                tmin.getData().add(new XYChart.Data<>(Integer.toString(month), weather.getTmin()));
                af.getData().add(new XYChart.Data<>(Integer.toString(month), weather.getAf()));
                rain.getData().add(new XYChart.Data<>(Integer.toString(month), weather.getRain()/10));
                month ++;
            }
        }
    }

    private void addSeriesToXYChart(XYChart chart)
    {

        chart.getData().clear();
        chart.getData().add(tmin);
        chart.getData().add(tmax);
        chart.getData().add(af);
        chart.getData().add(rain);
    }

    private void clearSeries()
    {
        tmax.getData().clear();
        tmin.getData().clear();
        af.getData().clear();
        rain.getData().clear();
    }
}