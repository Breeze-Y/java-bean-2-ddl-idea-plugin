package com.breezes.javabean2ddl.ui;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
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

    public SettingPanelConfig() {
        this.settingPanel = new SettingPanel();
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
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {

    }

}
