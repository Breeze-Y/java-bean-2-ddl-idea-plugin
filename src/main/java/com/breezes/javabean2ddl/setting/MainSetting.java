package com.breezes.javabean2ddl.setting;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.breezes.javabean2ddl.enums.SqlTypeAndJavaTypeEnum.*;

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
        /**
         * 表名使用的注解
         */
        private String tableAnnotation = "javax.persistence.Table";
        /**
         * 表名使用的注解属性
         */
        private String tableAnnotationProperty = "name";
        /**
         * id使用的注解
         */
        private String idAnnotation = "javax.persistence.Id";
        /**
         * 注释
         */
        private String commentAnnotation = "comment";

        private String intType = INT.getSqlType();

        private String longType = BIGINT.getSqlType();

        private String stringType = VARCHAR.getSqlType();

        private String booleanType = TINYINT.getSqlType();

        private String dateType = DATETIME.getSqlType();

        private String doubleType = DOUBLE.getSqlType();

        private String floatType = DOUBLE.getSqlType();

        private String intDefaultLength = INT.getDefaultLength();

        private String longDefaultLength = BIGINT.getDefaultLength();

        private String stringDefaultLength = VARCHAR.getDefaultLength();

        private String doubleDefaultLength = DOUBLE.getDefaultLength();

        private String floatDefaultLength = DOUBLE.getDefaultLength();

        private String booleanDefaultLength = TINYINT.getDefaultLength();

        private String dateDefaultLength = DATETIME.getDefaultLength();

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

        public String getTableAnnotation() {
            return tableAnnotation;
        }

        public void setTableAnnotation(String tableAnnotation) {
            this.tableAnnotation = tableAnnotation;
        }

        public String getIdAnnotation() {
            return idAnnotation;
        }

        public void setIdAnnotation(String idAnnotation) {
            this.idAnnotation = idAnnotation;
        }

        public String getTableAnnotationProperty() {
            return tableAnnotationProperty;
        }

        public void setTableAnnotationProperty(String tableAnnotationProperty) {
            this.tableAnnotationProperty = tableAnnotationProperty;
        }

        public String getCommentAnnotation() {
            return commentAnnotation;
        }

        public void setCommentAnnotation(String commentAnnotation) {
            this.commentAnnotation = commentAnnotation;
        }

        public String getIntType() {
            return intType;
        }

        public void setIntType(String intType) {
            this.intType = intType;
        }

        public String getLongType() {
            return longType;
        }

        public void setLongType(String longType) {
            this.longType = longType;
        }

        public String getStringType() {
            return stringType;
        }

        public void setStringType(String stringType) {
            this.stringType = stringType;
        }

        public String getBooleanType() {
            return booleanType;
        }

        public void setBooleanType(String booleanType) {
            this.booleanType = booleanType;
        }

        public String getDateType() {
            return dateType;
        }

        public void setDateType(String dateType) {
            this.dateType = dateType;
        }

        public String getDoubleType() {
            return doubleType;
        }

        public void setDoubleType(String doubleType) {
            this.doubleType = doubleType;
        }

        public String getFloatType() {
            return floatType;
        }

        public void setFloatType(String floatType) {
            this.floatType = floatType;
        }

        public String getIntDefaultLength() {
            return intDefaultLength;
        }

        public void setIntDefaultLength(String intDefaultLength) {
            this.intDefaultLength = intDefaultLength;
        }

        public String getLongDefaultLength() {
            return longDefaultLength;
        }

        public void setLongDefaultLength(String longDefaultLength) {
            this.longDefaultLength = longDefaultLength;
        }

        public String getStringDefaultLength() {
            return stringDefaultLength;
        }

        public void setStringDefaultLength(String stringDefaultLength) {
            this.stringDefaultLength = stringDefaultLength;
        }

        public String getDoubleDefaultLength() {
            return doubleDefaultLength;
        }

        public void setDoubleDefaultLength(String doubleDefaultLength) {
            this.doubleDefaultLength = doubleDefaultLength;
        }

        public String getFloatDefaultLength() {
            return floatDefaultLength;
        }

        public void setFloatDefaultLength(String floatDefaultLength) {
            this.floatDefaultLength = floatDefaultLength;
        }

        public String getBooleanDefaultLength() {
            return booleanDefaultLength;
        }

        public void setBooleanDefaultLength(String booleanDefaultLength) {
            this.booleanDefaultLength = booleanDefaultLength;
        }

        public String getDateDefaultLength() {
            return dateDefaultLength;
        }

        public void setDateDefaultLength(String dateDefaultLength) {
            this.dateDefaultLength = dateDefaultLength;
        }
    }
}
