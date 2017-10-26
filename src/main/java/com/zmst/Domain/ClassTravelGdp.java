package com.zmst.Domain;

public class ClassTravelGdp {
    private Integer ctgid;

    private String year;

    private String place;

    private String clcode;

    private String clname;

    private Double ctgdp;

    public Integer getCtgid() {
        return ctgid;
    }

    public void setCtgid(Integer ctgid) {
        this.ctgid = ctgid;
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

    public String getClcode() {
        return clcode;
    }

    public void setClcode(String clcode) {
        this.clcode = clcode == null ? null : clcode.trim();
    }

    public String getClname() {
        return clname;
    }

    public void setClname(String clname) {
        this.clname = clname == null ? null : clname.trim();
    }

    public Double getCtgdp() {
        return ctgdp;
    }

    public void setCtgdp(Double ctgdp) {
        this.ctgdp = ctgdp;
    }
}