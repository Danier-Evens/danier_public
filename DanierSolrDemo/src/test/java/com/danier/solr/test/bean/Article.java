package com.danier.solr.test.bean;

import org.apache.solr.client.solrj.beans.Field;

import com.alibaba.fastjson.JSON;

public class Article {

    @Field
    private String id;

    @Field
    private String actContent;

    @Field
    private String actName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getActContent() {
        return actContent;
    }

    public void setActContent(String actContent) {
        this.actContent = actContent;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
