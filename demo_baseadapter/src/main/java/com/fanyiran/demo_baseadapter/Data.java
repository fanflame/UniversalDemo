package com.fanyiran.demo_baseadapter;

/**
 * Created by fanqiang on 2019/4/18.
 */
public class Data {
    private String content;
    private int itemType;

    public Data(int itemType, String content) {
        this.content = content;
        this.itemType = itemType;
    }

    public int getItemType() {
        return itemType;
    }

    public String getContent() {
        return content;
    }
}
