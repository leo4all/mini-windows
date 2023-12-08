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
public class FileButton extends JButton{
    private String labelText;
    private String fileExtension;
    private String location;
    
    public FileButton(String fileName) {
        labelText = fileName;
    }
    
    public FileButton(File file) {
        super();
        labelText = file.getName();
        location = file.getAbsolutePath();
        createFile();
    }
    
    private void createFile(){
        setLayout(new BorderLayout());
        JLabel iconLabel = new JLabel(getImageIcon(labelText));
        iconLabel.setBorder(new EmptyBorder(0, 0, 5, 0));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(iconLabel, BorderLayout.CENTER);

        JLabel textLabel = new JLabel(labelText);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setBorder(new EmptyBorder(5, 0, 0, 0)); 
        setFocusable(false);
        add(textLabel, BorderLayout.SOUTH);  
        this.fileExtension = extractFileExtension(labelText);
    }
    
    private ImageIcon getImageIcon(String fileName){
        String extension = extractFileExtension(fileName);
        switch (extension) {
            case "txt":
                return (new ImageIcon(getClass().getResource("/images/finder/icon-txt.png")));
            case "zip":
                return (new ImageIcon(getClass().getResource("/images/finder/icon-zip.png")));
            case "mp3":
                return (new ImageIcon(getClass().getResource("/images/finder/icon-mp3.png")));                
            case "wav":
            case "mp4a":
            case "wma":
            case "mp4":
                return (new ImageIcon(getClass().getResource("/images/finder/icon-audio.png")));   
            case "jpg":
            case "jpeg":
            case "png":
            case "gif":
                return (new ImageIcon(getClass().getResource("/images/finder/icon-image-1.png")));
            default:
                return (new ImageIcon(getClass().getResource("/images/finder/icon-file.png")));
        }
    }
    
    private String extractFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setLocation(String location){
        this.location = location;
    }
    
    public String getFileLocation(){
        return location;
    }

    public String getFileName() {
        return labelText;
    }

    public void setFileName(String labelText) {
        this.labelText = labelText;
    }
 
}
