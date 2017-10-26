package com.zmst.Domain;

public class LargeTravelTax {
    private Integer lttid;

    private String year;

    private String place;

    private String lacode;

    private String laname;

    private Double lttax;

    public Integer getLttid() {
        return lttid;
    }

    public void setLttid(Integer lttid) {
        this.lttid = lttid;
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

    public Double getLttax() {
        return lttax;
    }

    public void setLttax(Double lttax) {
        this.lttax = lttax;
    }
}