package com.danier.solr.test.bean;

import org.apache.solr.client.solrj.beans.Field;

import com.alibaba.fastjson.JSON;

public class User {

    @Field
    private String id;

    @Field
    private String userName;

    @Field
    private String userAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
