package com.mty.cc.po;


public class Clock {

    private String id;
    private String uid;
    private String ptime;
    private String qtime;

    private String uname;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setId(String value) {
        this.id = value;
    }
    public String getId() {
       return this.id;
    }
    public void setUid(String value) {
        this.uid = value;
    }
    public String getUid() {
       return this.uid;
    }
    public void setPtime(String value) {
        this.ptime = value;
    }
    public String getPtime() {
       return this.ptime;
    }

    public String getQtime() {
        return qtime;
    }

    public void setQtime(String qtime) {
        this.qtime = qtime;
    }
}
