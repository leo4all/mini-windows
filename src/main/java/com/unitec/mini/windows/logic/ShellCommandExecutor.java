/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JTextArea;


public class ShellCommandExecutor {
    private JTextArea outputArea;
    private File currentDirectory;
    private File rootDirectory;

    public ShellCommandExecutor(JTextArea outputArea, File directory) {
        this.outputArea = outputArea;
        this.rootDirectory = directory;
        this.currentDirectory = directory;
    }
    
    public void executeCommand(String command) {
        outputArea.append(this.getCurrentPath() + " > " + command + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
        
        if ("clear".equalsIgnoreCase(command.trim())) {
            outputArea.setText("");
            outputArea.append(this.getCurrentPath() + " > " + command + "\n");
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

                return;
            }

        } catch (IOException | InterruptedException ex) {
            // Handle exception
            outputArea.append("Error: " + ex.getMessage() + "\n");
        }

        outputArea.append("\n");
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
            e.printStackTrace(); // Handle the exception as needed
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
}
