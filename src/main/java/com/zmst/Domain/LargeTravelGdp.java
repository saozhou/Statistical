package com.zmst.Domain;

public class LargeTravelGdp {
    private Integer ltgid;

    private String year;

    private String place;

    private String lacode;

    private String laname;

    private Double ltgdp;

    public Integer getLtgid() {
        return ltgid;
    }

    public void setLtgid(Integer ltgid) {
        this.ltgid = ltgid;
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

    public Double getLtgdp() {
        return ltgdp;
    }

    public void setLtgdp(Double ltgdp) {
        this.ltgdp = ltgdp;
    }
}