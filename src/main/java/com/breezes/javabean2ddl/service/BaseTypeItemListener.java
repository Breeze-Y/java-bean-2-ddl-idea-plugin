package com.breezes.javabean2ddl.service;

import com.breezes.javabean2ddl.enums.SqlTypeEnum;
import com.breezes.javabean2ddl.model.SqlTypeComboBoxItem;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author breezes_y@163.com
 * @date 2021/2/11 00:53
 * @description
 */
public class BaseTypeItemListener implements ItemListener {

    private JTextField jTextField;

    public BaseTypeItemListener(JTextField jTextField) {
        this.jTextField = jTextField;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }
        String value = ((SqlTypeComboBoxItem) e.getItem()).getValue();
        SqlTypeEnum typeEnum = SqlTypeEnum.findByType(value);
        if (null == typeEnum) {
            return;
        }
        if (typeEnum.getDefaultLengthNeedEmpty()) {
            jTextField.setText("");
        }
        if (StringUtils.equals(SqlTypeEnum.DECIMAL.getType(), value)) {
            jTextField.setText("(18,2)");
        }
    }
}
