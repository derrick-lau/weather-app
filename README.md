

## Setup & Installation

1.Open pom.xml file as a project with Intellij

2.Import existing project

3.please mark resources directory as Resources root




## An overview of program design and implementation.

This project used MVC structure with FXML. 
Most of of business logic is in service classes and provided by Factory class in order to loose coupling, keep the code DRY, and improve maintainability. 



```
openjfx/
├── controller/
│   ├── BaseMenuController
│   ├── OverviewController
│   ├── PrimaryController
│   ├── ReportController
│   └── StatisticController
├── model/
│   ├── AnnualWeather
│   ├── HighestTmax
│   ├── LowestTmin
│   └── MonthlyWeather
├── repository/
│   ├── IFileOut
│   ├── IFxml
│   ├── IReadFiles
│   ├── ISideMenuMath
│   └── IWeatherMath
├── services/   
│   ├── FileOut
│   ├── Fxml
│   ├── ReadFiles
│   ├── SideMenuMath
│   └── WeatherMath
├── App
├── Factory
```

```
public class Factory
{

    public static IFxml fxmlServices () {
        return new Fxml();
    }

    public static IReadFiles fileServices () {
        return new ReadCsv();
    }

    public static ISideMenuScene sideMenuController () { return new SideMenuScene();}

    public static IFileOut fileOut () { return new FileOut();}

    public static IWeatherMath weatherMath () { return new WeatherMath(); }
}
```





## A basic algorithmic description of the main elements.

First of all, the application read the data stored within all CSV files in resources directory using OverviewController which uses 'ReadCsv' service.
Then, the application presents the CSV data in a table on a grid pane with a search field which can filter the data by station.
Search fields allow users to search accurate data conveniently and they work well with tables.

Implementation:

The implementations for filtering and setting table initialized in Overview Controller.

```
public class OverviewCotroller implements Initializable
{

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

```




Next, the application allows users to view monthly data of weather by selecting year and station in a separated tab, which are implemented with two choiceBox and one button for view in 'StatisticController'
When 'viewChoices' onAction method is called, it will get a list of data using ReadCsv service based on the value of 2 choiceBox, and set the value on the chart.
The data is presented in a stacked Bar chart. Compared to other types of chart like line chart or area chart, bar charts which avoid overlapping the data are clearer.

Implementation:
```
public class StatisticController extends BaseMenuController
{

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
}

``` 

The application also allows users to generate a report containing a summary of annual data for each station:
To generate the annual data, a 'annualWeather' model is needed. 'ReportController' handles onAction 'save'.
When 'save' method is called, it will first get a nested list of all rows in the csv files, then it calculates those data to get a list of annual data using 'WeatherMath' service.
Lastly, it show a save dialog and get the desired directory and write the data on a text file using 'fileOut' service. 
 
Implementation:

```
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
            Factory.fileOut().writeTextsToFile(title, annualWeathers, file);
        }
    }
} 

```






## A brief user guide describing how major features can be used. 
First, run the application. The first view will be a directory that allows user to select either
'view statistic' or 'get report' services. (Beyond the basic requirements)
<br />
<img src="https://i.imgur.com/DFtIW2a.png" alt="Image of directory" width="450"/>
 
In every views, users can utilize a side menu to change views. (Beyond the basic requirements)
<br />
<img src="https://i.imgur.com/L2wLzx2.png" alt="Image of side menu" width="450"/>
  
In 'Statistic' view, users can select 'overview' tab and get a list weather data in 2019 on a table. Users can search the data by station (Beyond the basic requirements).
<br />
<img src="https://i.imgur.com/4gMCeDP.png" alt="Image of Overview" width="450"/>
 
In the same view, users can also select 'Monthly Records'. Then select a 'station' and a 'year' and click 'view'. The chosen data will be presented on the stacked bar chart below the choice boxes.
<br />
<img src="https://i.imgur.com/gdqCYDQ.png" alt="Image of Monthly Records" width="450"/>
 
In 'Report' view, users can click 'Generate' button and choose a directory to save the report generated in the format as the description said.
<br />
<img src="https://i.imgur.com/wRIab3m.png" alt="Image of Report" width="450"/>
 
