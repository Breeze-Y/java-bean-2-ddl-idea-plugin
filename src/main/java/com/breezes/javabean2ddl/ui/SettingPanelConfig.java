package com.breezes.javabean2ddl.ui;

import com.breezes.javabean2ddl.enums.SqlTypeEnum;
import com.breezes.javabean2ddl.enums.TranslationAppEnum;
import com.breezes.javabean2ddl.model.ComboBoxItem;
import com.breezes.javabean2ddl.model.SqlTypeComboBoxItem;
import com.breezes.javabean2ddl.model.TranslationAppComboBoxItem;
import com.breezes.javabean2ddl.setting.MainSetting;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

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
        ComboBoxItem intComboBox = (ComboBoxItem) settingPanel.getIntMapComboBox().getSelectedItem();
        assert intComboBox != null;
        if (!StringUtils.equals(myProperties.getIntType(), intComboBox.getValue())) {
            return true;
        }
        ComboBoxItem doubleComboBox = (ComboBoxItem) settingPanel.getDoubleMapComboBox().getSelectedItem();
        assert doubleComboBox != null;
        if (!StringUtils.equals(myProperties.getDoubleType(), doubleComboBox.getValue())) {
            return true;
        }
        ComboBoxItem floatComboBox = (ComboBoxItem) settingPanel.getFloatMapComboBox().getSelectedItem();
        assert floatComboBox != null;
        if (!StringUtils.equals(myProperties.getFloatType(), floatComboBox.getValue())) {
            return true;
        }
        ComboBoxItem longComboBox = (ComboBoxItem) settingPanel.getLongMapComboBox().getSelectedItem();
        assert longComboBox != null;
        if (!StringUtils.equals(myProperties.getLongType(), longComboBox.getValue())) {
            return true;
        }
        ComboBoxItem stringComboBox = (ComboBoxItem) settingPanel.getStringMapComboBox().getSelectedItem();
        assert stringComboBox != null;
        if (!StringUtils.equals(myProperties.getStringType(), stringComboBox.getValue())) {
            return true;
        }
        ComboBoxItem booleanComboBox = (ComboBoxItem) settingPanel.getBooleanMapComboBox().getSelectedItem();
        assert booleanComboBox != null;
        if (!StringUtils.equals(myProperties.getBooleanType(), booleanComboBox.getValue())) {
            return true;
        }
        ComboBoxItem dateComboBox = (ComboBoxItem) settingPanel.getDateMapComboBox().getSelectedItem();
        assert dateComboBox != null;
        if (!StringUtils.equals(myProperties.getDateType(), dateComboBox.getValue())) {
            return true;
        }
        if (!StringUtils.equals(myProperties.getIntDefaultLength(), settingPanel.getIntDefaultText().getText())) {
            return true;
        }
        if (!StringUtils.equals(myProperties.getLongDefaultLength(), settingPanel.getLongDefaultText().getText())) {
            return true;
        }
        if (!StringUtils.equals(myProperties.getDoubleDefaultLength(), settingPanel.getDoubleDefaultText().getText())) {
            return true;
        }
        if (!StringUtils.equals(myProperties.getFloatDefaultLength(), settingPanel.getFloatDefaultText().getText())) {
            return true;
        }
        if (!StringUtils.equals(myProperties.getBooleanDefaultLength(), settingPanel.getBooleanDefaultText().getText())) {
            return true;
        }
        if (!StringUtils.equals(myProperties.getStringDefaultLength(), settingPanel.getStringDefaultText().getText())) {
            return true;
        }
        return !StringUtils.equals(myProperties.getDateDefaultLength(), settingPanel.getDateDefaultText().getText());
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

        myProperties.setIntType(((ComboBoxItem) Objects.requireNonNull(settingPanel.getIntMapComboBox().getSelectedItem())).getValue());
        myProperties.setBooleanType(((ComboBoxItem) Objects.requireNonNull(settingPanel.getBooleanMapComboBox().getSelectedItem())).getValue());
        myProperties.setDoubleType(((ComboBoxItem) Objects.requireNonNull(settingPanel.getDoubleMapComboBox().getSelectedItem())).getValue());
        myProperties.setFloatType(((ComboBoxItem) Objects.requireNonNull(settingPanel.getFloatMapComboBox().getSelectedItem())).getValue());
        myProperties.setLongType(((ComboBoxItem) Objects.requireNonNull(settingPanel.getLongMapComboBox().getSelectedItem())).getValue());
        myProperties.setStringType(((ComboBoxItem) Objects.requireNonNull(settingPanel.getStringMapComboBox().getSelectedItem())).getValue());
        myProperties.setDateType(((ComboBoxItem) Objects.requireNonNull(settingPanel.getDateMapComboBox().getSelectedItem())).getValue());

        myProperties.setIntDefaultLength(settingPanel.getIntDefaultText().getText());
        myProperties.setLongDefaultLength(settingPanel.getLongDefaultText().getText());
        myProperties.setDoubleDefaultLength(settingPanel.getDoubleDefaultText().getText());
        myProperties.setFloatDefaultLength(settingPanel.getFloatDefaultText().getText());
        myProperties.setBooleanDefaultLength(settingPanel.getBooleanDefaultText().getText());
        myProperties.setDateDefaultLength(settingPanel.getDateDefaultText().getText());
        myProperties.setStringDefaultLength(settingPanel.getStringDefaultText().getText());
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

        settingPanel.getIntMapComboBox().setSelectedItem(new SqlTypeComboBoxItem(Objects.requireNonNull(SqlTypeEnum.findByType(myProperties.getIntType()))));
        settingPanel.getLongMapComboBox().setSelectedItem(new SqlTypeComboBoxItem(Objects.requireNonNull(SqlTypeEnum.findByType(myProperties.getLongType()))));
        settingPanel.getBooleanMapComboBox().setSelectedItem(new SqlTypeComboBoxItem(Objects.requireNonNull(SqlTypeEnum.findByType(myProperties.getBooleanType()))));
        settingPanel.getDateMapComboBox().setSelectedItem(new SqlTypeComboBoxItem(Objects.requireNonNull(SqlTypeEnum.findByType(myProperties.getDateType()))));
        settingPanel.getStringMapComboBox().setSelectedItem(new SqlTypeComboBoxItem(Objects.requireNonNull(SqlTypeEnum.findByType(myProperties.getStringType()))));
        settingPanel.getDoubleMapComboBox().setSelectedItem(new SqlTypeComboBoxItem(Objects.requireNonNull(SqlTypeEnum.findByType(myProperties.getDoubleType()))));
        settingPanel.getFloatMapComboBox().setSelectedItem(new SqlTypeComboBoxItem(Objects.requireNonNull(SqlTypeEnum.findByType(myProperties.getFloatType()))));

        settingPanel.getIntDefaultText().setText(myProperties.getIntDefaultLength());
        settingPanel.getLongDefaultText().setText(myProperties.getLongDefaultLength());
        settingPanel.getDoubleDefaultText().setText(myProperties.getDoubleDefaultLength());
        settingPanel.getFloatDefaultText().setText(myProperties.getFloatDefaultLength());
        settingPanel.getBooleanDefaultText().setText(myProperties.getBooleanDefaultLength());
        settingPanel.getDateDefaultText().setText(myProperties.getDateDefaultLength());
        settingPanel.getStringDefaultText().setText(myProperties.getStringDefaultLength());
    }
}
