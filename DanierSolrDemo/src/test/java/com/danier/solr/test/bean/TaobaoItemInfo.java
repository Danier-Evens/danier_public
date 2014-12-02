package com.danier.solr.test.bean;

import org.apache.solr.client.solrj.beans.Field;

public class TaobaoItemInfo {

	@Field
    private String id;

	@Field
    private String image;       // 图片链接

	@Field
    private String title;       // 标题

	@Field
    private String tip;         // 提示 通常和标题一样

	@Field
    private double price;       // 原始价格

	@Field
    private double currentPrice;// 当前价格

	@Field
    private double ship;        // 邮费

	@Field
    private int tradeNum;    // 销量

	@Field
    private String nick;        // 卖家昵称

	@Field
    private String sellerId;    // 卖家ID

	@Field
    private String itemId;      // 商品ID

    private String loc;         // 地区

    private String storeLink;   // 商家店铺链接

    @Field
    private String href;        // 商品链接

    private String goodRate;    // 商品好评

    private String dsrScore;    // 商家评分

    private TaobaoCategory category; // 所属分类
    
    @Field
    private Long timeStamp;     // 时间戳

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getShip() {
        return ship;
    }

    public void setShip(double ship) {
        this.ship = ship;
    }

    public int getTradeNum() {
		return tradeNum;
	}

	public void setTradeNum(int tradeNum) {
		this.tradeNum = tradeNum;
	}

	public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getStoreLink() {
        return storeLink;
    }

    public void setStoreLink(String storeLink) {
        this.storeLink = storeLink;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getGoodRate() {
        return goodRate;
    }

    public void setGoodRate(String goodRate) {
        this.goodRate = goodRate;
    }

    public String getDsrScore() {
        return dsrScore;
    }

    public void setDsrScore(String dsrScore) {
        this.dsrScore = dsrScore;
    }

    public TaobaoCategory getCategory() {
        return category;
    }

    public void setCategory(TaobaoCategory category) {
        this.category = category;
    }

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
    
}
