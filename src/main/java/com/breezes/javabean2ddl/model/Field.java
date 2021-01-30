package com.breezes.javabean2ddl.model;


import com.google.common.base.CaseFormat;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/1/30 15:11
 * @description
 */
public class Field {

    public String name;

    private String type;

    private Boolean primaryKey;

    public static Field newField(String name, String type, boolean primaryKey) {
        return new Field(name, type, primaryKey);
    }

    public static Field newField(String name, String type) {
        return new Field(name, type);
    }

    public String getTableColumn() {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.name);
    }

    public Field() {
    }

    public Field(String name, String type) {
        this.name = name;
        this.type = type;
        this.primaryKey = false;
    }

    public Field(String name, String type, Boolean primaryKey) {
        this.name = name;
        this.type = type;
        this.primaryKey = primaryKey;
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

    public Boolean getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }
}
