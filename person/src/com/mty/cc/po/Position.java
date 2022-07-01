package com.mty.cc.po;


public class Position {

    private String id;
    private String name;
    private String did;
    private String remark;

    private String dname;

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

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
    public void setDid(String value) {
        this.did = value;
    }
    public String getDid() {
       return this.did;
    }
    public void setRemark(String value) {
        this.remark = value;
    }
    public String getRemark() {
       return this.remark;
    }
}
