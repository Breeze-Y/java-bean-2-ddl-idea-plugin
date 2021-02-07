package com.breezes.javabean2ddl.model;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/2/6 23:44
 * @description
 */
public class ComboBoxItem {

    protected String name;

    protected String value;

    public ComboBoxItem() {
    }

    public ComboBoxItem(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
