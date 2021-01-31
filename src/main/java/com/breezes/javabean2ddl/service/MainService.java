package com.breezes.javabean2ddl.service;

import com.breezes.javabean2ddl.model.Field;
import com.breezes.javabean2ddl.utils.BaseUtil;
import com.google.common.base.CaseFormat;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.impl.source.tree.java.PsiLiteralExpressionImpl;
import com.intellij.psi.util.PsiLiteralUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static com.breezes.javabean2ddl.constant.Constant.DATE_PACKAGE;
import static com.breezes.javabean2ddl.constant.Constant.STRING_PACKAGE;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/1/31 14:39
 * @description
 */
public class MainService {

    public String getTableName(PsiClass currentClass) {
        PsiAnnotation annotation = currentClass.getAnnotation("javax.persistence.Table");
        if (null != annotation && null != annotation.findAttributeValue("name")) {
            PsiAnnotationMemberValue name = annotation.findAttributeValue("name");
            if (null != name && StringUtils.isNotBlank(name.getText())) {
                return PsiLiteralUtil.getStringLiteralContent(((PsiLiteralExpressionImpl) name));
            }
        }
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
        return new ArrayList<>(fieldSet);
    }

    private PsiField[] getPsiFields(PsiClass currentClass, boolean allField) {
        if (allField) {
            return BaseUtil.getAllFields(currentClass);
        }
        return BaseUtil.getFields(currentClass);
    }

    private Field getField(PsiField field) {
        PsiAnnotation annotation = field.getAnnotation("javax.persistence.Id");
        if (null != annotation) {
            return Field.newField(field.getName(), field.getType().getPresentableText(), true);
        }
        return Field.newField(field.getName(), field.getType().getPresentableText());
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
                || StringUtils.equals(DATE_PACKAGE, internalCanonicalText));
    }

}
