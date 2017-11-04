package com.zmst.Domain;

public class GFReference {
    private Integer gftid;

    private String incode;

    private String inname;

    private String incoefficient;

    private String place;

    private String year;

    public Integer getGftid() {
        return gftid;
    }

    public void setGftid(Integer gftid) {
        this.gftid = gftid;
    }

    public String getIncode() {
        return incode;
    }

    public void setIncode(String incode) {
        this.incode = incode == null ? null : incode.trim();
    }

    public String getInname() {
        return inname;
    }

    public void setInname(String inname) {
        this.inname = inname == null ? null : inname.trim();
    }

    public String getIncoefficient() {
        return incoefficient;
    }

    public void setIncoefficient(String incoefficient) {
        this.incoefficient = incoefficient == null ? null : incoefficient.trim();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

	@Override
	public String toString() {
		return "GFReference [gftid=" + gftid + ", incode=" + incode + ", inname=" + inname + ", incoefficient="
				+ incoefficient + ", place=" + place + ", year=" + year + "]";
	}
    
}