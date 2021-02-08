package com.breezes.javabean2ddl.setting;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/2/7 21:26
 * @description
 */
@State(
        name = "JavaBean2DDL.Settings",
        storages = {
                @Storage(value = "$APP_CONFIG$/javabean2ddl.settings.xml")
        }
)
public class MainSetting implements PersistentStateComponent<MainSetting.MySettingProperties> {

    public MySettingProperties myProperties = new MySettingProperties();

    public static MainSetting getInstance() {
        return ServiceManager.getService(MainSetting.class);
    }

    @Nullable
    @Override
    public MainSetting.MySettingProperties getState() {
        System.out.println("getState run ...");
        return myProperties;
    }

    @Override
    public void loadState(@NotNull MainSetting.MySettingProperties state) {
        System.out.println("loadState run ...");
        myProperties = state;
    }

    public static class MySettingProperties {
        /**
         * 是否开启自动翻译
         */
        private Boolean autoTranslationRadio = false;
        /**
         * 翻译组件
         */
        private String translationAppComboBox = "";
        /**
         * appid
         */
        private String appIdText = "";
        /**
         * secret
         */
        private String secretText = "";
        /**
         * 腾讯云翻译secretId
         */
        private String secretId = "";
        /**
         * 腾讯云翻译secretKey
         */
        private String secretKey = "";

        public Boolean getAutoTranslationRadio() {
            return autoTranslationRadio;
        }

        public void setAutoTranslationRadio(Boolean autoTranslationRadio) {
            this.autoTranslationRadio = autoTranslationRadio;
        }

        public String getTranslationAppComboBox() {
            return translationAppComboBox;
        }

        public void setTranslationAppComboBox(String translationAppComboBox) {
            this.translationAppComboBox = translationAppComboBox;
        }

        public String getAppIdText() {
            return appIdText;
        }

        public void setAppIdText(String appIdText) {
            this.appIdText = appIdText;
        }

        public String getSecretText() {
            return secretText;
        }

        public void setSecretText(String secretText) {
            this.secretText = secretText;
        }

        public String getSecretId() {
            return secretId;
        }

        public void setSecretId(String secretId) {
            this.secretId = secretId;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }
    }
}
