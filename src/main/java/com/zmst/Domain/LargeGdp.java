package com.zmst.Domain;

public class LargeGdp {
    private Integer lcid;

    private String year;

    private String place;

    private String lacode;

    private String laname;

    private Double lagdp;

    public Integer getLcid() {
        return lcid;
    }

    public void setLcid(Integer lcid) {
        this.lcid = lcid;
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

    public Double getLagdp() {
        return lagdp;
    }

    public void setLagdp(Double lagdp) {
        this.lagdp = lagdp;
    }
}