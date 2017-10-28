package com.zmst.Domain;

public class SelfSearch {

	private String name;
	private String code;
	private Double tax;
	private Double gdp;
	private Double travetax;
	private Double travegdp;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public Double getGdp() {
		return gdp;
	}
	public void setGdp(Double gdp) {
		this.gdp = gdp;
	}
	public Double getTravetax() {
		return travetax;
	}
	public void setTravetax(Double travetax) {
		this.travetax = travetax;
	}
	public Double getTravegdp() {
		return travegdp;
	}
	public void setTravegdp(Double travegdp) {
		this.travegdp = travegdp;
	}
	public SelfSearch() {
		
	}
	@Override
	public String toString() {
		return "SelfSearch [name=" + name + ", code=" + code + ", tax=" + tax + ", gdp=" + gdp + ", travetax="
				+ travetax + ", travegdp=" + travegdp + "]";
	}
	
	
}
