package com.zmst.Domain;

public class AllCodeDictionary {
    private Integer inid;

    private String incode;

    private String inname;

    public Integer getInid() {
        return inid;
    }

    public void setInid(Integer inid) {
        this.inid = inid;
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
}