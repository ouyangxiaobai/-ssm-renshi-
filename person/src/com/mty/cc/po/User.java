package com.mty.cc.po;


public class User {

    private String id;
    private String realname;
    private String username;
    private String password;
    private String sex;
    private String phone;
    private String did;
    private String pid;

    private String type;
    private String passwords;
    private String dname;
    private String pname;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public void setId(String value) {
        this.id = value;
    }
    public String getId() {
       return this.id;
    }
    public void setRealname(String value) {
        this.realname = value;
    }
    public String getRealname() {
       return this.realname;
    }
    public void setUsername(String value) {
        this.username = value;
    }
    public String getUsername() {
       return this.username;
    }
    public void setPassword(String value) {
        this.password = value;
    }
    public String getPassword() {
       return this.password;
    }
    public void setSex(String value) {
        this.sex = value;
    }
    public String getSex() {
       return this.sex;
    }
    public void setPhone(String value) {
        this.phone = value;
    }
    public String getPhone() {
       return this.phone;
    }
    public void setDid(String value) {
        this.did = value;
    }
    public String getDid() {
       return this.did;
    }
}
