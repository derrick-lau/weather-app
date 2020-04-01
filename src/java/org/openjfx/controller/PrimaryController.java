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
import org.openjfx.model.Weather;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PrimaryController extends AController
{

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        Factory.sideMenuController().sideMenuInitialization(mainPane, sidePane, menuButton);

    }

}
