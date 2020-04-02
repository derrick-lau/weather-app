package org.openjfx.repository;


import org.openjfx.model.MonthlyWeather;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface IReadFiles
{
    String getResourcesPath(String name);
    List<String> getFilteredFileNames(String path);
    List<MonthlyWeather> readFile (String filePath, String dataAddInFile);
    List<List<MonthlyWeather>> readFiles (String folderPath);
    List<MonthlyWeather> readFilesByFileName (String path, String name);
}
