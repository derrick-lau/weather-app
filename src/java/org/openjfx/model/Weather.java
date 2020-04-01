package org.openjfx.model;

public class Weather {
    private Integer stationId;
    private String station;
    private Integer year;
    private Integer month;
    private Double tmax;
    private Double tmin;
    private Integer af;
    private Double rain;
    private HighestTmax highestTmax;
    private LowestTmin lowestTmin;

    public Weather() {
    }

    public Weather(Integer stationId, String station, Integer af, Double rain, HighestTmax highestTmax, LowestTmin lowestTmin) {
        this.stationId = stationId;
        this.station = station;
        this.af = af;
        this.rain = rain;
        this.highestTmax = highestTmax;
        this.lowestTmin = lowestTmin;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getTmax() {
        return tmax;
    }

    public void setTmax(Double tmax) {
        this.tmax = tmax;
    }

    public Double getTmin() {
        return tmin;
    }

    public void setTmin(Double tmin) {
        this.tmin = tmin;
    }

    public Integer getAf() {
        return af;
    }

    public void setAf(Integer af) {
        this.af = af;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }
}
