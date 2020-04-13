package org.openjfx.model;

public class AnnualWeather
{
    private Long id;
    private Integer stationId;
    private String station;
    private HighestTmax highestTmax;
    private LowestTmin lowestTmin;
    private Double highestTmaxNumber;
    private Double lowestTminNumber;
    private Integer avgAf;
    private Double avgRainfall;
    private Integer totalAf;
    private Double totalRainfall;

    public AnnualWeather(Integer stationId, String station, Double highestTmaxNumber, Double lowestTminNumber, HighestTmax highestTmax, LowestTmin lowestTmin, Integer totalAf, Double totalRainfall, Integer avgAf, Double avgRainfall) {
        this.stationId = stationId;
        this.station = station;
        this.highestTmaxNumber = highestTmaxNumber;
        this.lowestTminNumber = lowestTminNumber;
        this.highestTmax = highestTmax;
        this.lowestTmin = lowestTmin;
        this.avgAf = avgAf;
        this.avgRainfall = avgRainfall;
        this.totalAf = totalAf;
        this.totalRainfall = totalRainfall;
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

    public Integer getTotalAf() {
        return totalAf;
    }

    public void setTotalAf(Integer totalAf) {
        this.totalAf = totalAf;
    }

    public Double getTotalRainfall() {
        return totalRainfall;
    }

    public void setTotalRainfall(Double totalRainfall) {
        this.totalRainfall = totalRainfall;
    }


    public Double getHighestTmaxNumber() {
        return highestTmaxNumber;
    }

    public void setHighestTmaxNumber(Double highestTmaxNumber) {
        this.highestTmaxNumber = highestTmaxNumber;
    }

    public Double getLowestTminNumber() {
        return lowestTminNumber;
    }

    public void setLowestTminNumber(Double lowestTminNumber) {
        this.lowestTminNumber = lowestTminNumber;
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
