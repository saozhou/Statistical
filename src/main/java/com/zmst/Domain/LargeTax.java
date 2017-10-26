package com.zmst.Domain;

public class LargeTax {
    private Integer ltid;

    private String year;

    private String place;

    private String lacode;

    private String laname;

    private Double latax;

    public Integer getLtid() {
        return ltid;
    }

    public void setLtid(Integer ltid) {
        this.ltid = ltid;
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

    public Double getLatax() {
        return latax;
    }

    public void setLatax(Double latax) {
        this.latax = latax;
    }
}