package org.openjfx.model;

public class Weather {

    private String station;
    private String year;
    private String month;
    private Double tmax;
    private Double tmin;
    private int af;
    private Double rain;

    public Weather() {
    }

    public Weather(String station, String year, String month, Double tmax, Double tmin, int af, Double rain) {
        this.station = station;
        this.year = year;
        this.month = month;
        this.tmax = tmax;
        this.tmin = tmin;
        this.af = af;
        this.rain = rain;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
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

    public int getAf() {
        return af;
    }

    public void setAf(int af) {
        this.af = af;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
