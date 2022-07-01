package com.mty.cc.po;


public class Department {

    private String id;
    private String name;
    private String major;
    private String remark;

    public void setId(String value) {
        this.id = value;
    }
    public String getId() {
       return this.id;
    }
    public void setName(String value) {
        this.name = value;
    }
    public String getName() {
       return this.name;
    }
    public void setMajor(String value) {
        this.major = value;
    }
    public String getMajor() {
       return this.major;
    }
    public void setRemark(String value) {
        this.remark = value;
    }
    public String getRemark() {
       return this.remark;
    }
}
