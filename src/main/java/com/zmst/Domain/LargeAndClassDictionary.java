package com.zmst.Domain;

public class LargeAndClassDictionary {
    private Integer clid;

    private String lacode;

    private String clcode;

    public Integer getClid() {
        return clid;
    }

    public void setClid(Integer clid) {
        this.clid = clid;
    }

    public String getLacode() {
        return lacode;
    }

    public void setLacode(String lacode) {
        this.lacode = lacode == null ? null : lacode.trim();
    }

    public String getClcode() {
        return clcode;
    }

    public void setClcode(String clcode) {
        this.clcode = clcode == null ? null : clcode.trim();
    }
}