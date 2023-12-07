/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import com.unitec.mini.windows.ui.FolderButton;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author leonel
 */
public class FolderStructureCreator {
    private static final String projectDir = System.getProperty("user.dir") + "/src/main/users";
    
    public static void createDefaultFolderForAdmin(){
        
    }
    public static void createFolderFor(String username){
        String folderPath = username;
        String documentsFolderPath = folderPath + File.separator + "Documents";
        String musicFolderPath = folderPath + File.separator + "Music";
        String imagesFolderPath = folderPath + File.separator + "Images";
        String desktopFolderPath = folderPath + File.separator + "Desktop";
        
        createFolder(projectDir, folderPath);
        createFolder(projectDir, documentsFolderPath);
        createFolder(projectDir, musicFolderPath);
        createFolder(projectDir, imagesFolderPath);
        createFolder(projectDir, desktopFolderPath);
    }

    public static String getUserRootFolder() {
        return System.getProperty("user.dir") + 
                File.separator +"src" + 
                File.separator +"main" +
                File.separator +"users";
    }
    
    public static void createFolder(String parentDir, String folderPath) {
        File folder = new File(parentDir, folderPath);
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                System.out.println("Folder '" + folderPath + "' created successfully.");
            } else {
                System.err.println("Failed to create folder '" + folderPath + "'.");
            }
        } else {
            System.out.println("Folder '" + folderPath + "' already exists.");
        }
    }

    public static void createFile(String parentDir, String filePath) {
        File file = new File(parentDir, filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File '" + filePath + "' created successfully.");
                } else {
                    System.err.println("Failed to create file '" + filePath + "'.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File '" + filePath + "' already exists.");
        }
    }
    

    public JButton createJButtonFolderAtPosition(String folderName, int posX, int posY, String belongsTo){
        Path path = Paths.get(belongsTo);
        FolderButton newButton = null;

        int counter = 1;
        while (Files.exists(path)) {
            counter++;
        }

        try {
            Files.createDirectory(path);
            newButton = new FolderButton(folderName);

            Folder folder = new Folder(folderName, posX, posY, belongsTo, path.toString(), newButton);

            newButton.setBorder(BorderFactory.createEmptyBorder());
            newButton.setContentAreaFilled(false);
            newButton.setBorderPainted(false);
            newButton.setFocusPainted(false);
            newButton.setBounds(posX, posY, 100, 35); 
            
            ImageIcon iconFolder = new ImageIcon(getClass().getResource("/images/icon_default_folder.png"));
            newButton.setIcon(iconFolder);
        } catch (Exception ex) {
            System.err.println("Error creating folder: " + ex.getMessage());
        }
        
        return newButton;
    }
}
