package com.breezes.javabean2ddl.enums;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/2/7 19:25
 * @description
 */
public enum TranslationAppEnum {

    BAIDU("百度翻译", "baidu");

    private final String name;

    private final String value;

    TranslationAppEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return value;
    }
}
