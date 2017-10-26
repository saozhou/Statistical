package com.zmst.Domain;

public class TravelLargeTaxContribute {
    private Integer ttcid;

    private String year;

    private String place;

    private String lacode;

    private String laname;

    private Double tax;

    private Double trtax;

    private Double rate;

    public Integer getTtcid() {
        return ttcid;
    }

    public void setTtcid(Integer ttcid) {
        this.ttcid = ttcid;
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

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTrtax() {
        return trtax;
    }

    public void setTrtax(Double trtax) {
        this.trtax = trtax;
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