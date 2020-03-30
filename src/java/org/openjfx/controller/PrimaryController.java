package org.openjfx.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.openjfx.Factory;
import org.openjfx.controller.abstractions.AController;
import org.openjfx.controller.abstractions.IPrimaryController;
import org.openjfx.model.Weather;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController extends AController implements IPrimaryController
{

    @FXML private TextField filterField;
    @FXML private TableView<Weather> tableView;

    @FXML private TableColumn<Weather, String> station;
    @FXML private TableColumn<Weather, String> month;
    @FXML private TableColumn<Weather, String> tmax;
    @FXML private TableColumn<Weather, String> tmin;
    @FXML private TableColumn<Weather, String> af;
    @FXML private TableColumn<Weather, String> rain;




    private final ObservableList<Weather> observableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        Factory.sideMenuController().sideMenuInitialization(mainPane, sidePane, menuButton);

        // Add values to obsList
        setCellValues();
        Weather w1 = new Weather("Cardiff",2019, 1, 2.99, 3.44,10,6.99 );
        Weather w2 = new Weather("Cardiff",2019, 1, 2.99, 3.44,10,6.99 );
        Weather w3 = new Weather("noWhere",2019, 1, 2.99, 3.44,10,6.99 );
        Weather w4 = new Weather("somewhere",2019, 1, 2.99, 3.44,10,6.99 );

        observableList.addAll(w1,w2, w3, w4);

        // Init filteredList
        FilteredList<Weather> filteredList = new FilteredList<>(observableList, b -> true);

        // Filter changes using filterField
        filterChanges(filteredList);

        // Sort filteredList.
        SortedList<Weather> sortedData = new SortedList<>(filteredList);

        // Bind the SortedList comparator to the TableView comparator
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        // Add sorted data to the table.
        tableView.setItems(sortedData);


    }

    private void setCellValues ()
    {
        station.setCellValueFactory(new PropertyValueFactory<>("station"));
        month.setCellValueFactory(new PropertyValueFactory<>("month"));
        tmax.setCellValueFactory(new PropertyValueFactory<>("tmax"));
        tmin.setCellValueFactory(new PropertyValueFactory<>("tmin"));
        af.setCellValueFactory(new PropertyValueFactory<>("af"));
        rain.setCellValueFactory(new PropertyValueFactory<>("rain"));
    }

    private void filterChanges (FilteredList<Weather> filteredList)
    {
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredList.setPredicate(weather -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (weather.getStation().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;
                } else
                    return false;
            });
        });
    }
}
