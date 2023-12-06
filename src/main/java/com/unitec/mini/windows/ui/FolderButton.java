/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.ui;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author leonel
 */
public class FolderButton extends JButton {
    static String projectDir = System.getProperty("user.dir") + "/src/main/users";
    private String labelText;

    public FolderButton(String labelText) {
        super();
        this.labelText = labelText;

        setLayout(new BorderLayout());
        ImageIcon appIcon = new ImageIcon(getClass().getResource("/images/icon_default_folder.png"));
        JLabel iconLabel = new JLabel(appIcon);
        iconLabel.setBorder(new EmptyBorder(0, 0, 100, 0));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(iconLabel, BorderLayout.CENTER);

        JLabel textLabel = new JLabel(labelText);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setBorder(new EmptyBorder(125, 0, 0, 0)); 
        
        add(textLabel, BorderLayout.SOUTH);       
    }
}
