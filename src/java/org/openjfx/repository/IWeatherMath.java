package org.openjfx.repository;

import org.openjfx.model.Weather;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface IWeatherMath
{
    List<Weather> calculate(List<List<Weather>> weatherLists);
}
