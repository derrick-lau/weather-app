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
import org.openjfx.model.Weather;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class OverviewCotroller implements Initializable
{

    @FXML
    private TextField filterField;
    @FXML private TableView<Weather> tableView;
    @FXML private TableColumn<Weather, String> station;
    @FXML private TableColumn<Weather, String> month;
    @FXML private TableColumn<Weather, String> tmax;
    @FXML private TableColumn<Weather, String> tmin;
    @FXML private TableColumn<Weather, String> af;
    @FXML private TableColumn<Weather, String> rain;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ObservableList<Weather> observableList = FXCollections.observableArrayList();

        //Setup Table
        setCellValues();

        // Add values to obsList
        try
        {
            addToObsList(observableList);
        } catch (IOException | URISyntaxException e)
        {
            e.printStackTrace();
        }


        FilteredList<Weather> filteredList = new FilteredList<>(observableList, b -> true);
        initfilerField(filteredList);



        // Bind the SortedList comparator to the TableView comparator
        SortedList<Weather> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());


        tableView.setItems(sortedList);

    }


    ///////////////////////////////////Business Logic//////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addToObsList(ObservableList<Weather> observableList) throws IOException, URISyntaxException
    {

        List<List<Weather>> weatherLists = new ArrayList<>(Factory.fileServices().readFiles(Factory.fileServices().getResourcesPath("org/openjfx/__MACOSX")));
        List<Weather> weathers = weatherLists.stream().flatMap(List::stream).collect(Collectors.toList());
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
        station.setCellValueFactory(new PropertyValueFactory<Weather, String>("station"));
        month.setCellValueFactory(new PropertyValueFactory<Weather, String>("month"));
        tmax.setCellValueFactory(new PropertyValueFactory<Weather, String>("tmax"));
        tmin.setCellValueFactory(new PropertyValueFactory<Weather, String>("tmin"));
        af.setCellValueFactory(new PropertyValueFactory<Weather, String>("af"));
        rain.setCellValueFactory(new PropertyValueFactory<Weather, String>("rain"));
    }

    private void initfilerField (FilteredList<Weather> filteredList)
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
