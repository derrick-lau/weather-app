package org.openjfx.controller;


import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import org.openjfx.Factory;
import org.openjfx.model.MonthlyWeather;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;


public class StatisticController extends BaseMenuController
{

    @FXML private LineChart<String, Number> chart1;
    @FXML private ChoiceBox<String> choiceBoxStation;
    @FXML private ChoiceBox<String> choiceBoxYear;
    @FXML private Button viewButton;

    private XYChart.Series<String, Number> tmax = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> tmin = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> af = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> rain = new XYChart.Series<String, Number>();


    Alert alert = new Alert(Alert.AlertType.NONE);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Factory.sideMenuController().sideMenuInitialization(mainPane, sidePane, menuButton);

        initChoiceBox(Factory.fileServices().getResourcesPath());
        initViewButton();
        initChartLegends();
    }

    //viewButton #onAction
    private void viewChoices(ChoiceBox<String> choiceBoxStation, ChoiceBox<String> choiceBoxYear)
    {
        String folder = Factory.fileServices().getResourcesPath();
        String chosenStation = choiceBoxStation.getValue().toLowerCase();
        String chosenYear = choiceBoxYear.getValue();
        addChosenDataToSeries(folder, chosenStation, chosenYear);
        addSeriesToXYChart(chart1);
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

    private void initChoiceBox(String dataFolder)
    {
        choiceBoxStation.getItems().addAll(Factory.fileServices().getFilteredFileNames(dataFolder));
        choiceBoxStation.setValue("Aberporth");
        choiceBoxYear.getItems().addAll(getYearsRange());
        choiceBoxYear.setValue("2019");
    }

    private List<String> getYearsRange()
    {
        List<String> years = new ArrayList<>();
        for(int i = 2010; i<= LocalDate.now().getYear(); i++)
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
            for (MonthlyWeather weather : weatherList)
            {
                if (weather.getYear().toString().equals(chosenYear))
                {
                    tmax.getData().add(new XYChart.Data<>(Integer.toString(month), weather.getTmax()));
                    tmin.getData().add(new XYChart.Data<>(Integer.toString(month), weather.getTmin()));
                    af.getData().add(new XYChart.Data<>(Integer.toString(month), weather.getAf()));
                    rain.getData().add(new XYChart.Data<>(Integer.toString(month), weather.getRain() / 10));
                    month++;
                }
            }

    }

    private void addSeriesToXYChart(XYChart chart)
    {
        chart.getData().clear();

        if(!tmin.getData().isEmpty() || !tmin.getData().isEmpty() || !af.getData().isEmpty() || !rain.getData().isEmpty()) {
            chart.getData().add(tmin);
            chart.getData().add(tmax);
            chart.getData().add(af);
            chart.getData().add(rain);

        } else {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("No data found");
            alert.show();
        }

    }

    private void clearSeries()
    {
        tmax.getData().clear();
        tmin.getData().clear();
        af.getData().clear();
        rain.getData().clear();
    }
}