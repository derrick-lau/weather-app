package org.openjfx.model;

public class HighestTmax
{
    private Long id;
    private Double highestTmax;
    private Integer year;
    private Integer month;

    public HighestTmax(Double highestTmax, Integer year, Integer month) {
        this.highestTmax = highestTmax;
        this.year = year;
        this.month = month;
    }

    public HighestTmax() {
    }

    public Double getHighestTmax() {
        return highestTmax;
    }

    public void setHighestTmax(Double tmax) {
        this.highestTmax = highestTmax;
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

    @Override
    public String toString() {
        return  "  "+ month + " / " + year + "  " + highestTmax ;
    }
}
