package com.breezes.javabean2ddl.utils;

import com.intellij.lang.jvm.types.JvmPrimitiveTypeKind;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiType;
import com.intellij.psi.util.PsiTreeUtil;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/1/30 14:40
 * @description
 */
public class BaseUtil {

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
        PsiType type = field.getType();
        JvmPrimitiveTypeKind kindByName = JvmPrimitiveTypeKind.getKindByName(type.getInternalCanonicalText());
        if (null != kindByName) {
            return true;
        }
        JvmPrimitiveTypeKind kindByFqn = JvmPrimitiveTypeKind.getKindByFqn(type.getInternalCanonicalText());
        return null != kindByFqn;
    }

}
