package com.breezes.javabean2ddl.model;

import com.breezes.javabean2ddl.enums.TranslationAppEnum;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/2/7 19:37
 * @description
 */
public class TranslationAppComboBoxItem extends ComboBoxItem {

    public TranslationAppComboBoxItem() {
    }

    public TranslationAppComboBoxItem(String name, String value) {
        super(name, value);
    }

    public TranslationAppComboBoxItem(TranslationAppEnum appEnum) {
        this.name = appEnum.getName();
        this.value = appEnum.getCode();
    }

}
