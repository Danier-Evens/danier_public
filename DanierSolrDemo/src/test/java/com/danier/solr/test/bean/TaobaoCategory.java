package com.danier.solr.test.bean;

public class TaobaoCategory {

    private String id;

    private String code;

    private String name;

    private String displayName;

    private String hasSameLevelCategory;

    private String hasSubCategory;

    private TaobaoCategory parent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getHasSameLevelCategory() {
        return hasSameLevelCategory;
    }

    public void setHasSameLevelCategory(String hasSameLevelCategory) {
        this.hasSameLevelCategory = hasSameLevelCategory;
    }

    public String getHasSubCategory() {
        return hasSubCategory;
    }

    public void setHasSubCategory(String hasSubCategory) {
        this.hasSubCategory = hasSubCategory;
    }

    public TaobaoCategory getParent() {
        return parent;
    }

    public void setParent(TaobaoCategory parent) {
        this.parent = parent;
    }
}
