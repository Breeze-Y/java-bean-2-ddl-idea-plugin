package com.breezes.javabean2ddl.utils;

import com.breezes.javabean2ddl.model.Field;
import com.breezes.javabean2ddl.model.TranslationVO;
import com.breezes.javabean2ddl.service.translation.BaiduTranslationService;
import com.breezes.javabean2ddl.service.translation.TencentTranslationService;
import com.breezes.javabean2ddl.service.translation.Translation;
import com.breezes.javabean2ddl.setting.MainSetting;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.breezes.javabean2ddl.enums.TranslationAppEnum.BAIDU;
import static com.breezes.javabean2ddl.enums.TranslationAppEnum.TENCENT;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/2/5 14:27
 * @description
 */
public class TranslationUtil {

    private static ThreadLocal<Translation> translationLocal = new ThreadLocal<>();

    public static void translationInit(MainSetting.MySettingProperties properties) {
        translationLocal.remove();
        if (StringUtils.equals(BAIDU.getValue(), properties.getTranslationAppComboBox())) {
            translationLocal.set(new BaiduTranslationService(properties.getAppIdText(), properties.getSecretText()));
        }
        if (StringUtils.equals(TENCENT.getValue(), properties.getTranslationAppComboBox())) {
            translationLocal.set(new TencentTranslationService(properties.getSecretId(), properties.getSecretKey()));
        }
    }

    public static Map<String, String> enToZh(List<Field> fieldList, String tableName) {
        tableName = tableName.replace("_", " ");
        MainSetting.MySettingProperties properties = MainSetting.getInstance().myProperties;
        String translationApp = properties.getTranslationAppComboBox();
        translationInit(properties);

        Map<String, String> dataMap = new HashMap<>();
        Translation translation = translationLocal.get();
        if (null == translation) {
            return dataMap;
        }

        String englishText = "";
        // 百度翻译
        if (StringUtils.equals(BAIDU.getValue(), translationApp)) {
            List<String> commendList = new ArrayList<>();
            commendList.add(tableName);
            for (Field field : fieldList) {
                commendList.add(field.getTableColumn().replace("_", " "));
            }
            englishText = String.join("\n", commendList);
        }
        // 腾讯翻译
        if (StringUtils.equals(TENCENT.getValue(), translationApp)) {
            StringBuilder text = new StringBuilder("[" + tableName + "]");
            for (Field field : fieldList) {
                text.append("[").append(field.getTableColumn().replace("_", " ")).append("]");
            }
            englishText = text.toString();
        }
        List<TranslationVO> translationList = translation.toChinese(englishText);
        if (CollectionUtils.isEmpty(translationList)) {
            return dataMap;
        }

        for (TranslationVO translationVO : translationList) {
            dataMap.put(translationVO.getSrc(), translationVO.getDst());
        }
        return dataMap;
    }
}