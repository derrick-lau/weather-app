package org.openjfx.services;

import org.openjfx.model.MonthlyWeather;
import org.openjfx.repository.IReadFiles;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;


public class ReadCsv implements IReadFiles
{


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

        BufferedReader fileReader = readFileSetup(csv);

        //Map a row to a bean
        List<MonthlyWeather> weathers = mapRowToBean(fileReader);

        //Add a station name to a weather object
        addFileNameToObj(weathers, fileName);

        return weathers;
    }





    //////////////////////////Business Logic/////////////////////////////////////////////////////////////////////

    private BufferedReader readFileSetup(String csv)
    {

        FileReader fileReader = null;
        try
        {
            fileReader = new FileReader(csv);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(fileReader);

        return reader;
    }

    private List<MonthlyWeather> mapRowToBean (BufferedReader reader)
    {

        List<MonthlyWeather> monthlyWeathers = new ArrayList<>();
        String line;
        try
        {
            while ((line = reader.readLine()) != null)
            {
                String[] row = line.split(",");
                monthlyWeathers.add(new MonthlyWeather(
                        Integer.parseInt(row[0]),
                        Integer.parseInt(row[1]),
                        Double.parseDouble(row[2]),
                        Double.parseDouble(row[3]),
                        Integer.parseInt(row[4]),
                        Double.parseDouble(row[5])
                        ));
            }
        }

        catch (IOException e)

        {
            e.printStackTrace();
        }

        return monthlyWeathers;
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
