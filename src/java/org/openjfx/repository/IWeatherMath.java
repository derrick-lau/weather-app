package org.openjfx.repository;

import org.openjfx.model.AnnualWeather;
import org.openjfx.model.MonthlyWeather;

import java.util.List;

public interface IWeatherMath
{
    List<String> getCalculatedStrings(List<List<MonthlyWeather>> weatherLists);
    List<AnnualWeather> getCalculatedAnnualWeathers(List<List<MonthlyWeather>> weatherLists, int year);
}
