package com.zmst.Domain;

public class GdpMiddleTable {
    private Integer gmid;

    private String year;

    private String place;

    private Double upli;

    private Double loli;

    private Double gdp;

    public Integer getGmid() {
        return gmid;
    }

    public void setGmid(Integer gmid) {
        this.gmid = gmid;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public Double getUpli() {
        return upli;
    }

    public void setUpli(Double upli) {
        this.upli = upli;
    }

    public Double getLoli() {
        return loli;
    }

    public void setLoli(Double loli) {
        this.loli = loli;
    }

    public Double getGdp() {
        return gdp;
    }

    public void setGdp(Double gdp) {
        this.gdp = gdp;
    }
}