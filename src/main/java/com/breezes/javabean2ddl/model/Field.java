package com.breezes.javabean2ddl.model;


import com.breezes.javabean2ddl.enums.SqlTypeAndJavaTypeEnum;
import com.breezes.javabean2ddl.utils.SqlTypeMapUtil;
import com.google.common.base.CaseFormat;

import java.util.Objects;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/1/30 15:11
 * @description
 */
public class Field {

    public String name;

    private String type;

    private Boolean primaryKey;

    private String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return Objects.equals(name, field.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static Field newField(String name, String type, boolean primaryKey, String comment) {
        return new Field(name, type, primaryKey, comment);
    }

    public static Field newField(String name, String type) {
        return new Field(name, type);
    }

    public String getTableColumn() {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.name);
    }

    public String getSqlType() {
        SqlTypeMapUtil.ConvertBean convertBean = SqlTypeMapUtil.getInstance().typeConvert(this.type);
        if (null != convertBean) {
            return convertBean.getSqlType() + convertBean.getSqlTypeLength();
        }
        return "";
//        if (null == getSqlTypeSize()) {
//            return getSqlTypeForMapping();
//        }
//        return getSqlTypeForMapping() + getSqlTypeSize();
    }

    /**
     * 获取mysql类型
     */
    public String getSqlTypeForMapping() {
        /*类型映射*/
        return SqlTypeAndJavaTypeEnum.findByJavaType(this.type).getSqlType();
    }

    public String getSqlTypeSize() {
        return SqlTypeAndJavaTypeEnum.findByJavaType(this.type).getDefaultLength();
    }

    public Field() {
    }

    public Field(String name, String type) {
        this.name = name;
        this.type = type;
        this.primaryKey = false;
    }

    public Field(String name, String type, Boolean primaryKey, String comment) {
        this.name = name;
        this.type = type;
        this.primaryKey = primaryKey;
        this.comment = comment;
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

    public Boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Boolean getPrimaryKey() {
        return primaryKey;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
