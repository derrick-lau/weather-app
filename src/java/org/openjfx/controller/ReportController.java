package org.openjfx.controller;

import org.openjfx.Factory;
import org.openjfx.controller.abstractions.AController;
import org.openjfx.model.Weather;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReportController extends AController
{
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        Factory.sideMenuController().sideMenuInitialization(mainPane, sidePane, menuButton);
        try
        {
            Factory.weatherMath().calculate(getWeatherLists());
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
    }

    private List<List<Weather>> getWeatherLists () throws URISyntaxException, IOException
    {
        return Factory.fileServices().readFiles(Factory.fileServices().getResourcesPath("org/openjfx/__MACOSX"));
    }

//    public void directoryChooser() {
//        FileChooser chooser = new FileChooser();
//        chooser.setTitle("Choose location To Save Report"):
//        File selectedFile = null;
//        while(selectedFile== null){
//            selectedFile = chooser.showSaveDialog(null);
//        }
//
//        File file = new File(selectedFile);
//        PrintWriter outFile = null;
//        try {
//            outFile = new PrintWriter(file);
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        for(int i = 0; i<table.getItems().size(); i++){
//            outFile.println(table.getItems().get(i).toString());
//        }
//        outFile.close();
//    }

}


