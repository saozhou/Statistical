package com.zmst.Domain;

public class TravelClassGdpContribute {
    private Integer tccid;

    private String year;

    private String place;

    private String clcode;

    private String clname;

    private Double gdp;

    private Double trgdp;

    private Double rate;

    public Integer getTccid() {
        return tccid;
    }

    public void setTccid(Integer tccid) {
        this.tccid = tccid;
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
}