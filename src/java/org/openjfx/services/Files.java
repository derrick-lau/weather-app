package org.openjfx.services;

import org.openjfx.Factory;
import org.openjfx.repository.IFiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Files implements IFiles {



    @Override
    public List<String> getFileNames(String path)
    {
        List<String> entities = new ArrayList<String>();

        File[] files = getFiles(path);

        addFilesToList(files, entities);

        return entities;
    }

    public void readFile (String searchTerm, String path)
    {

        try
        {
            Scanner scanner = new Scanner(new File(path));
            scanner.useDelimiter("[,\n]");


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private File[] getFiles(String path)
    {
        return new File(path).listFiles((dir, name) -> name.endsWith(".csv"));
    }


    private void addFilesToList(File[] files, List<String> entities)
    {
        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                fileName = fileName.substring(0, fileName.lastIndexOf('.'));
                fileName = fileName.replaceAll("[_.]","");
                entities.add(fileName);
            }
        }
    }



}
