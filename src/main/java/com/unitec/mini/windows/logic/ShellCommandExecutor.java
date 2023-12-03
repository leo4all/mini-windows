/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;


public class ShellCommandExecutor {
    private JTextArea outputArea;
    private File currentDirectory;
    private File rootDirectory;
    private static int caretBlinkRate = 500;
    private JInternalFrame internalFrame;

    public ShellCommandExecutor(JInternalFrame jFrame, JTextArea outputArea, File directory) {
        this.outputArea = outputArea;
        this.rootDirectory = directory;
        this.currentDirectory = directory;
        this.internalFrame = jFrame;

        outputArea.append(this.getCurrentPath() + " >");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
        outputArea.getCaret().setVisible(true);
        outputArea.getCaret().setBlinkRate(500);
        outputArea.setCaretColor(Color.WHITE);
        
    }
    
    public void executeCommand(String command) {
        outputArea.append("\n");
        
        if ("exit".equalsIgnoreCase(command.trim())) {
            if (internalFrame != null && internalFrame.isVisible()) {
                internalFrame.doDefaultCloseAction();
            }
        }
        
        if ("clear".equalsIgnoreCase(command.trim())) {
            outputArea.setText("");
            outputArea.append(this.getCurrentPath() + " >");
            outputArea.setCaretPosition(outputArea.getDocument().getLength());
            return;
        }
        
        if ("info".equalsIgnoreCase(command.trim())) {
            outputArea.append(getCommandsInfo());
            outputArea.append(this.getCurrentPath() + " >");
            outputArea.setCaretPosition(outputArea.getDocument().getLength());
            return;
        }
        
 
        try {
            ProcessBuilder processBuilder;
            String osName = System.getProperty("os.name").toLowerCase();

            if (osName.contains("win")) {
                processBuilder = new ProcessBuilder("cmd", "/c", command);
            } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("mac")) {
                processBuilder = new ProcessBuilder("/bin/bash", "-c", command);
            } else {
                throw new UnsupportedOperationException("Unsupported operating system");
            }

            processBuilder.directory(currentDirectory);

            Process process = processBuilder.start();
            InputStream lsOut = process.getInputStream();
            InputStreamReader r = new InputStreamReader(lsOut);
            BufferedReader in = new BufferedReader(r);

            String line;
            while ((line = in.readLine()) != null) {
                outputArea.append(line);
                outputArea.append("\n");
            }

            
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                outputArea.append("Error executing command. Exit code: " + exitCode + "\n");
            }

            if (command.startsWith("cd ")) {
                String directoryName = command.substring(3).trim();
                File newDirectory;

                if (directoryName.equals("..")) {
                    newDirectory = currentDirectory.getParentFile();
                } else if (directoryName.equals("~")) {
                    newDirectory = rootDirectory;
                } else {
                    newDirectory = new File(currentDirectory, directoryName);
                }

                if (isValidDirectory(newDirectory)) {
                    currentDirectory = newDirectory;
                } else {
                    outputArea.append("Error: Invalid directory or directory does not exist.\n");
                }
            }

        } catch (IOException | InterruptedException ex) {
            outputArea.append("Error: " + ex.getMessage() + "\n");
        }

        outputArea.append(this.getCurrentPath() + " >");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
        DefaultCaret caret = (DefaultCaret) outputArea.getCaret();
        blinkCaret(caret);
    }

    private boolean isValidDirectory(File directory) {
       return directory.exists() && directory.isDirectory();
    }

    private boolean isWithinRoot(File directory) {
        try {
            String rootPath = rootDirectory.getCanonicalPath();
            String directoryPath = directory.getCanonicalPath();
            return directoryPath.startsWith(rootPath);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String getCurrentPath() {
        String rootPath = rootDirectory.getName();
        String currentPath = currentDirectory.getAbsolutePath();

        if (currentPath.equals(rootDirectory.getAbsolutePath())) {
            return rootPath;
        }

        return rootPath + " / " + currentDirectory.getName();
    }
    
    private void blinkCaret(DefaultCaret caret) {        
        outputArea.getCaret().setVisible(true);
        outputArea.getCaret().setBlinkRate(500);
        outputArea.setCaretColor(Color.WHITE);
    }
    
    private String getCommandsInfo(){
         return """
        General commands Manual

        Menu:
        * pwd :  Print the name of the current working directory.
        * cd :   Change the current directory
        * rm:    Remove files.
        * rmdir: Remove empty directories.
        * mv:    Rename files.
        * mkdir: Create directories.
        * ls:    List directory contents.
        * date:  Print/set system date and time.
        * cp:    Copy files.
        * cat:   Concatenate and write files.
        * dir:   List directories briefly.
        * echo:  Print a line of text.
        * exit:  Close the app \n""";
    }
}
