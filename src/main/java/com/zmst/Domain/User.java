package com.zmst.Domain;

public class User {
    private Integer usid;

    private String usname;

    private String usplace;

    private String uspassword;

    private Integer uspower;

    private Integer usstatus;

    private Integer dainsert;

    private Integer dasearch;

    private Integer dacheck;

    private Integer damath;

    public Integer getUsid() {
        return usid;
    }

    public void setUsid(Integer usid) {
        this.usid = usid;
    }

    public String getUsname() {
        return usname;
    }

    public void setUsname(String usname) {
        this.usname = usname == null ? null : usname.trim();
    }

    public String getUsplace() {
        return usplace;
    }

    public void setUsplace(String usplace) {
        this.usplace = usplace == null ? null : usplace.trim();
    }

    public String getUspassword() {
        return uspassword;
    }

    public void setUspassword(String uspassword) {
        this.uspassword = uspassword == null ? null : uspassword.trim();
    }

    public Integer getUspower() {
        return uspower;
    }

    public void setUspower(String uspower) {
        this.uspower = Integer.valueOf(uspower);
    }

    public Integer getUsstatus() {
        return usstatus;
    }

    public void setUsstatus(Integer usstatus) {
        this.usstatus = usstatus;
    }

    public Integer getDainsert() {
        return dainsert;
    }

    public void setDainsert(Integer dainsert) {
        this.dainsert = dainsert;
    }

    public Integer getDasearch() {
        return dasearch;
    }

    public void setDasearch(Integer dasearch) {
        this.dasearch = dasearch;
    }

    public Integer getDacheck() {
        return dacheck;
    }

    public void setDacheck(Integer dacheck) {
        this.dacheck = dacheck;
    }

    public Integer getDamath() {
        return damath;
    }

    public void setDamath(Integer damath) {
        this.damath = damath;
    }
}