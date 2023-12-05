/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author leonel
 */
public class DirectoryRestrictedFileSystemView extends FileSystemView {

    private final File[] rootDirectories;

    DirectoryRestrictedFileSystemView(File rootDirectory) {
        this.rootDirectories = new File[]{rootDirectory};
    }

    DirectoryRestrictedFileSystemView(File[] rootDirectories) {
        this.rootDirectories = rootDirectories;
    }

    @Override
    public File createNewFolder(File containingDir) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public File[] getRoots() {
        return rootDirectories;
    }

    @Override
    public boolean isRoot(File file) {
        for (File root : rootDirectories) {
            if (root.equals(file)) {
                return true;
            }
        }
        return false;
    }
}
