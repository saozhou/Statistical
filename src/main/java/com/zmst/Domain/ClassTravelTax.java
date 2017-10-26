package com.zmst.Domain;

public class ClassTravelTax {
    private Integer cttid;

    private String year;

    private String place;

    private String clcode;

    private String clname;

    private Double cttax;

    public Integer getCttid() {
        return cttid;
    }

    public void setCttid(Integer cttid) {
        this.cttid = cttid;
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

    public Double getCttax() {
        return cttax;
    }

    public void setCttax(Double cttax) {
        this.cttax = cttax;
    }
}