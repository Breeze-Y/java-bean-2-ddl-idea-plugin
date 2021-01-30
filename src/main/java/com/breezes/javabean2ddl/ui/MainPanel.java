package com.breezes.javabean2ddl.ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/1/30 01:37
 * @description
 */
public class MainPanel extends JFrame {
    private JPanel content;
    private JButton copy;
    private JTextPane sqlContentPanel;
    private JRadioButton removeRadioButton;

    private String contentTxt;

    public MainPanel(String contentTxt) throws HeadlessException {
        setSize(500, 600);
        setTitle("Java Bean Convert DDL");
        setContentPane(content);
        // 屏幕居中
        setLocationRelativeTo(null);

        sqlContentPanel.setText(contentTxt);
        setVisible(true);
    }

}
