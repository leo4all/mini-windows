/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import java.io.File;
import javax.swing.JButton;

/**
 *
 * @author leonel
 */
public class Folder {
    private String folderName;
    private int posX;
    private int posY;
    private String belongsTo;
    private String currentPath;
    private JButton button;
    
    public Folder(String folderName, int posX, int posY, String belongsTo, String currentPath, JButton button) {
        this.folderName = folderName;
        this.posX = posX;
        this.posY = posY;
        this.belongsTo = belongsTo;
        this.currentPath = currentPath;
        this.button = button;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(String belongsTo) {
        this.belongsTo = belongsTo;
    }

    public String getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }
    
    public JButton getButton(){
        return button;
    }

    @Override
    public String toString() {
        return "Folder{" + "folderName=" + folderName + ", posX=" + posX + ", posY=" + posY + ", belongsTo=" + belongsTo + ", currentPath=" + currentPath + '}';
    }
}
