/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import java.io.File;

/**
 *
 * @author leonel
 */
public class FolderStructureCreator {
    static String projectDir = System.getProperty("user.dir") + "/src/main/users";
                
    public static void createFolderFor(String username){
        String folderPath = username;
        String documentsFolderPath = folderPath + File.separator + "Documents";
        String musicFolderPath = folderPath + File.separator + "Music";
        String filesFolderPath = folderPath + File.separator + "Files";
        
        createFolder(projectDir, folderPath);
        createFolder(projectDir, documentsFolderPath);
        createFolder(projectDir, musicFolderPath);
        createFolder(projectDir, filesFolderPath);
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
}
