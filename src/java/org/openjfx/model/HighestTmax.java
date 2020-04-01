package org.openjfx.model;

public class HighestTmax
{
    private Double HighestTmax;
    private Integer year;
    private Integer month;

    public HighestTmax(Double HighestTmax, Integer year, Integer month) {
        this.HighestTmax = HighestTmax;
        this.year = year;
        this.month = month;
    }

    public HighestTmax() {
    }

    public Double getHighestTmax() {
        return HighestTmax;
    }

    public void setHighestTmax(Double tmax) {
        this.HighestTmax = HighestTmax;
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
}
