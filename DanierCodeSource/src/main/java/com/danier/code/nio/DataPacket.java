package com.danier.code.nio;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;

/**
 * NIO 通信数据包
 * @author Danier
 * @date 2014-11-3
 */
public class DataPacket implements Serializable {

    private long id;

    private String content;// 数据包内容

    private Date sendTime;// 发送时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
