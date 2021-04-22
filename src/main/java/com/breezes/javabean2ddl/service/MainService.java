package com.breezes.javabean2ddl.service;

import com.breezes.javabean2ddl.main.MainAction;
import com.breezes.javabean2ddl.model.Field;
import com.breezes.javabean2ddl.setting.MainSetting;
import com.breezes.javabean2ddl.utils.BaseUtil;
import com.breezes.javabean2ddl.utils.TranslationUtil;
import com.google.common.base.CaseFormat;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.javadoc.PsiDocTokenImpl;
import com.intellij.psi.impl.source.tree.PsiWhiteSpaceImpl;
import com.intellij.psi.impl.source.tree.java.PsiLiteralExpressionImpl;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocTag;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.breezes.javabean2ddl.constant.Constant.*;

/**
 * @author breezes_y@163.com
 * @date 2021/1/31 14:39
 * @description
 */
public class MainService {

    public static final String PRIMARY_KEY_COMMEND = "物理主键";

    public String getTableName(PsiClass currentClass) {
        MainSetting.MySettingProperties properties = MainSetting.getInstance().myProperties;
        String tableAnnotation = properties.getTableAnnotation();
        if (StringUtils.isBlank(tableAnnotation)) {
            // table注解为空直接返回类名作为表名
            return getTableNameFromClass(currentClass);
        }
        PsiAnnotation annotation = currentClass.getAnnotation(tableAnnotation);
        if (null == annotation || null == annotation.findAttributeValue(properties.getTableAnnotationProperty())) {
            return getTableNameFromClass(currentClass);
        }
        PsiAnnotationMemberValue value = annotation.findAttributeValue(properties.getTableAnnotationProperty());
        if (null == value || StringUtils.isBlank(value.getText())) {
            return getTableNameFromClass(currentClass);
        }
        // return PsiLiteralUtil.getStringLiteralContent(((PsiLiteralExpressionImpl) name));
        // 低版本兼容
        // return ((PsiLiteralExpressionImpl) name).getInnerText();
        String tableName = BaseUtil.getStringLiteralContent(((PsiLiteralExpressionImpl) value));
        return StringUtils.isNotBlank(tableName) ? tableName : getTableNameFromClass(currentClass);
    }

    private String getTableNameFromClass(PsiClass currentClass) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, Objects.requireNonNull(currentClass.getName()));
    }

    /**
     * 获取需要转换的字段
     *
     * @param currentClass
     * @return
     */
    public List<Field> getFieldList(PsiClass currentClass) {
        return getFieldList(currentClass, true);
    }

    /**
     * 获取需要转换的字段
     *
     * @param currentClass
     * @return
     */
    public List<Field> getFieldList(PsiClass currentClass, boolean allField) {
        PsiField[] fields = getPsiFields(currentClass, allField);
        // 利用set去重
        HashSet<Field> fieldSet = new LinkedHashSet<>();
        for (PsiField field : fields) {
            if (isNeedAddConvert(field)) {
                fieldSet.add(getField(field));
            }
        }

        // set command
        List<Field> fieldList = new ArrayList<>(fieldSet);
        if (MainSetting.getInstance().myProperties.getAutoTranslationRadio()) {
            getTranslationMap(fieldList, getTableName(currentClass));
        }

        for (Field field : fieldList) {
            if (StringUtils.isBlank(field.getComment())) {
                field.setComment(getCommend(field, MainAction.translationMap));
            }
        }

        return fieldList;
    }

    /**
     * 翻译字段得到注释
     *
     * @param fieldList
     * @param tableName
     * @return
     */
    private static Map<String, String> getTranslationMap(List<Field> fieldList, String tableName) {
        if (null != MainAction.translationMap && !MainAction.translationMap.isEmpty()) {
            return MainAction.translationMap;
        }
        Map<String, String> translationMap = TranslationUtil.enToZh(fieldList, tableName);
        MainAction.translationMap = new ConcurrentHashMap<>(translationMap);
        return new ConcurrentHashMap<>(translationMap);
    }

    /**
     * 获取注释
     *
     * @param field
     * @param translationMap
     * @return
     */
    private static String getCommend(Field field, Map<String, String> translationMap) {
        if (!StringUtils.equals(field.getName(), "id")) {
            if (MainSetting.getInstance().myProperties.getAutoTranslationRadio()) {
                return translationMap.getOrDefault(field.getTableColumn().replace("_", " "), "");
            }
            return translationMap.getOrDefault(field.getTableColumn().replace("_", " "), null);
        }
        return PRIMARY_KEY_COMMEND;
    }


    private PsiField[] getPsiFields(PsiClass currentClass, boolean allField) {
        if (allField) {
            return BaseUtil.getAllFields(currentClass);
        }
        return BaseUtil.getFields(currentClass);
    }

    private Field getField(PsiField field) {
        boolean primaryKey = false;
        String idAnnotation = MainSetting.getInstance().myProperties.getIdAnnotation();
        if (StringUtils.isNotBlank(idAnnotation)) {
            PsiAnnotation annotation = field.getAnnotation(idAnnotation);
            if (null != annotation) {
                primaryKey = true;
            }
        }

        return Field.newField(field.getName(), field.getType().getPresentableText(), primaryKey, getComment(field));
    }

    private String getComment(PsiField field) {
        String commentAnnotation = MainSetting.getInstance().myProperties.getCommentAnnotation();
        if (null != field.getDocComment() && StringUtils.isNotBlank(commentAnnotation)) {
            // 字段上携带了对应的标识备注注解
            PsiDocTag comment = field.getDocComment().findTagByName(commentAnnotation);
            if (null != comment && null != comment.getValueElement()) {
                return comment.getValueElement().getText();
            }
            // 字段上没有携带注解，则从Doc注释上获取
            PsiDocComment docComment = field.getDocComment();
            if (null != docComment) {
                PsiElement[] elements = docComment.getDescriptionElements();
                return parseAllDoc(elements);
            }
        }
        return null;
    }

    /**
     * 解析出文档所有有效文本
     */
    public String parseAllDoc(PsiElement[] elements) {
        if (elements == null || elements.length == 0) {
            return null;
        }
        List<String> docList = new ArrayList<>();
        for (PsiElement element : elements) {
            if (element instanceof PsiWhiteSpaceImpl) {
                continue;
            }
            if (element instanceof PsiDocTokenImpl) {
                String text = element.getText();
                if (StringUtils.isNotBlank(text)) {
                    docList.add(text.trim());
                }
            }
        }
        return CollectionUtils.isEmpty(docList) ? StringUtils.EMPTY : String.join(" ", docList);
    }

    /**
     * 字段类型是否需要加入转换
     *
     * @param psiField
     * @return
     */
    private boolean isNeedAddConvert(PsiField psiField) {
        if (BaseUtil.isPrimitiveType(psiField)) {
            return true;
        }
        String internalCanonicalText = psiField.getType().getInternalCanonicalText();
        return (StringUtils.equals(STRING_PACKAGE, internalCanonicalText)
                || StringUtils.equals(DATE_PACKAGE, internalCanonicalText)
                || StringUtils.equals(BIGDECIMAL_PACKAGE, internalCanonicalText));
    }

}
