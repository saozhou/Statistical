package com.zmst.Domain;

public class Gdp {
    private Integer gdpid;

    private String year;

    private String place;

    private String gdpcode;

    private Double gdp;

    private String gdpname;

    public Integer getGdpid() {
        return gdpid;
    }

    public void setGdpid(Integer gdpid) {
        this.gdpid = gdpid;
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

    public String getGdpcode() {
        return gdpcode;
    }

    public void setGdpcode(String gdpcode) {
        this.gdpcode = gdpcode == null ? null : gdpcode.trim();
    }

    public Double getGdp() {
        return gdp;
    }

    public void setGdp(Double gdp) {
        this.gdp = gdp;
    }

    public String getGdpname() {
        return gdpname;
    }

    public void setGdpname(String gdpname) {
        this.gdpname = gdpname == null ? null : gdpname.trim();
    }
}