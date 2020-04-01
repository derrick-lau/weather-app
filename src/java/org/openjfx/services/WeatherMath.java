package org.openjfx.services;

import org.openjfx.model.HighestTmax;
import org.openjfx.model.LowestTmin;
import org.openjfx.model.Weather;
import org.openjfx.repository.IWeatherMath;

import java.util.ArrayList;
import java.util.List;

public class WeatherMath implements IWeatherMath
{
    @Override
    public List<Weather> calculate(List<List<Weather>> weatherLists)
    {
        List<Weather> calcuatedWeathers = new ArrayList<>();

        int sequenceNumber = 0;

        for(List<Weather> weatherList: weatherLists)
        {
            HighestTmax highestTmax = getHighestTmax(weatherList);
            LowestTmin lowestTmin = getLowestTmin(weatherList);
            int annualAvgAf = getAnnualAvgAf(weatherList);
            Double annualAvgRain = getAnnualAvgRain(weatherList);


            String station = weatherList.get(sequenceNumber).getStation();
            Weather weatherToAdd = new Weather(sequenceNumber, station, annualAvgAf,annualAvgRain, highestTmax, lowestTmin);
            calcuatedWeathers.add(sequenceNumber, weatherToAdd);
            sequenceNumber++;
        }

        return calcuatedWeathers;
    }

    private Double getAnnualAvgRain(List<Weather> weatherList) {
        //    LongSummaryStatistics statistics = weather.stream()
//            .mapToLong(Weather::get)
//            .summaryStatistics();
        return 0.0;
    }

    private int getAnnualAvgAf(List<Weather> weatherList) {
        return 0;
    }

    private LowestTmin getLowestTmin(List<Weather> weatherList) {
        return new LowestTmin();
    }

    private HighestTmax getHighestTmax(List<Weather> weatherList)
    {


        return new HighestTmax();
    }
}
