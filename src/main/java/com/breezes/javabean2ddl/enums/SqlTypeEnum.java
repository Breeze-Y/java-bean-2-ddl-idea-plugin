package com.breezes.javabean2ddl.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/2/10 17:56
 * @description
 */
public enum SqlTypeEnum {

    TINYINT("TINYINT", false),
    INT("INT", false),
    BIGINT("BIGINT", false),
    FLOAT("FLOAT", true),
    DOUBLE("DOUBLE", true),
    DECIMAL("DECIMAL", false),
    DATE("DATE", true),
    DATETIME("DATETIME", true),
    TIMESTAMP("TIMESTAMP", true),
    CHAR("CHAR", false),
    VARCHAR("VARCHAR", false),
    TEXT("TEXT", true),
    LONGTEXT("LONGTEXT", true);

    private final String type;

    private final Boolean defaultLengthNeedEmpty;

    SqlTypeEnum(String type, Boolean defaultLengthNeedEmpty) {
        this.type = type;
        this.defaultLengthNeedEmpty = defaultLengthNeedEmpty;
    }

    public static SqlTypeEnum findByType(String type) {
        for (SqlTypeEnum typeEnum : values()) {
            if (StringUtils.equals(type, typeEnum.getType())) {
                return typeEnum;
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public Boolean getDefaultLengthNeedEmpty() {
        return defaultLengthNeedEmpty;
    }
}
