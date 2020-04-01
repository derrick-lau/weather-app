package org.openjfx.services;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.openjfx.model.Weather;
import org.openjfx.repository.IFiles;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class CsvFile implements IFiles
{
    ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();


    @Override
    public List<Weather> readFiles (String folderPath) throws IOException
    {
        File[] csvs = getFiles(folderPath);
        List<List<Weather>> weatherLists = new ArrayList<>();

        //Add multiple weather in multiple csvs into a list
        for(File csv: csvs)
        {
            weatherLists.add(readFile(csv.getPath(), getFilteredFileName(csv)));
        }

        //Flat nested list and return it
        return weatherLists.stream().flatMap(List::stream).collect(Collectors.toList());
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
    public List<Weather> readFilesByFileName (String path, String name)throws IOException
    {
        List<Weather> weathers = new ArrayList<>();

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
    public List<Weather> readFile (String csv, String fileName) throws IOException
    {
        //Setup
        strategy.setType(Weather.class);
        String[] columns = new String[] {"year", "month", "tmax", "tmin", "af", "rain"};
        strategy.setColumnMapping(columns);
        FileReader fileReader = new FileReader(csv);
        CsvToBean<Weather> csvToBean = null;

        //Map a row to a bean
        if (fileReader.read() > 0)
        {
            csvToBean = new CsvToBeanBuilder<Weather>(fileReader)
                    .withMappingStrategy(strategy)
                    .withType(Weather.class)
                    .withSeparator(',')
                    .build();
        } else {

            return new ArrayList<Weather>();
        }

        //Parse the bean
        List<Weather> weathers = csvToBean.parse();

        //Add a station name to a weather object
        addFileNameToObj(weathers, fileName);
        return weathers;
    }

    private void addFileNameToObj(List<Weather> weathers, String fileName)
    {
        for(Weather weather: weathers)
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
