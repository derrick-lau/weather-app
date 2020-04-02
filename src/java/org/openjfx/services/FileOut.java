package org.openjfx.services;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.openjfx.repository.IFileOut;


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
