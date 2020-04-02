

## Setup & Installation

1.Open pom.xml file as a project with Intellij

2.Import existing project

3.please mark resources directory as Resources root




## An overview of program design and implementation.

This project used MVC structure with FXML. 
Most of of business logic is in service classes and provided by Factor class in order to loose coupling, keep the code DRY, and improve maintainability. 



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






## A basic algorithmic description of the main elements.

First of all, the application read the data stored within all CSV files in resources directory using OverviewController which uses 'ReadCsv' service.
Then, the application presents the CSV data in a table on a grid pane with a search field which can filter the data by station.
Search fields allow users to search accurate data conveniently and they work well with tables.

Implementation:

The implementations for filtering and setting table initialized in Overview Controller.

```
public class OverviewCotroller implements Initializable
{

    @FXMLprivate TextField filterField;
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

```



GET A List of Lists of MonthlyWeather

```
public class ReadCsv implements IReadFiles
{
    ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();


    @Override
    public List<List<MonthlyWeather>> readFiles (String folderPath)
    {
        File[] csvs = getFiles(folderPath);
        List<List<MonthlyWeather>> weatherLists = new ArrayList<>();

        //Add multiple weather in multiple csvs into a list
        for(File csv: csvs)
        {
            weatherLists.add(readFile(csv.getPath(), getFilteredFileName(csv)));
        }

        //Flat nested list and return it
        return weatherLists;
    }
}
```

Add MonthlyWeather to a Observable list

```
public class OverviewCotroller implements Initializable
{

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
}

```
    
    



Next, the application allows users to view monthly data of weather by selecting year and station in a separated tab, which are implemented with two choiceBox and one button for view in 'StatisticController'
When 'viewChoices' onAction method is called, it will get a list of data using ReadCsv service based on the value of 2 choiceBox, and set the value on the chart.
The data is presented in a stacked Bar chart. Compared to other types of chart like line chart or area chart, bar charts which avoid overlapping the data are clearer.

Implementation:
```
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

public class FileOut implements IFileOut {

    @Override
    public void writeTextsToFile(String title, List<String> contents, File file)
    {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(title);
            for(String content: contents )
            {
                writer.println(content);
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
```






## A brief user guide describing how major features can be used. 
First, run the application. The first view will be a directory that allows user to select either
'view statistic' or 'get report' services. (Beyond the basic requirements)
 ![Image of directory](https://i.imgur.com/DFtIW2a.png) 
 
In every views, users can utilize a side menu to change views. (Beyond the basic requirements)
 ![Image of side menu](https://i.imgur.com/L2wLzx2.png)
  
In 'Statistic' view, users can select 'overview' tab and get a list weather data in 2019 on a table. Users can search the data by station (Beyond the basic requirements).
 ![Image of Overview](https://i.imgur.com/4gMCeDP.png)
 
In the same view, users can also select 'Monthly Records'. Then select a 'station' and a 'year' and click 'view'. The chosen data will be presented on the stacked bar chart below the choice boxes.
 ![Image of Monthly Records](https://i.imgur.com/gdqCYDQ.png)
 
In 'Report' view, users can click 'Generate' button and choose a directory to save the report generated in the format as the description said.
 ![Image of Report](https://i.imgur.com/wRIab3m.png) 
