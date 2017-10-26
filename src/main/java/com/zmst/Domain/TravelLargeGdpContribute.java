package com.zmst.Domain;

public class TravelLargeGdpContribute {
    private Integer conid;

    private String lacode;

    private String laname;

    private Double gdp;

    private Double trgdp;

    private Double rate;

    private String year;

    private String place;

    public Integer getConid() {
        return conid;
    }

    public void setConid(Integer conid) {
        this.conid = conid;
    }

    public String getLacode() {
        return lacode;
    }

    public void setLacode(String lacode) {
        this.lacode = lacode == null ? null : lacode.trim();
    }

    public String getLaname() {
        return laname;
    }

    public void setLaname(String laname) {
        this.laname = laname == null ? null : laname.trim();
    }

    public Double getGdp() {
        return gdp;
    }

    public void setGdp(Double gdp) {
        this.gdp = gdp;
    }

    public Double getTrgdp() {
        return trgdp;
    }

    public void setTrgdp(Double trgdp) {
        this.trgdp = trgdp;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
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
}