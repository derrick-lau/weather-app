package org.openjfx.controller;

import org.openjfx.App;
import org.openjfx.controller.abstractions.IController;

import java.io.IOException;

abstract public class Controller implements IController
{
    @Override
    public void switchToStatistic() throws IOException {
        App.setScene("statistic");
    }

    @Override
    public void switchToPrimary() throws IOException {
        App.setScene("primary");
    }
}
