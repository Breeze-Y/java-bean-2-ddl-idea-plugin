package com.breezes.javabean2ddl.enums;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/2/10 17:56
 * @description
 */
public enum SqlTypeEnum {

    TINYINT("TINYINT"),
    INT("INT"),
    BIGINT("BIGINT"),
    FLOAT("FLOAT"),
    DOUBLE("DOUBLE"),
    DECIMAL("DECIMAL"),
    DATE("DATE"),
    DATETIME("DATETIME"),
    TIMESTAMP("TIMESTAMP"),
    CHAR("CHAR"),
    VARCHAR("VARCHAR"),
    TEXT("TEXT"),
    LONGTEXT("LONGTEXT");

    private final String type;

    SqlTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
