package com.zmst.Domain;

public class LandTax {
    private Integer ltid;

    private String year;

    private String place;

    private String lacode;

    private String smcode;

    private String smname;

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

    public String getSmcode() {
        return smcode;
    }

    public void setSmcode(String smcode) {
        this.smcode = smcode == null ? null : smcode.trim();
    }

    public String getSmname() {
        return smname;
    }

    public void setSmname(String smname) {
        this.smname = smname == null ? null : smname.trim();
    }

    public Double getLatax() {
        return latax;
    }

    public void setLatax(Double latax) {
        this.latax = latax;
    }
}