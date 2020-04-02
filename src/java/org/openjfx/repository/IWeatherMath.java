package org.openjfx.repository;

import org.openjfx.model.MonthlyWeather;

import java.util.List;

public interface IWeatherMath
{
    List<String> getCalculatedStrings(List<List<MonthlyWeather>> weatherLists);
}
