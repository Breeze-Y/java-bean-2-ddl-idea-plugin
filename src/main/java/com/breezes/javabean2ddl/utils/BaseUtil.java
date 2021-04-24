package com.breezes.javabean2ddl.utils;

import com.intellij.lang.jvm.types.JvmPrimitiveTypeKind;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

/**
 * @author breezes_y@163.com
 * @date 2021/1/30 14:40
 * @description
 */
public class BaseUtil {

    private static final String QUOT = "&quot;";

    /**
     * 获取文件的所有类
     *
     * @param element
     * @return
     */
    public static List<PsiClass> getClasses(PsiElement element) {
        List<PsiClass> elements = Lists.newArrayList();
        List<PsiClass> classElements = PsiTreeUtil.getChildrenOfTypeAsList(element, PsiClass.class);
        elements.addAll(classElements);
        for (PsiClass classElement : classElements) {
            // 这里用了递归的方式获取内部类
            elements.addAll(getClasses(classElement));
        }
        return elements;
    }

    /**
     * 获取当前类
     *
     * @param element
     * @return
     */
    public static PsiClass getClassEntity(PsiElement element) {
        return PsiTreeUtil.getChildOfType(element, PsiClass.class);
    }

    /**
     * 获取当前类的所有字段(包括父类)
     *
     * @param psiClass
     * @return
     */
    public static PsiField[] getAllFields(PsiClass psiClass) {
        if (null == psiClass) {
            return new PsiField[0];
        }
        return psiClass.getAllFields();
    }

    /**
     * 获取当前类的所有字段
     *
     * @param psiClass
     * @return
     */
    public static PsiField[] getFields(PsiClass psiClass) {
        if (null == psiClass) {
            return new PsiField[0];
        }
        return psiClass.getFields();
    }

    /**
     * 是否基础类型
     *
     * @param field
     * @return
     */
    public static boolean isPrimitiveType(PsiField field) {
        if (null == field) {
            return false;
        }

        String canonicalText = field.getType().getCanonicalText();
        JvmPrimitiveTypeKind kindByName = JvmPrimitiveTypeKind.getKindByName(canonicalText);
        if (null != kindByName) {
            return true;
        }

        JvmPrimitiveTypeKind kindByFqn = JvmPrimitiveTypeKind.getKindByFqn(canonicalText);
        return null != kindByFqn;
    }

    /**
     * 去除字符首尾 "" 标记，转换成常规字符串
     * <pre>
     *     复制于PsiLiteralUtil.getStringLiteralContent
     *     为了消除高版本因为API变动，IDEA版本校验的警告
     * </pre>
     *
     * @param expression
     * @return
     */
    public static String getStringLiteralContent(PsiLiteralExpression expression) {
        String text = expression.getText();
        int textLength = text.length();
        if (textLength > 1 && text.charAt(0) == '\"' && text.charAt(textLength - 1) == '\"') {
            return text.substring(1, textLength - 1);
        }
        if (textLength > QUOT.length() && text.startsWith(QUOT) && text.endsWith(QUOT)) {
            return text.substring(QUOT.length(), textLength - QUOT.length());
        }
        return null;
    }
}
