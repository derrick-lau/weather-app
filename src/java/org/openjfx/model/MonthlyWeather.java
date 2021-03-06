package org.openjfx.model;

public class MonthlyWeather {
    private Long id;
    private String station;
    private Integer year;
    private Integer month;
    private Double tmax;
    private Double tmin;
    private Integer af;
    private Double rain;

    public MonthlyWeather() {
    }

    public MonthlyWeather(Integer year, Integer month, Double tmax, Double tmin, Integer af, Double rain) {
        this.year = year;
        this.month = month;
        this.tmax = tmax;
        this.tmin = tmin;
        this.af = af;
        this.rain = rain;
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
