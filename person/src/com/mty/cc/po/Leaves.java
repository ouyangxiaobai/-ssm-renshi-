package com.mty.cc.po;


public class Leaves {

    private String id;
    private String uid;
    private String day;
    private String ptime;
    private String status;

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
    public void setDay(String value) {
        this.day = value;
    }
    public String getDay() {
       return this.day;
    }
    public void setPtime(String value) {
        this.ptime = value;
    }
    public String getPtime() {
       return this.ptime;
    }
    public void setStatus(String value) {
        this.status = value;
    }
    public String getStatus() {
       return this.status;
    }
}
