package com.mty.cc.po;


public class Works {

    private String id;
    private String uid;
    private String wtime;
    private String day;
    private String money;

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
    public void setWtime(String value) {
        this.wtime = value;
    }
    public String getWtime() {
       return this.wtime;
    }
    public void setDay(String value) {
        this.day = value;
    }
    public String getDay() {
       return this.day;
    }
    public void setMoney(String value) {
        this.money = value;
    }
    public String getMoney() {
       return this.money;
    }
}
