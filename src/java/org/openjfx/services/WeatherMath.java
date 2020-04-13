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

                AnnualWeather annualWeather = getAnnualWeather(weatherList, sequenceNumber, station, 0);
                calculatedWeathers.add(annualWeather.toString());
                sequenceNumber++;
            }
        }

        return calculatedWeathers;
    }

    @Override
    public List<AnnualWeather> getCalculatedAnnualWeathers(List<List<MonthlyWeather>> weatherLists, int year)
    {

        List<AnnualWeather> calculatedWeathers = new ArrayList<>();

        int sequenceNumber = 1;

        for(List<MonthlyWeather> weatherList: weatherLists)
        {
            if(!weatherList.isEmpty()) {
                String station = weatherList.get(sequenceNumber).getStation();

                AnnualWeather annualWeather = getAnnualWeather(weatherList, sequenceNumber, station, year);

                calculatedWeathers.add(annualWeather);
                sequenceNumber++;
            }
        }

        return calculatedWeathers;
    }

    private AnnualWeather getAnnualWeather(List<MonthlyWeather> weatherList, int sequenceNumber, String station, int year) {

        int hit = 0;
        AnnualWeather annualWeather = new AnnualWeather(sequenceNumber, station, -1111.0, 1111.0, new HighestTmax(-1111.0, 0,0), new LowestTmin(1110.0, 0,0), 0,0.0,0, 0.0);
        for (MonthlyWeather weather: weatherList)
        {

            if (weather.getYear().equals(year) || year == 0) {
                hit++;
                // Sum all the data of rainfall and Af
                annualWeather.setTotalRainfall(annualWeather.getTotalRainfall() + weather.getRain());

                annualWeather.setTotalAf(annualWeather.getTotalAf() + weather.getAf());

                //compare and get highestT
                if(weather.getTmax() > annualWeather.getHighestTmax().getHighestTmax())
                {
                    annualWeather.setHighestTmaxNumber(weather.getTmax());
                    annualWeather.setHighestTmax(new HighestTmax(weather.getTmax(), weather.getYear(), weather.getMonth()));
                }

                //compare and get LowestT
                if(weather.getTmin() < annualWeather.getLowestTmin().getLowestTmin())
                {
                    annualWeather.setLowestTminNumber(weather.getTmin());
                    annualWeather.setLowestTmin(new LowestTmin(weather.getTmin(), weather.getYear(), weather.getMonth()));
                }
            }

        }



        if(hit != 0) {

            annualWeather.setAvgRainfall(annualWeather.getTotalRainfall()/12);
            annualWeather.setAvgAf(annualWeather.getTotalAf()/12);

        } else {

            return new AnnualWeather(sequenceNumber, "NO DATA FOUND in " + year + " at " + annualWeather.getStation(), 0.0, 0.0, new HighestTmax(0.0, 0,0), new LowestTmin(1110.0, 0,0), 0,0.0,0, 0.0);
        }


        return annualWeather;

    }

}
