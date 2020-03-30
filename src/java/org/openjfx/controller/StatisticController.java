package org.openjfx.controller;


import javafx.fxml.FXML;
import javafx.scene.chart.*;
import org.openjfx.Factory;
import org.openjfx.controller.abstractions.AController;

import java.net.URL;
import java.util.ResourceBundle;


public class StatisticController extends AController
{
    @FXML
    LineChart<String, Number> lineChart;
    @FXML
    AreaChart<String, Number> areaChart;
    @FXML
    ScatterChart<String, Number> scatterChart;


    XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
    XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
    XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Factory.sideMenuController().sideMenuInitialization(mainPane, sidePane, menuButton);
        viewChart();
    }

    public void viewChart()
    {
        int i = 1;

        if(i==1)
        {
            viewXYChart(lineChart);
            viewXYChart(areaChart);
            viewXYChart(scatterChart);

        }
    }

    private void viewXYChart (XYChart<String, Number> chart)
    {
        chart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>();
        series.getData().add(new XYChart.Data<String, Number>("Jan", 1));
        series.getData().add(new XYChart.Data<String, Number>("Feb", 20));
        series.getData().add(new XYChart.Data<String, Number>("Mar", 10));
        series.getData().add(new XYChart.Data<String, Number>("Apr", 10));
        series.getData().add(new XYChart.Data<String, Number>("May", 5));
        series.setName("Rain");

        chart.getData().add(series);
        chart.getData().add(series2);
        chart.getData().add(series3);

    }




}