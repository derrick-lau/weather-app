package org.openjfx.services;

import org.openjfx.model.AnnualWeather;
import org.openjfx.model.MonthlyWeather;
import org.openjfx.repository.IWeatherMath;

import java.util.ArrayList;
import java.util.List;

public class WeatherMath implements IWeatherMath
{
    @Override
    public List<String> getCalculatedStrings(List<List<MonthlyWeather>> weatherLists)
    {
        List<String> calculatedWeathers = new ArrayList<>();

        int sequenceNumber = 0;

        for(List<MonthlyWeather> weatherList: weatherLists)
        {
//            HighestTmax highestTmax = getHighestTmax(weatherList);
//            LowestTmin lowestTmin = getLowestTmin(weatherList);
//            int annualAvgAf = getAnnualAvgAf(weatherList);
//            Double annualAvgRain = getAnnualAvgRain(weatherList);


            String station = weatherList.get(sequenceNumber).getStation();
            AnnualWeather weatherToAdd = new AnnualWeather();
            calculatedWeathers.add(sequenceNumber, weatherToAdd.toString());
            sequenceNumber++;
        }

        return calculatedWeathers;
    }

}
