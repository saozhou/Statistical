package com.zmst.Domain;

public class TravelIndustryGdpContribute {
    private Integer inid;

    private String year;

    private String place;

    private String incode;

    private String inname;

    private Double gdp;

    private Double trgdp;

    private Double rate;

    public Integer getInid() {
        return inid;
    }

    public void setInid(Integer inid) {
        this.inid = inid;
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

	public void deleteByYearPlace(String year2, String place2) {
		// TODO Auto-generated method stub
		
	}
}