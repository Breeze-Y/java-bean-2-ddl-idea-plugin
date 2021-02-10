package com.breezes.javabean2ddl.model;

import com.breezes.javabean2ddl.enums.SqlTypeEnum;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/2/10 17:52
 * @description
 */
public class SqlTypeComboBoxItem extends ComboBoxItem {

    public SqlTypeComboBoxItem() {
    }

    public SqlTypeComboBoxItem(String name, String value) {
        super(name, value);
    }

    public SqlTypeComboBoxItem(SqlTypeEnum sqlTypeEnum) {
        super(sqlTypeEnum.getType(), sqlTypeEnum.getType());
    }

}
