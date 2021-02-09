package com.breezes.javabean2ddl.ui;

import com.breezes.javabean2ddl.enums.TranslationAppEnum;
import com.breezes.javabean2ddl.model.ComboBoxItem;
import com.breezes.javabean2ddl.model.TranslationAppComboBoxItem;
import com.breezes.javabean2ddl.setting.MainSetting;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/2/5 18:59
 * @description
 */
public class SettingPanelConfig implements SearchableConfigurable {

    private SettingPanel settingPanel;

    private MainSetting mainSetting;

    public SettingPanelConfig() {
        this.settingPanel = new SettingPanel();
        this.mainSetting = MainSetting.getInstance();
    }

    @NotNull
    @Override
    public String getId() {
        return "top.breezes.javabean2ddl.java-bean-2-ddl-idea-plugin";
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "JavaBeanToDDL";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return settingPanel.getMainPanel();
    }

    @Override
    public boolean isModified() {
        System.out.println("校验配置修改");
        MainSetting.MySettingProperties myProperties = mainSetting.myProperties;
        if (!StringUtils.equals(String.valueOf(myProperties.getAutoTranslationRadio()),
                String.valueOf(settingPanel.getAutoTranslationRadio().isSelected()))) {
            return true;
        }
        ComboBoxItem appComboBox = (ComboBoxItem) settingPanel.getTranslationAppComboBox().getSelectedItem();
        assert appComboBox != null;
        if (!StringUtils.equals(myProperties.getTranslationAppComboBox(), appComboBox.getValue())) {
            return true;
        }
        if (!StringUtils.equals(myProperties.getAppIdText(), settingPanel.getAppIdText().getText())) {
            return true;
        }
        if (!StringUtils.equals(myProperties.getSecretText(), settingPanel.getSecretText().getText())) {
            return true;
        }
        if (!StringUtils.equals(myProperties.getSecretId(), settingPanel.getSecretId().getText())) {
            return true;
        }
        if (!StringUtils.equals(myProperties.getSecretKey(), settingPanel.getSecretKey().getText())) {
            return true;
        }
        if (!StringUtils.equals(myProperties.getIdAnnotation(), settingPanel.getIdText().getText())) {
            return true;
        }
        if (!StringUtils.equals(myProperties.getTableAnnotation(), settingPanel.getTableText().getText())) {
            return true;
        }
        if (!StringUtils.equals(myProperties.getTableAnnotationProperty(), settingPanel.getTablePropertyText().getText())) {
            return true;
        }
        if (!StringUtils.equals(myProperties.getCommentAnnotation(), settingPanel.getCommendText().getText())) {
            return true;
        }
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {
        MainSetting.MySettingProperties myProperties = mainSetting.myProperties;
        myProperties.setAutoTranslationRadio(settingPanel.getAutoTranslationRadio().isSelected());
        myProperties.setAppIdText(settingPanel.getAppIdText().getText());
        myProperties.setSecretText(settingPanel.getSecretText().getText());
        myProperties.setSecretId(settingPanel.getSecretId().getText());
        myProperties.setSecretKey(settingPanel.getSecretKey().getText());
        ComboBoxItem appComboBox = (ComboBoxItem) settingPanel.getTranslationAppComboBox().getSelectedItem();
        assert appComboBox != null;
        myProperties.setTranslationAppComboBox(appComboBox.getValue());
        myProperties.setIdAnnotation(settingPanel.getIdText().getText());
        myProperties.setTableAnnotation(settingPanel.getTableText().getText());
        myProperties.setTableAnnotationProperty(settingPanel.getTablePropertyText().getText());
        myProperties.setCommentAnnotation(settingPanel.getCommendText().getText());
        System.out.println("配置保存");
    }

    @Override
    public void reset() {
        System.out.println("配置重置");
        MainSetting.MySettingProperties myProperties = mainSetting.myProperties;
        settingPanel.getAutoTranslationRadio().setSelected((myProperties.getAutoTranslationRadio()));
        settingPanel.getTranslationAppComboBox().setSelectedItem(new TranslationAppComboBoxItem(
                TranslationAppEnum.findByValue(myProperties.getTranslationAppComboBox())
        ));
        settingPanel.getAppIdText().setText(myProperties.getAppIdText());
        settingPanel.getSecretText().setText(myProperties.getSecretText());
        settingPanel.getSecretId().setText(myProperties.getSecretId());
        settingPanel.getSecretKey().setText(myProperties.getSecretKey());
        settingPanel.getIdText().setText(myProperties.getIdAnnotation());
        settingPanel.getTableText().setText(myProperties.getTableAnnotation());
        settingPanel.getTablePropertyText().setText(myProperties.getTableAnnotationProperty());
        settingPanel.getCommendText().setText(myProperties.getCommentAnnotation());
    }
}
