package com.zmst.Domain;

public class ClassGdp {
    private Integer cgid;

    private String year;

    private String place;

    private String clcode;

    private String clname;

    private Double clgdp;

    public Integer getCgid() {
        return cgid;
    }

    public void setCgid(Integer cgid) {
        this.cgid = cgid;
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

    public Double getClgdp() {
        return clgdp;
    }

    public void setClgdp(Double clgdp) {
        this.clgdp = clgdp;
    }
}