package org.openjfx.model;

public class LowestTmin
{
    private Long id;
    private Double lowestTmin;
    private Integer year;
    private Integer month;

    public LowestTmin(Double lowestTmin, Integer year, Integer month) {
        this.lowestTmin = lowestTmin;
        this.year = year;
        this.month = month;
    }

    public LowestTmin() {
    }

    public Double getLowestTmin() {
        return lowestTmin;
    }

    public void setLowestTminTmin(Double tmin) {
        this.lowestTmin = tmin;
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
        return  "  " + month + " / " + year + "  " + lowestTmin;
    }
}
