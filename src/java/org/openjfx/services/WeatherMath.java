package org.openjfx.services;

import org.openjfx.model.AnnualWeather;
import org.openjfx.model.HighestTmax;
import org.openjfx.model.LowestTmin;
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

        int sequenceNumber = 1;

        for(List<MonthlyWeather> weatherList: weatherLists)
        {
            if(!weatherList.isEmpty()) {
                String station = weatherList.get(sequenceNumber).getStation();

                AnnualWeather annualWeather = getAnnualWeather(weatherList, sequenceNumber, station);
                calculatedWeathers.add(annualWeather.toString());
                sequenceNumber++;
            }
        }

        return calculatedWeathers;
    }

    private AnnualWeather getAnnualWeather(List<MonthlyWeather> weatherList, int sequenceNumber, String station) {

        AnnualWeather annualWeather = new AnnualWeather(sequenceNumber, station, new HighestTmax(0.0, 0,0), new LowestTmin(0.0, 0,0), 0, 0.0);
        for (MonthlyWeather weather: weatherList)
        {
            // Sum all the data of rainfall and Af
            annualWeather.setAvgRainfall(annualWeather.getAvgRainfall() + weather.getRain());

            annualWeather.setAvgAf(annualWeather.getAvgAf() + weather.getAf());

            //compare and get highestT
            if(weather.getTmax() > annualWeather.getHighestTmax().getHighestTmax())
            {
                annualWeather.setHighestTmax(new HighestTmax(weather.getTmax(), weather.getYear(), weather.getMonth()));
            }

            //compare and get LowestT
            if(weather.getTmin() < annualWeather.getLowestTmin().getLowestTmin())
            {
                annualWeather.setLowestTmin(new LowestTmin(weather.getTmax(), weather.getYear(), weather.getMonth()));
            }
        }

        annualWeather.setAvgRainfall(annualWeather.getAvgRainfall()/12);
        annualWeather.setAvgAf(annualWeather.getAvgAf()/12);

        return annualWeather;

    }

}
