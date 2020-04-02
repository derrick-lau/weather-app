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
import org.openjfx.model.MonthlyWeather;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class OverviewCotroller implements Initializable
{

    @FXML
    private TextField filterField;
    @FXML private TableView<MonthlyWeather> tableView;
    @FXML private TableColumn<MonthlyWeather, String> station;
    @FXML private TableColumn<MonthlyWeather, String> month;
    @FXML private TableColumn<MonthlyWeather, String> tmax;
    @FXML private TableColumn<MonthlyWeather, String> tmin;
    @FXML private TableColumn<MonthlyWeather, String> af;
    @FXML private TableColumn<MonthlyWeather, String> rain;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ObservableList<MonthlyWeather> observableList = FXCollections.observableArrayList();

        //Setup Table
        setCellValues();

        // Add values to obsList
        addToObsList(observableList);

        FilteredList<MonthlyWeather> filteredList = new FilteredList<>(observableList, b -> true);
        initfilerField(filteredList);

        // Bind the SortedList comparator to the TableView comparator
        SortedList<MonthlyWeather> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedList);

    }


    ///////////////////////////////////Business Logic//////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addToObsList(ObservableList<MonthlyWeather> observableList)
    {

        List<List<MonthlyWeather>> weatherLists = new ArrayList<>(Factory.fileServices().readFiles(Factory.fileServices().getResourcesPath("org/openjfx/__MACOSX")));
        List<MonthlyWeather> weathers = weatherLists.stream().flatMap(List::stream).collect(Collectors.toList());
        for(int i = 0; i < weathers.size(); i++)
        {
            if(weathers.get(i).getYear() == 2019)
            {
                observableList.add(weathers.get(i));
            }
        }
    }

    private void setCellValues()
    {
        station.setCellValueFactory(new PropertyValueFactory<>("station"));
        month.setCellValueFactory(new PropertyValueFactory<>("month"));
        tmax.setCellValueFactory(new PropertyValueFactory<>("tmax"));
        tmin.setCellValueFactory(new PropertyValueFactory<>("tmin"));
        af.setCellValueFactory(new PropertyValueFactory<>("af"));
        rain.setCellValueFactory(new PropertyValueFactory<>("rain"));
    }

    private void initfilerField (FilteredList<MonthlyWeather> filteredList)
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
