package com.breezes.javabean2ddl.utils;

import com.breezes.javabean2ddl.model.TranslationVO;
import com.breezes.javabean2ddl.service.translation.BaiduTranslationService;
import com.breezes.javabean2ddl.service.translation.Translation;
import com.breezes.javabean2ddl.setting.MainSetting;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.breezes.javabean2ddl.enums.TranslationAppEnum.BAIDU;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/2/5 14:27
 * @description
 */
public class TranslationUtil {

    private static ThreadLocal<Translation> translationLocal = new ThreadLocal<>();

    public static void translationInit() {
        translationLocal.remove();
        MainSetting.MySettingProperties properties = MainSetting.getInstance().myProperties;
        if (StringUtils.equals(BAIDU.getValue(), properties.getTranslationAppComboBox())) {
            translationLocal.set(new BaiduTranslationService(properties.getAppIdText(), properties.getSecretText()));
        }
    }

    public static Map<String, String> enToZh(String englishText) {
        translationInit();

        Map<String, String> dataMap = new HashMap<>();
        Translation translation = translationLocal.get();
        if (null == translation) {
            return dataMap;
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