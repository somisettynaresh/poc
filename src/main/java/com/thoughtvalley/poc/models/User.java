package com.thoughtvalley.poc.models;

/**
 * Created by Naresh on 7/21/2014.
 */
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class User implements Serializable{

    private static final long serialVersionUID = -7788619177798333712L;

    private String username;
    private String password;
    private Date createdDate;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonSerialize(using=DateSerializer.class)
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


}
