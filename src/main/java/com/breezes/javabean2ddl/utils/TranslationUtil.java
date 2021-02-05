package com.breezes.javabean2ddl.utils;

import com.breezes.javabean2ddl.model.TranslationVO;
import com.breezes.javabean2ddl.service.translation.BaiduTranslationService;
import com.breezes.javabean2ddl.service.translation.Translation;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/2/5 14:27
 * @description
 */
public class TranslationUtil {

    private static final Translation translation;

    static {
        translation = new BaiduTranslationService("20210205000691187", "J9Kf0EozqnMhujKnEnGn");
    }

    public static Map<String, String> enToZh(String englishText) {
        Map<String, String> dataMap = new HashMap<>();
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