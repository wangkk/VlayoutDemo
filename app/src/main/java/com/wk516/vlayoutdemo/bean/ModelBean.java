package com.wk516.vlayoutdemo.bean;

/**
 * Created by Ke_Wang on 2019/3/15.
 */

public class ModelBean {

    private String name;
    private String type;

    public ModelBean(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
