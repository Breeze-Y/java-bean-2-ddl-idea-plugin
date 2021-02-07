package com.breezes.javabean2ddl.ui;

import com.breezes.javabean2ddl.enums.TranslationAppEnum;
import com.breezes.javabean2ddl.model.ComboBoxItem;
import com.breezes.javabean2ddl.model.TranslationAppComboBoxItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/2/6 19:35
 * @description
 */
public class SettingPanel {
    private JPanel mainPanel;
    private JPanel accountPanel;
    private JPanel translationSettingPanel;
    private JPanel translationBasePanel;
    private JComboBox<ComboBoxItem> translationAppComboBox;
    private JTextField appIdText;
    private JRadioButton autoTranslationRadio;
    private JTextField secretText;
    private JTextField tableText;
    private JTextField idText;
    private JTextField commendText;
    private JComboBox<ComboBoxItem> intMapComboBox;
    private JTextField intDefaultText;
    private JPanel auxiliaryPanel;
    private JPanel annotationPanel;
    private JPanel docPanel;
    private JPanel mapPanel;
    private JPanel commonlyUsedMapPanel;
    private JComboBox<ComboBoxItem> longMapComboBox;
    private JTextField longDefaultText;
    private JComboBox<ComboBoxItem> stringMapComboBox;
    private JTextField stringDefaultText;
    private JComboBox<ComboBoxItem> booleanMapComboBox;
    private JTextField booleanDefaultText;
    private JComboBox<ComboBoxItem> dateMapComboBox;
    private JTextField dateDefaultText;

    public SettingPanel() {
        accountPanelInit();
        /*翻译组件下拉框初始化*/
        translationAppComboBoxInit();
        /*自动翻译单元框*/
        autoTranslationRadioInit();
    }

    private void accountPanelInit() {
        accountPanel.setVisible(autoTranslationRadio.isSelected());
    }

    private void autoTranslationRadioInit() {
        autoTranslationRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (autoTranslationRadio.isSelected()) {
                    accountPanel.setVisible(true);
                    return;
                }
                accountPanel.setVisible(false);
            }
        });
    }

    private void commonlyUsedMapComboBoxInit() {

    }

    private void translationAppComboBoxInit() {
        translationAppComboBox.addItem(new TranslationAppComboBoxItem(TranslationAppEnum.BAIDU));
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }
}
