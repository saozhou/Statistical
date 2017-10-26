package com.zmst.Domain;

public class SubGdp {
    private Integer sgid;

    private String year;

    private String place;

    private String smcode;

    private String smname;

    private Double smgdp;

    public Integer getSgid() {
        return sgid;
    }

    public void setSgid(Integer sgid) {
        this.sgid = sgid;
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

    public Double getSmgdp() {
        return smgdp;
    }

    public void setSmgdp(Double smgdp) {
        this.smgdp = smgdp;
    }
}