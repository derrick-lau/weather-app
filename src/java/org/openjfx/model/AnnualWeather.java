package org.openjfx.model;

public class AnnualWeather
{
    private Long id;
    private Integer stationId;
    private String station;
    private HighestTmax highestTmax;
    private LowestTmin lowestTmin;
    private Integer avgAf;
    private Double avgRainfall;

    public AnnualWeather(Integer stationId, String station, HighestTmax highestTmax, LowestTmin lowestTmin, Integer avgAf, Double avgRainfall) {
        this.stationId = stationId;
        this.station = station;
        this.highestTmax = highestTmax;
        this.lowestTmin = lowestTmin;
        this.avgAf = avgAf;
        this.avgRainfall = avgRainfall;
    }

    public AnnualWeather() {
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public HighestTmax getHighestTmax() {
        return highestTmax;
    }

    public void setHighestTmax(HighestTmax highestTmax) {
        this.highestTmax = highestTmax;
    }

    public LowestTmin getLowestTmin() {
        return lowestTmin;
    }

    public void setLowestTmin(LowestTmin lowestTmin) {
        this.lowestTmin = lowestTmin;
    }

    public Integer getAvgAf() {
        return avgAf;
    }

    public void setAvgAf(Integer avgAf) {
        this.avgAf = avgAf;
    }

    public Double getAvgRainfall() {
        return avgRainfall;
    }

    public void setAvgRainfall(Double avgRainfall) {
        this.avgRainfall = avgRainfall;
    }

    @Override
    public String toString() {
        return "==========================\n\n" +
                "Number:" + stationId +'\n' +
                "Station:" + station + '\n' +
                "Highest Temperature:" + highestTmax.toString() + '\n' +
                "Lowest Temperature:" + lowestTmin.toString() + '\n' +
                "average Annual Af:" + "  " + avgAf + '\n' +
                "Average Annual Rainfall:" + "  " + avgRainfall + "\n\n" +
                "=========================\n\n\n\n";
    }
}
