package com.breezes.javabean2ddl.main;

import com.breezes.javabean2ddl.model.Field;
import com.breezes.javabean2ddl.service.MainService;
import com.breezes.javabean2ddl.ui.MainPanel;
import com.breezes.javabean2ddl.utils.BaseUtil;
import com.breezes.javabean2ddl.utils.DdlFormatUtil;
import com.breezes.javabean2ddl.utils.SqlTypeMapUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author breezes_y@163.com
 * @date 2021/1/29 18:56
 * @description 插件主入口
 */
public class MainAction extends AnAction {

    public static ConcurrentHashMap<String, String> translationMap;

    public static ConcurrentHashMap<String, SqlTypeMapUtil.ConvertBean> convertMap;

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        translationMapInit();
        convertMapInit();
        PsiFile file = anActionEvent.getData(CommonDataKeys.PSI_FILE);
        PsiClass currentClass = BaseUtil.getClassEntity(file);
        assert currentClass != null;
        MainService mainService = new MainService();
        String tableName = mainService.getTableName(currentClass);
        /*获取当前类所有字段*/
        List<Field> fieldList = mainService.getFieldList(currentClass);
        String script = DdlFormatUtil.buildDdlScript(tableName, fieldList);
        mainPanelInit(script, currentClass, mainService);
    }

    private void convertMapInit() {
        if (null != convertMap) {
            convertMap.clear();
        }
        convertMap = SqlTypeMapUtil.getInstance().convertMapInit();
    }

    private void translationMapInit() {
        if (null != translationMap) {
            translationMap.clear();
        } else {
            translationMap = new ConcurrentHashMap<>();
        }
    }

    /**
     * 主面板初始化
     *
     * @param script
     * @param currentClass 操作的当前类
     * @param mainService
     */
    private void mainPanelInit(String script, PsiClass currentClass, MainService mainService) {
        new MainPanel(script, currentClass, mainService);
    }

}
