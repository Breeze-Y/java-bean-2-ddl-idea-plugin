package com.breezes.javabean2ddl.service.translation;

import com.breezes.javabean2ddl.model.TranslationVO;

import java.util.List;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/2/5 11:54
 * @description
 */
public interface Translation {

    /**
     * 翻译成中文
     *
     * @param content 需要翻译的文本
     * @return
     */
    List<TranslationVO> toChinese(String content);

}
