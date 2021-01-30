package com.breezes.javabean2ddl.main;

import com.breezes.javabean2ddl.model.Field;
import com.breezes.javabean2ddl.ui.MainPanel;
import com.breezes.javabean2ddl.utils.BaseUtil;
import com.breezes.javabean2ddl.utils.DdlUtil;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.java.PsiLiteralExpressionImpl;
import com.intellij.psi.util.PsiLiteralUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import static com.breezes.javabean2ddl.constant.Constant.DATE_PACKAGE;
import static com.breezes.javabean2ddl.constant.Constant.STRING_PACKAGE;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/1/29 18:56
 * @description 插件主入口
 */
public class MainAction extends AnAction {

    private static PsiClass CURRENT_CLASS;

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        PsiFile FILE = anActionEvent.getData(CommonDataKeys.PSI_FILE);
        CURRENT_CLASS = BaseUtil.getClassEntity(FILE);
        assert CURRENT_CLASS != null;
        String tableName = getTableName();
        /*获取当前类所有字段*/
        List<Field> fieldList = getFieldList();
        String script = DdlUtil.buildDdlScript(tableName, fieldList);
        mainPanelInit(script);
    }

    private String getTableName() {
        PsiAnnotation annotation = CURRENT_CLASS.getAnnotation("javax.persistence.Table");
        if (null != annotation && null != annotation.findAttributeValue("name")) {
            PsiAnnotationMemberValue name = annotation.findAttributeValue("name");
            if (null != name && StringUtils.isNotBlank(name.getText())) {
                return PsiLiteralUtil.getStringLiteralContent(((PsiLiteralExpressionImpl) name));
            }
        }
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, Objects.requireNonNull(CURRENT_CLASS.getName()));
    }

    /**
     * 获取需要转换的字段
     *
     * @return
     */
    private List<Field> getFieldList() {
        PsiField[] fields = BaseUtil.getFields(CURRENT_CLASS);
        List<Field> fieldList = Lists.newArrayList();
        for (PsiField field : fields) {
            if (isNeedAddConvert(field)) {
                addField(fieldList, field);
            }
        }
        return fieldList;
    }

    private void addField(List<Field> fieldList, PsiField field) {
        PsiAnnotation annotation = field.getAnnotation("javax.persistence.Id");
        if (null != annotation) {
            fieldList.add(Field.newField(field.getName(), field.getType().getPresentableText(), true));
            return;
        }
        fieldList.add(Field.newField(field.getName(), field.getType().getPresentableText()));
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

    /**
     * 主面板初始化
     *
     * @param script
     */
    private void mainPanelInit(String script) {
        new MainPanel(script);
    }

}
