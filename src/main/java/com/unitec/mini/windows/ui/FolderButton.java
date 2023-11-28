/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.ui;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.Icon;
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
    private String labelText;

    public FolderButton(String labelText) {
        super();
        this.labelText = labelText;

        setLayout(new BorderLayout());

        // Create a JLabel for the icon
        Icon icon = new ImageIcon("/Users/leo/dev/unitec/proyecto-2/mini-windows/src/main/java/Images/icon_default_folder.png");
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBorder(new EmptyBorder(0, 0, 100, 0));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(iconLabel, BorderLayout.CENTER);

        // Create a JLabel for the text
        JLabel textLabel = new JLabel(labelText);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setBorder(new EmptyBorder(125, 0, 0, 0)); 
        
        add(textLabel, BorderLayout.SOUTH);       
    }
}
