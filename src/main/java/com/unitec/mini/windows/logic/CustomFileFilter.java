/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author leonel
 */
public class CustomFileFilter extends FileFilter{

    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getAbsolutePath().endsWith(".txt");
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDescription() {
        return "Text documents (.txt)";
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
