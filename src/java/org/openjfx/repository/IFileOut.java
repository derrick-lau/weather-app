package org.openjfx.repository;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public interface IFileOut
{
    void writeTextsToFile(String title, List<String> contents, File file);
}
