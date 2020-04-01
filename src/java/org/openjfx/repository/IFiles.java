package org.openjfx.repository;


import org.openjfx.model.Weather;

import java.io.IOException;
import java.util.List;

public interface IFiles
{
    List<String> getFilteredFileNames(String path);
    List<Weather> readFile (String filePath, String dataAddInFile) throws IOException;
    List<Weather> readFiles (String folderPath) throws IOException;
    List<Weather> readFilesByFileName (String path, String name)throws IOException;
}
