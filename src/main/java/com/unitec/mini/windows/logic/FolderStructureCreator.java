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
        String imagesFolderPath = folderPath + File.separator + "Images";
        String desktopFolderPath = folderPath + File.separator + "Desktop";
        
        createFolder(projectDir, folderPath);
        createFolder(projectDir, documentsFolderPath);
        createFolder(projectDir, musicFolderPath);
        createFolder(projectDir, imagesFolderPath);
        createFolder(projectDir, desktopFolderPath);
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
    
    public void createFolderAtPosition(String folderName, int posX, int posY, String belongsTo){
        /*File folderPath = new File(belongsTo, "");
        int counter = 1;
        
        while (Files.exists(folderPath)) {
            // Append counter index to the folder name
            folderName = folderName + "_" + counter;
            folderPath = belongsTo.resolve(folderName);
            counter++;
        }

        // Check if the folder exists
        try {
            // Create the folder
            Files.createDirectory(folderPath);
            FolderButton newButton = new FolderButton(folderName);

            // Add folder attributes to the list
            Folder folder = new Folder(folderName, posX, posY, belongsTo, folderPath.toString(), newButton);

            System.out.println(folder);
            System.out.println("Folder created successfully."+ folderPath.toString());


            newButton.setBorder(BorderFactory.createEmptyBorder());
            newButton.setContentAreaFilled(false);
            newButton.setBorderPainted(false);
            newButton.setFocusPainted(false);
            newButton.setBounds(posX, posY, 100, 35); 
            newButton.setIcon(new javax.swing.ImageIcon("/Users/leo/dev/unitec/proyecto-2/mini-windows/src/main/java/Images/icon_default_folder.png"));
            newButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle button click event here
                    //JOptionPane.showMessageDialog(null, "Button '" + folderName + "' clicked!");
                    System.out.println("Click");
                  
                }
            });
            jPanel_Finder_Dashboard.add(newButton);

            // Repaint the panel to reflect the changes
            jPanel_Finder_Dashboard.revalidate();
            jPanel_Finder_Dashboard.repaint();
            folderList.add(folder);

        } catch (Exception ex) {
            System.err.println("Error creating folder: " + ex.getMessage());
        }*/
    }
}
