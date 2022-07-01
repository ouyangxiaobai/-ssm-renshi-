package com.mty.cc.po;


public class Salary {

    private String id;
    private String uid;
    private String month;
    private String money;
    private String ptime;

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
    public void setMonth(String value) {
        this.month = value;
    }
    public String getMonth() {
       return this.month;
    }
    public void setMoney(String value) {
        this.money = value;
    }
    public String getMoney() {
       return this.money;
    }
    public void setPtime(String value) {
        this.ptime = value;
    }
    public String getPtime() {
       return this.ptime;
    }
}
