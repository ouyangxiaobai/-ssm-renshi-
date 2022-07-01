package com.mty.cc.po;


public class Admin {

    private String id;
    private String username;
    private String password;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String value) {
        this.id = value;
    }
    public String getId() {
       return this.id;
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
}
