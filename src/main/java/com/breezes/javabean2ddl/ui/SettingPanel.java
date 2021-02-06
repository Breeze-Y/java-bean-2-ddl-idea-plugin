package com.breezes.javabean2ddl.ui;

import javax.swing.*;

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
    private JComboBox translationAppComboBox;
    private JTextField appIdText;
    private JRadioButton autoTranslationRadio;
    private JTextField secretText;
    private JTextField tableText;
    private JTextField idText;
    private JTextField commendText;
    private JComboBox intMapComboBox;
    private JTextField intDefaultText;
    private JPanel auxiliaryPanel;
    private JPanel annotationPanel;
    private JPanel docPanel;
    private JPanel mapPanel;
    private JPanel commonlyUsedMapPanel;
    private JComboBox longMapComboBox;
    private JTextField longDefaultText;
    private JComboBox stringMapComboBox;
    private JTextField stringDefaultText;
    private JComboBox booleanMapComboBox;
    private JTextField booleanDefaultText;
    private JComboBox dateMapComboBox;
    private JTextField dateDefaultText;

    public SettingPanel() {
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
