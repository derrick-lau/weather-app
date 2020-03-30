/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx.model;

import javafx.beans.property.IntegerPropertyBase;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Cool IT help
 */
public class Weather {

    private SimpleStringProperty station;
    private SimpleIntegerProperty year;
    private SimpleIntegerProperty month;
    private SimpleDoubleProperty tmax;
    private SimpleDoubleProperty tmin;
    private SimpleIntegerProperty af;
    private SimpleDoubleProperty rain;


    public Weather(String station, Integer year, Integer month, Double tmax, Double tmin, Integer af, Double rain) {

        this.station = new SimpleStringProperty(station);
        this.year = new SimpleIntegerProperty(year);
        this.month = new SimpleIntegerProperty(month);
        this.tmax = new SimpleDoubleProperty(tmax);
        this.tmin = new SimpleDoubleProperty(tmin);
        this.af = new SimpleIntegerProperty(af);
        this.rain = new SimpleDoubleProperty(rain);
    }

    public Weather() {
    }

    public String getStation() {
        return station.get();
    }

    public SimpleStringProperty stationProperty() {
        return station;
    }

    public void setStation(String station) {
        this.station.set(station);
    }

    public int getYear() {
        return year.get();
    }

    public SimpleIntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public int getMonth() {
        return month.get();
    }

    public SimpleIntegerProperty monthProperty() {
        return month;
    }

    public void setMonth(int month) {
        this.month.set(month);
    }

    public double getTmax() {
        return tmax.get();
    }

    public SimpleDoubleProperty tmaxProperty() {
        return tmax;
    }

    public void setTmax(double tmax) {
        this.tmax.set(tmax);
    }

    public double getTmin() {
        return tmin.get();
    }

    public SimpleDoubleProperty tminProperty() {
        return tmin;
    }

    public void setTmin(double tmin) {
        this.tmin.set(tmin);
    }

    public int getAf() {
        return af.get();
    }

    public SimpleIntegerProperty afProperty() {
        return af;
    }

    public void setAf(int af) {
        this.af.set(af);
    }

    public double getRain() {
        return rain.get();
    }

    public SimpleDoubleProperty rainProperty() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain.set(rain);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "month=" + month +
                '}';
    }
}
    
