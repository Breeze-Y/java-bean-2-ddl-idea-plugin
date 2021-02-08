package com.breezes.javabean2ddl.ui;

import com.breezes.javabean2ddl.enums.TranslationAppEnum;
import com.breezes.javabean2ddl.model.ComboBoxItem;
import com.breezes.javabean2ddl.model.TranslationAppComboBoxItem;
import com.breezes.javabean2ddl.setting.MainSetting;
import com.intellij.openapi.components.ServiceManager;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static com.breezes.javabean2ddl.enums.TranslationAppEnum.*;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/2/6 19:35
 * @description
 */
public class SettingPanel {
    private JPanel mainPanel;
    private JPanel baiduAccountPanel;
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
    private JPanel tencentAccountPanel;
    private JTextField secretId;
    private JTextField secretKey;

    private MainSetting.MySettingProperties properties;

    public SettingPanel() {
        MainSetting service = ServiceManager.getService(MainSetting.class);
        this.properties = service.myProperties;

        accountPanelInit();
        /*翻译组件下拉框初始化*/
        translationAppComboBoxInit();
        /*自动翻译单元框*/
        autoTranslationRadioInit();

        appIdText.setText(properties.getAppIdText());
        secretText.setText(properties.getSecretText());

        secretId.setText(properties.getSecretId());
        secretKey.setText(properties.getSecretKey());
    }

    private void accountPanelInit() {
        baiduAccountPanel.setVisible(StringUtils.equals(BAIDU.getValue(), properties.getTranslationAppComboBox()));
        tencentAccountPanel.setVisible(StringUtils.equals(TENCENT.getValue(), properties.getTranslationAppComboBox()));
    }

    private void autoTranslationRadioInit() {
        // 从配置中设置值
        autoTranslationRadio.setSelected(properties.getAutoTranslationRadio());
        // 单选按钮设置事件
        autoTranslationRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (autoTranslationRadio.isSelected()) {
                    TranslationAppComboBoxItem item = (TranslationAppComboBoxItem) translationAppComboBox.getSelectedItem();
                    assert item != null;
                    baiduAccountPanel.setVisible(StringUtils.equals(BAIDU.getValue(), item.getValue()));
                    tencentAccountPanel.setVisible(StringUtils.equals(TENCENT.getValue(), item.getValue()));
                    return;
                }
                baiduAccountPanel.setVisible(false);
                tencentAccountPanel.setVisible(false);
            }
        });
    }

    private void commonlyUsedMapComboBoxInit() {

    }

    private void translationAppComboBoxInit() {
        // 填充下拉框数据
        translationAppComboBox.addItem(new TranslationAppComboBoxItem(EMPTY));
        translationAppComboBox.addItem(new TranslationAppComboBoxItem(TENCENT));
        translationAppComboBox.addItem(new TranslationAppComboBoxItem(BAIDU));
        // 从配置中设置值
        translationAppComboBox.setSelectedItem(
                new TranslationAppComboBoxItem(
                        TranslationAppEnum.findByValue(properties.getTranslationAppComboBox())
                )
        );
        translationAppComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // 设置下拉框被选中的事件
                    TranslationAppComboBoxItem item = (TranslationAppComboBoxItem) e.getItem();
                    if (StringUtils.equals(TENCENT.getValue(), item.getValue())) {
                        autoTranslationRadio.setSelected(true);
                        tencentAccountPanel.setVisible(true);
                        baiduAccountPanel.setVisible(false);
                        return;
                    }
                    if (StringUtils.equals(BAIDU.getValue(), item.getValue())) {
                        autoTranslationRadio.setSelected(true);
                        tencentAccountPanel.setVisible(false);
                        baiduAccountPanel.setVisible(true);
                        return;
                    }
                    autoTranslationRadio.setSelected(false);
                    tencentAccountPanel.setVisible(false);
                    baiduAccountPanel.setVisible(false);
                }
            }
        });
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getAccountPanel() {
        return baiduAccountPanel;
    }

    public void setAccountPanel(JPanel accountPanel) {
        this.baiduAccountPanel = accountPanel;
    }

    public JPanel getTranslationSettingPanel() {
        return translationSettingPanel;
    }

    public void setTranslationSettingPanel(JPanel translationSettingPanel) {
        this.translationSettingPanel = translationSettingPanel;
    }

    public JPanel getTranslationBasePanel() {
        return translationBasePanel;
    }

    public void setTranslationBasePanel(JPanel translationBasePanel) {
        this.translationBasePanel = translationBasePanel;
    }

    public JComboBox<ComboBoxItem> getTranslationAppComboBox() {
        return translationAppComboBox;
    }

    public void setTranslationAppComboBox(JComboBox<ComboBoxItem> translationAppComboBox) {
        this.translationAppComboBox = translationAppComboBox;
    }

    public JTextField getAppIdText() {
        return appIdText;
    }

    public void setAppIdText(JTextField appIdText) {
        this.appIdText = appIdText;
    }

    public JRadioButton getAutoTranslationRadio() {
        return autoTranslationRadio;
    }

    public void setAutoTranslationRadio(JRadioButton autoTranslationRadio) {
        this.autoTranslationRadio = autoTranslationRadio;
    }

    public JTextField getSecretText() {
        return secretText;
    }

    public void setSecretText(JTextField secretText) {
        this.secretText = secretText;
    }

    public JTextField getTableText() {
        return tableText;
    }

    public void setTableText(JTextField tableText) {
        this.tableText = tableText;
    }

    public JTextField getIdText() {
        return idText;
    }

    public void setIdText(JTextField idText) {
        this.idText = idText;
    }

    public JTextField getCommendText() {
        return commendText;
    }

    public void setCommendText(JTextField commendText) {
        this.commendText = commendText;
    }

    public JComboBox<ComboBoxItem> getIntMapComboBox() {
        return intMapComboBox;
    }

    public void setIntMapComboBox(JComboBox<ComboBoxItem> intMapComboBox) {
        this.intMapComboBox = intMapComboBox;
    }

    public JTextField getIntDefaultText() {
        return intDefaultText;
    }

    public void setIntDefaultText(JTextField intDefaultText) {
        this.intDefaultText = intDefaultText;
    }

    public JPanel getAuxiliaryPanel() {
        return auxiliaryPanel;
    }

    public void setAuxiliaryPanel(JPanel auxiliaryPanel) {
        this.auxiliaryPanel = auxiliaryPanel;
    }

    public JPanel getAnnotationPanel() {
        return annotationPanel;
    }

    public void setAnnotationPanel(JPanel annotationPanel) {
        this.annotationPanel = annotationPanel;
    }

    public JPanel getDocPanel() {
        return docPanel;
    }

    public void setDocPanel(JPanel docPanel) {
        this.docPanel = docPanel;
    }

    public JPanel getMapPanel() {
        return mapPanel;
    }

    public void setMapPanel(JPanel mapPanel) {
        this.mapPanel = mapPanel;
    }

    public JPanel getCommonlyUsedMapPanel() {
        return commonlyUsedMapPanel;
    }

    public void setCommonlyUsedMapPanel(JPanel commonlyUsedMapPanel) {
        this.commonlyUsedMapPanel = commonlyUsedMapPanel;
    }

    public JComboBox<ComboBoxItem> getLongMapComboBox() {
        return longMapComboBox;
    }

    public void setLongMapComboBox(JComboBox<ComboBoxItem> longMapComboBox) {
        this.longMapComboBox = longMapComboBox;
    }

    public JTextField getLongDefaultText() {
        return longDefaultText;
    }

    public void setLongDefaultText(JTextField longDefaultText) {
        this.longDefaultText = longDefaultText;
    }

    public JComboBox<ComboBoxItem> getStringMapComboBox() {
        return stringMapComboBox;
    }

    public void setStringMapComboBox(JComboBox<ComboBoxItem> stringMapComboBox) {
        this.stringMapComboBox = stringMapComboBox;
    }

    public JTextField getStringDefaultText() {
        return stringDefaultText;
    }

    public void setStringDefaultText(JTextField stringDefaultText) {
        this.stringDefaultText = stringDefaultText;
    }

    public JComboBox<ComboBoxItem> getBooleanMapComboBox() {
        return booleanMapComboBox;
    }

    public void setBooleanMapComboBox(JComboBox<ComboBoxItem> booleanMapComboBox) {
        this.booleanMapComboBox = booleanMapComboBox;
    }

    public JTextField getBooleanDefaultText() {
        return booleanDefaultText;
    }

    public void setBooleanDefaultText(JTextField booleanDefaultText) {
        this.booleanDefaultText = booleanDefaultText;
    }

    public JComboBox<ComboBoxItem> getDateMapComboBox() {
        return dateMapComboBox;
    }

    public void setDateMapComboBox(JComboBox<ComboBoxItem> dateMapComboBox) {
        this.dateMapComboBox = dateMapComboBox;
    }

    public JTextField getDateDefaultText() {
        return dateDefaultText;
    }

    public void setDateDefaultText(JTextField dateDefaultText) {
        this.dateDefaultText = dateDefaultText;
    }

    public JPanel getBaiduAccountPanel() {
        return baiduAccountPanel;
    }

    public void setBaiduAccountPanel(JPanel baiduAccountPanel) {
        this.baiduAccountPanel = baiduAccountPanel;
    }

    public JPanel getTencentAccountPanel() {
        return tencentAccountPanel;
    }

    public void setTencentAccountPanel(JPanel tencentAccountPanel) {
        this.tencentAccountPanel = tencentAccountPanel;
    }

    public JTextField getSecretId() {
        return secretId;
    }

    public void setSecretId(JTextField secretId) {
        this.secretId = secretId;
    }

    public JTextField getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(JTextField secretKey) {
        this.secretKey = secretKey;
    }
}