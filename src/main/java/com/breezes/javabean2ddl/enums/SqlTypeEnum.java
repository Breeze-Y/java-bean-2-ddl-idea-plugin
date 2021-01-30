package com.breezes.javabean2ddl.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/1/30 19:02
 * @description
 */
public enum SqlTypeEnum {

    BIGINT("BIGINT", Arrays.asList("long", "Long"), "(20)"),
    INT("INT", Arrays.asList("int", "Integer"), "(11)"),
    VARCHAR("VARCHAR", Arrays.asList("String", "char"), "(255)"),
    TINYINT("TINYINT", Arrays.asList("boolean", "Boolean"), "(1)"),
    DATETIME("DATETIME", Collections.singletonList("Date"), null);

    private final String sqlType;

    private final List<String> javaType;

    private final String defaultLength;

    SqlTypeEnum(String sqlType, List<String> javaType, String defaultLength) {
        this.sqlType = sqlType;
        this.javaType = javaType;
        this.defaultLength = defaultLength;
    }

    public static SqlTypeEnum findByJavaType(String javaType) {
        if (StringUtils.isBlank(javaType)) {
            throw new RuntimeException("异常实体");
        }
        for (SqlTypeEnum sqlTypeEnum : values()) {
            if (sqlTypeEnum.getJavaType().contains(javaType)) {
                return sqlTypeEnum;
            }
        }
        return VARCHAR;
    }

    public String getSqlType() {
        return sqlType;
    }

    public List<String> getJavaType() {
        return javaType;
    }

    public String getDefaultLength() {
        return defaultLength;
    }
}
