package com.zmst.Domain;

public class TravelIndustryTaxContribute {
    private Integer titid;

    private String year;

    private String place;

    private String incode;

    private String inname;

    private Double tax;

    private Double trtax;

    private Double rate;

    public Integer getTitid() {
        return titid;
    }

    public void setTitid(Integer titid) {
        this.titid = titid;
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