/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.ui;

import java.awt.BorderLayout;
import java.io.File;
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
    private String location;
    
    public FolderButton(String folderName) {
        this.labelText = folderName;
        createFolder();
    }

    public FolderButton(File file) {
        super();
        this.labelText = file.getName();
        this.location = file.getAbsolutePath();
        createFolder();        
    }

    private void createFolder(){
        setLayout(new BorderLayout());
        ImageIcon appIcon = new ImageIcon(getClass().getResource("/images/icon_default_folder.png"));
        JLabel iconLabel = new JLabel(appIcon);
        iconLabel.setBorder(new EmptyBorder(0, 0, 5, 0));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(iconLabel, BorderLayout.CENTER);

        JLabel textLabel = new JLabel(labelText);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setBorder(new EmptyBorder(5, 0, 0, 0)); 
        setFocusable(false);
        add(textLabel, BorderLayout.SOUTH);       
    }

    public String getFolderName() {
        return labelText;
    }

    public void setFolderName(String labelText) {
        this.labelText = labelText;
    }
    
    public void setFolderLocation(String location){
        this.location = location;
    }
    
    public String getFolderLocation() {
        return location;
    }
}
