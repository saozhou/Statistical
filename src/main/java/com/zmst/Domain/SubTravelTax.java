package com.zmst.Domain;

public class SubTravelTax {
    private Integer sttid;

    private String year;

    private String place;

    private String smcode;

    private String smname;

    private Double sttax;

    private String lacode;

    public Integer getSttid() {
        return sttid;
    }

    public void setSttid(Integer sttid) {
        this.sttid = sttid;
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

    public Double getSttax() {
        return sttax;
    }

    public void setSttax(Double sttax) {
        this.sttax = sttax;
    }

    public String getLacode() {
        return lacode;
    }

    public void setLacode(String lacode) {
        this.lacode = lacode == null ? null : lacode.trim();
    }
}