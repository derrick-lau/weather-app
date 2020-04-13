package org.openjfx.services;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.openjfx.model.MonthlyWeather;
import org.openjfx.repository.IReadFiles;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;


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

    @Override
    public String getResourcesPath() {
        URL resource = getClass().getClassLoader().getResource("org/openjfx/__MACOSX");
        String resources = "";
        try
        {
            resources = String.valueOf(Paths.get(resource.toURI()).toFile());
        } catch (URISyntaxException e)
        {
            e.printStackTrace();
        }

        return resources;
    }

    @Override
    public List<String> getFilteredFileNames(String folderPath)
    {
        List<String> fileNamesList = new ArrayList<String>();

        File[] fileNames = getFiles(folderPath);

        // Filter fileName's (., -, .csv) and add to fileNamesList
        addFilteredFileNamesToList(fileNames, fileNamesList);

        return fileNamesList;
    }

    @Override
    public List<MonthlyWeather> readFilesByFileName (String path, String name)
    {
        List<MonthlyWeather> weathers = new ArrayList<>();

        File[] files = getFiles(path);
        for (File file: files)
        {
            if(getFilteredFileName(file).toLowerCase().equals(name))
            {
                weathers = readFile(file.getPath(), name);
            }
        }

        return weathers;
    }


    @Override
    public List<MonthlyWeather> readFile (String csv, String fileName)
    {
        //Setup

        FileReader fileReader = readFileSetup(csv);

        //Map a row to a bean
        List<MonthlyWeather> weathers = mapRowToBean(fileReader);

        //Add a station name to a weather object
        addFileNameToObj(weathers, fileName);

        return weathers;
    }





    //////////////////////////Business Logic/////////////////////////////////////////////////////////////////////

    private FileReader readFileSetup(String csv)
    {
        //Setup
        strategy.setType(MonthlyWeather.class);
        String[] columns = new String[] {"year", "month", "tmax", "tmin", "af", "rain"};
        strategy.setColumnMapping(columns);
        FileReader fileReader = null;
        try
        {
            fileReader = new FileReader(csv);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return fileReader;
    }

    private List<MonthlyWeather> mapRowToBean (FileReader fileReader)
    {
        CsvToBean<MonthlyWeather> csvToBean = null;
        try
        {
            if (fileReader.read() > 0)
            {
                csvToBean = new CsvToBeanBuilder<MonthlyWeather>(fileReader)
                        .withMappingStrategy(strategy)
                        .withType(MonthlyWeather.class)
                        .withSeparator(',')
                        .build();
            } else {
                return new ArrayList<MonthlyWeather>();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return csvToBean.parse();
    }


    private void addFileNameToObj(List<MonthlyWeather> weathers, String fileName)
    {
        for(MonthlyWeather weather: weathers)
        {
            weather.setStation(fileName);
        }
    }

    private String getFilteredFileName (File file)
    {
        String fileName = file.getName();
        fileName = fileName.substring(0, fileName.lastIndexOf('.'));
        fileName = fileName.replaceAll("[_.]","");
        return fileName;
    }

    private File[] getFiles(String path)
    {
        File[] files = new File(path).listFiles((dir, name) -> name.endsWith(".csv"));
        Arrays.sort(files);
        return files;
    }

    private void addFilteredFileNamesToList(File[] files, List<String> fileList)
    {
        for (File file : files) {
            if (file.isFile()) {
                String fileName = getFilteredFileName(file);
                fileList.add(fileName);
            }
        }
    }
}
