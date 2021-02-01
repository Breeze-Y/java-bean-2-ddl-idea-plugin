package com.breezes.javabean2ddl.ui;

import com.breezes.javabean2ddl.model.Field;
import com.breezes.javabean2ddl.service.MainService;
import com.breezes.javabean2ddl.utils.DdlFormatUtil;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.psi.PsiClass;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/1/30 01:37
 * @description
 */
public class MainPanel extends JFrame {
    private JPanel content;
    private JButton copy;
    private JRadioButton removeSuperPropertyRadio;
    private JTextArea sqlContentPanel;
    private JLabel jlabel;

    private String contentTxt;
    private final PsiClass currentClass;
    private final MainService mainService;

    public MainPanel(String contentTxt, PsiClass currentClass) throws HeadlessException {
        this.contentTxt = contentTxt;
        this.currentClass = currentClass;
        this.mainService = new MainService();

        copy.setText("一键复制");
        jlabel.setText("去除父类属性:");

        setSize(500, 600);
        setTitle("Java Bean Convert DDL");
        setContentPane(content);
        // 屏幕居中
        setLocationRelativeTo(null);
        sqlContentPanelInit(contentTxt);
        // 复制按钮
        copyButtonInit(sqlContentPanel);
        // 移除父类属性单选按钮
        removeSuperPropertyRadioInit(this);

        addKeyListener(getKeyAdapter());
        setVisible(true);
    }

    private void sqlContentPanelInit(String contentTxt) {
        // 设置文本
        sqlContentPanel.setText(contentTxt);
        sqlContentPanel.addKeyListener(getKeyAdapter());
    }

    private KeyAdapter getKeyAdapter() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                super.keyPressed(keyEvent);
                if (null == keyEvent) {
                    return;
                }
                // 监听ESC键
                if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }
        };
    }

    /**
     * 移除父类属性单选按钮初始化
     *
     * @param mainPanel
     */
    private void removeSuperPropertyRadioInit(MainPanel mainPanel) {
        removeSuperPropertyRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tableName = mainService.getTableName(currentClass);
                List<Field> fieldList = getFieldListByRadioSelect();
                String script = DdlFormatUtil.buildDdlScript(tableName, fieldList);
                sqlContentPanel.setText(script);
                /*按钮点击完后, 让mainPanel获取焦点，以便主面板可以继续监听键盘事件*/
                mainPanel.requestFocus();
            }
        });
    }

    private List<Field> getFieldListByRadioSelect() {
        if (removeSuperPropertyRadio.isSelected()) {
            /*获取当前类所有字段*/
            return mainService.getFieldList(currentClass, false);
        }
        return mainService.getFieldList(currentClass);
    }

    /**
     * 复制安妮初始化
     *
     * @param contentTxt 操作的内容文本域
     */
    private void copyButtonInit(JTextArea contentTxt) {
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                // 封装文本内容
                Transferable trans = new StringSelection(contentTxt.getText());
                // 把文本内容设置到系统剪贴板
                clipboard.setContents(trans, null);
                showNotify();
                dispose();
            }
        });
    }

    /**
     * 复制成功的消息通知
     */
    private void showNotify() {
        Notification notification = new Notification("System Clipboard",
                "Notify success", "复制成功 !", NotificationType.INFORMATION);
        Notifications.Bus.notify(notification);
    }

    public String getContentTxt() {
        return contentTxt;
    }

    public void setContentTxt(String contentTxt) {
        this.contentTxt = contentTxt;
    }

    public PsiClass getCurrentClass() {
        return currentClass;
    }

}
