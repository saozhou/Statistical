package com.zmst.Domain;

public class GFCoefficient {
    private Integer gfid;

    private String place;

    private String year;

    private Double avspend;//旅游人均消费

    private Double spday;//旅游消费天数

    private Double cpaspend;//城乡居民人均消费

    private Double lipeople;//常住人口

    private Double ysday;//年均消费天数

    private Double fsta;//f系数

    private Double gsta;//g系数

    private Double tpsum;//游客总数

    public Integer getGfid() {
        return gfid;
    }

    public void setGfid(Integer gfid) {
        this.gfid = gfid;
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

    public Double getAvspend() {
        return avspend;
    }

    public void setAvspend(Double avspend) {
        this.avspend = avspend;
    }

    public Double getSpday() {
        return spday;
    }

    public void setSpday(Double spday) {
        this.spday = spday;
    }

    public Double getCpaspend() {
        return cpaspend;
    }

    public void setCpaspend(Double cpaspend) {
        this.cpaspend = cpaspend;
    }

    public Double getLipeople() {
        return lipeople;
    }

    public void setLipeople(Double lipeople) {
        this.lipeople = lipeople;
    }

    public Double getYsday() {
        return ysday;
    }

    public void setYsday(Double ysday) {
        this.ysday = ysday;
    }

    public Double getFsta() {
        return fsta;
    }

    public void setFsta(Double fsta) {
        this.fsta = fsta;
    }

    public Double getGsta() {
        return gsta;
    }

    public void setGsta(Double gsta) {
        this.gsta = gsta;
    }

    public Double getTpsum() {
        return tpsum;
    }

    public void setTpsum(Double tpsum) {
        this.tpsum = tpsum;
    }
}