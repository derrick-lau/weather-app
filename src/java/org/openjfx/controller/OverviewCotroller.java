package org.openjfx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.openjfx.Factory;
import org.openjfx.model.AnnualWeather;
import org.openjfx.model.MonthlyWeather;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OverviewCotroller implements Initializable
{

    @FXML
    private TextField filterField;
    @FXML private TableView<AnnualWeather> tableView;
    @FXML private TableColumn<AnnualWeather, String> station;
    @FXML private TableColumn<AnnualWeather, String> tmax;
    @FXML private TableColumn<AnnualWeather, String> tmin;
    @FXML private TableColumn<AnnualWeather, String> af;
    @FXML private TableColumn<AnnualWeather, String> rain;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ObservableList<AnnualWeather> observableList = FXCollections.observableArrayList();

        //Setup Table
        setCellValues();

        // Add values to obsList
        addToObsList(observableList);

        FilteredList<AnnualWeather> filteredList = new FilteredList<>(observableList, b -> true);
        initfilerField(filteredList);

        // Bind the SortedList comparator to the TableView comparator
        SortedList<AnnualWeather> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedList);

    }


    ///////////////////////////////////Business Logic//////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addToObsList(ObservableList<AnnualWeather> observableList)
    {

        List<List<MonthlyWeather>> weatherLists = Factory.fileServices().readFiles(Factory.fileServices().getResourcesPath());
        List<AnnualWeather> annualWeatherList = Factory.weatherMath().getCalculatedAnnualWeathers(weatherLists, 2019);
        observableList.addAll(annualWeatherList);


    }

    private void setCellValues()
    {
        station.setCellValueFactory(new PropertyValueFactory<>("station"));
        tmax.setCellValueFactory(new PropertyValueFactory<>("highestTmaxNumber"));
        tmin.setCellValueFactory(new PropertyValueFactory<>("lowestTminNumber"));
        af.setCellValueFactory(new PropertyValueFactory<>("totalAf"));
        rain.setCellValueFactory(new PropertyValueFactory<>("totalRainfall"));
    }

    private void initfilerField (FilteredList<AnnualWeather> filteredList)
    {
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredList.setPredicate(weather -> {

                if (newValue == null || newValue.isEmpty())
                    return true;

                String lowerCase = newValue.toLowerCase();

                if (weather.getStation().toLowerCase().indexOf(lowerCase) != -1 )
                    return true;
                else
                    return false;
            });
        });
    }
}
