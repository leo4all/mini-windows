/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import com.unitec.mini.windows.ui.FolderButton;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    private static final String projectDir = System.getProperty("user.dir");
    private static final String usersPath =  File.separator + "src"+
            File.separator+"main"+
            File.separator+"users";

    public static void createDefaultFolderForAdmin(){
        /**
         * Initialize default folder root structure
         * Initialize default files for users and followers
         */
        createDefaultFoldersFor(UserManager.getDefaultUser());
        createFileWithContent(UserManager.getDefaultUser(), "users.twc", null, null );
        createFileWithContent(UserManager.getDefaultUser(), "twitter_accounts.twc", null, null );
    }

    public static void createDefaultFoldersFor(String username){
        String location = Paths.get(projectDir, usersPath, username).toString();
        createFolder(location);
        
        for (String folder : new String[]{"Documents", "Music", "", "Images", "Desktop"}) {
            createFolder(location + File.separator + folder);  
        }
    }

    public static boolean createFolder(String location) {
        Path path = Paths.get(location);
        
        if (!Files.exists(path)) {     
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }
    
    public static boolean createFileWithContent(String username, String fileName, String folderPath, Object content){
        String location = Paths.get(projectDir, usersPath, username).toString();        

        // Create default user folder in case user folder no exists.
        if(!createFolder(location)){
            return false;
        }
        
        // Create a new folder in case provided, related to current user.
        if (folderPath != null && !folderPath.isEmpty()) {
            String newFolder = Paths.get(location, folderPath).toString();
            if(!createFolder(location)){
                return false;
            }

            location = newFolder;
        }

        // Create file, related to current location.
        String filePath = Paths.get( location, fileName).toString();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        
        // Write content to file in case content is provided.
        if (content != null && file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(content.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }
    
    public static JButton createButtonFolderAtPosition(String belongsTo,  String folderRoot, String newFolderName, int posX, int posY){
        Path path = Paths.get(projectDir, usersPath, belongsTo, folderRoot, newFolderName);
        FolderButton newButton = null;

        int counter = 1;
        while (Files.exists(path)) {
            newFolderName += "1"; 
            counter++;
        }

        try {
            Files.createDirectory(path);
            
            newButton = new FolderButton(newFolderName);
            newButton.setBorder(BorderFactory.createEmptyBorder());
            newButton.setContentAreaFilled(false);
            newButton.setBorderPainted(false);
            newButton.setFocusPainted(false);
            newButton.setBounds(posX, posY, 100, 35); 
            
            //ImageIcon iconFolder = new ImageIcon(getClass().getResource("/images/icon_default_folder.png"));
            //newButton.setIcon(iconFolder);
        } catch (Exception ex) {
            System.err.println("Error creating folder: " + ex.getMessage());
            return null;
        }
        
        return newButton;
    }
    
    public static boolean isFolderExistsForUser(String username){
        Path location = Paths.get(projectDir, usersPath, username);
        return Files.exists(location) && Files.isDirectory(location);
    }
    
    public static boolean createFile(String parentDir, String filePath) {
        File file = new File(parentDir, filePath);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    return false;
                } 
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        
        return true;
    }

    public static String getUserRootFolder(String username) {
        return Paths.get(projectDir, usersPath, username).toString();
    }
        
    public static String getProjectPath(){
        return  projectDir;
    }
}
