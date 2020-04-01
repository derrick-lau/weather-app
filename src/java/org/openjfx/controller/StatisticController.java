package org.openjfx.controller;


import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import org.openjfx.Factory;
import org.openjfx.controller.abstractions.AController;
import org.openjfx.model.Weather;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;


public class StatisticController extends AController
{

    @FXML private StackedBarChart<String, Number> stackedBarChart;
    @FXML private AreaChart<String, Number> areaChart;
    @FXML private ScatterChart<String, Number> scatterChart;
    @FXML private ChoiceBox<String> choiceBoxStation;
    @FXML private ChoiceBox<String> choiceBoxYear;
    @FXML private Button viewButton;
    @FXML private Tab stackedBarChartTab;
    @FXML private Tab scatteredChartTab;
    @FXML private Tab areaChartTab;

    private XYChart.Series<String, Number> tmax = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> tmin = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> af = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> rain = new XYChart.Series<String, Number>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Factory.sideMenuController().sideMenuInitialization(mainPane, sidePane, menuButton);

        //init choiceBox
        try
        {
            String defaultStation = "Aberporth";
            String defaultYear = "2019";
            initChoiceBox(getResourcesPath("org/openjfx/__MACOSX"),  defaultStation, defaultYear);
        } catch (URISyntaxException | IOException e)
        {
            e.printStackTrace();
        }

        //init
        initViewButton();
        initChartLegends();
        initTabs();
    }

    //viewButton #onAction
    private void viewChoices(ChoiceBox<String> choiceBoxStation, ChoiceBox<String> choiceBoxYear) throws IOException, URISyntaxException
    {
        String folder = getResourcesPath("org/openjfx/__MACOSX");
        String chosenStation = choiceBoxStation.getValue().toLowerCase();
        String chosenYear = choiceBoxYear.getValue();
        addChosenDataToSeries(folder, chosenStation, chosenYear);
        addSeriesToXYChartBySelectTab();
    }


    /////////////////////////Business Logic///////////////////////////////////////////////////////////////////////////////////

    private void initTabs ()
    {
        handleTabsChange(stackedBarChartTab, stackedBarChart);
        handleTabsChange(areaChartTab, areaChart);
        handleTabsChange(scatteredChartTab, scatterChart);
    }

    private void handleTabsChange (Tab tab, XYChart chart)
    {
        tab.setOnSelectionChanged(e -> {
            if (tab.isSelected()) {
                addSeriesToXYChart(chart);
            }
        });
    }

    private void addSeriesToXYChartBySelectTab()
    {
        if (areaChartTab.isSelected()) {
            addSeriesToXYChart(areaChart);
        } else if (scatteredChartTab.isSelected()) {
            addSeriesToXYChart(scatterChart);
        } else {
            addSeriesToXYChart(stackedBarChart);
        }
    }

    private void initViewButton()
    {
        viewButton.setOnAction(e -> {
            try
            {
                viewChoices(choiceBoxStation, choiceBoxYear);
            } catch (IOException | URISyntaxException ex)
            {
                ex.printStackTrace();
            }
        });
    }

    private void initChartLegends()
    {
        tmax.setName("Mean Maximum Temperature");
        tmin.setName("Mean Minimum Temperature");
        af.setName("Days of Air Frosts");
        rain.setName("Total Rainfall");
    }

    private void initChoiceBox(String dataFolder, String defaultStation, String defaultYear) throws IOException
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

    private void addChosenDataToSeries (String dataPath, String chosenStation, String chosenYear) throws IOException
    {
        clearSeries();
        List<Weather> weatherList = Factory.fileServices().readFilesByFileName(dataPath, chosenStation);

        int month = 1;
        for (Weather weather: weatherList)
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