package com.breezes.javabean2ddl.ui;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/1/30 01:37
 * @description
 */
public class MainPanel extends JFrame {
    private JPanel content;
    private JButton copy;
    private JRadioButton removeRadioButton;
    private JTextArea sqlContentPanel;

    private String contentTxt;

    public MainPanel(String contentTxt) throws HeadlessException {
        setSize(500, 600);
        setTitle("Java Bean Convert DDL");
        setContentPane(content);
        // 屏幕居中
        setLocationRelativeTo(null);

        sqlContentPanel.setText(contentTxt);
        setVisible(true);

        copyButtonInit(sqlContentPanel);
    }

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

    private void showNotify() {
        Notification notification = new Notification("System Clipboard",
                "Notify success", "复制成功 !", NotificationType.INFORMATION);
        Notifications.Bus.notify(notification);
    }

}
