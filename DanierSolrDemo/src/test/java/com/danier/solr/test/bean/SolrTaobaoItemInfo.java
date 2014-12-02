package com.danier.solr.test.bean;

import org.apache.solr.client.solrj.beans.Field;

public class SolrTaobaoItemInfo {

	@Field
    private String id;

	@Field
    private String image;       // 图片链接

	@Field
    private String title;       // 标题

	@Field
    private String tip;         // 提示 通常和标题一样

	@Field
    private double currentPrice;// 当前价格
	
	@Field
    private double price;       // 原始价格

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

    @Field
    private String href;        // 商品链接
    
    @Field
    private Long timeStamp;     // 时间戳
    
    @Field
    private String category_code;
    
    @Field
    private String category_name;
    
    @Field
    private String category_displayName;
    
    @Field
    private String category_parent_code;
    
    @Field
    private String category_parent_name;
    
    @Field
    private String category_parent_displayName;
    
    
    public SolrTaobaoItemInfo() {
	}
    
    public SolrTaobaoItemInfo(TaobaoItemInfo taobaoItemInfo) {
    	this.id = taobaoItemInfo.getId();
    	this.image = taobaoItemInfo.getImage();
    	this.title = taobaoItemInfo.getTitle();
    	this.tip = taobaoItemInfo.getTip();
    	this.currentPrice = taobaoItemInfo.getCurrentPrice();
    	this.price = taobaoItemInfo.getPrice();
    	this.ship = taobaoItemInfo.getShip();
    	this.tradeNum = taobaoItemInfo.getTradeNum();
    	this.nick = taobaoItemInfo.getNick();
    	this.sellerId = taobaoItemInfo.getSellerId();
    	this.itemId = taobaoItemInfo.getItemId();
    	this.href = taobaoItemInfo.getHref();
    	this.timeStamp = taobaoItemInfo.getTimeStamp();
    	if(this.timeStamp==null) this.timeStamp = System.currentTimeMillis();
	}

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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_displayName() {
		return category_displayName;
	}

	public void setCategory_displayName(String category_displayName) {
		this.category_displayName = category_displayName;
	}

	public String getCategory_parent_code() {
		return category_parent_code;
	}

	public void setCategory_parent_code(String category_parent_code) {
		this.category_parent_code = category_parent_code;
	}

	public String getCategory_parent_name() {
		return category_parent_name;
	}

	public void setCategory_parent_name(String category_parent_name) {
		this.category_parent_name = category_parent_name;
	}

	public String getCategory_parent_displayName() {
		return category_parent_displayName;
	}

	public void setCategory_parent_displayName(String category_parent_displayName) {
		this.category_parent_displayName = category_parent_displayName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
