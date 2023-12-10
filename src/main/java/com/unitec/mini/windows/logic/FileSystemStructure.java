/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author leonel
 */
public class FileSystemStructure {

    public static DefaultMutableTreeNode getStructureForLocation(String username, String folder) {
        String folderPath = FolderStructureCreator.getUserRootFolder(username);
        String location = Paths.get(folderPath, folder).toString();

        File rootFile = new File(location);
        
        if (!rootFile.exists() || !rootFile.isDirectory()) {
            throw new IllegalArgumentException("Invalid location: " + location);
        }

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootFile);
        File parentFile = (File) root.getUserObject();
        File[] files = parentFile.listFiles();

        if (files != null) {
            for (File file : files) {
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(file);
                root.add(childNode);
            }
        }

        return root;
    }


    public static FileNode buildFileSystemStructure(String username) throws IOException {
        String location = FolderStructureCreator.getUserRootFolder(username);
        Path path = Paths.get(location);
        FileNode root = new FileNode(path.getFileName().toString());

        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            private FileNode currentNode = root;

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                String folderName = path.relativize(dir).toString();
                FileNode folderNode = new FileNode(folderName);
                currentNode.addChild(folderNode);
                currentNode = folderNode;

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String fileName = path.relativize(file).toString();

                if (!shouldExclude(fileName)) {
                    FileNode fileNode = new FileNode(fileName);
                    currentNode.addChild(fileNode);
                }

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                currentNode = currentNode.getParent();
                return FileVisitResult.CONTINUE;
            }
        });

        return root;
    }

    public static DefaultMutableTreeNode getFolderTree(String username) {
        String location = FolderStructureCreator.getUserRootFolder(username);
        Path path = Paths.get(location);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(path.getFileName().toString());
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                private DefaultMutableTreeNode currentNode = root;

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    String folderName = path.relativize(dir).toString();
                    DefaultMutableTreeNode folderNode = new DefaultMutableTreeNode(getLastPathComponent(folderName), true); // "true" indicates it's a folder
                    currentNode.add(folderNode);
                    currentNode = folderNode;

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileName = path.relativize(file).toString();

                    // Exclude specific files
                    if (!shouldExclude(fileName)) {
                        DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(getLastPathComponent(fileName), false); // "false" indicates it's a file
                        currentNode.add(fileNode);
                    }

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    currentNode = (DefaultMutableTreeNode) currentNode.getParent(); // Move back up the tree after visiting a directory
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return root;

    }

    private static String getLastPathComponent(String path) {
        return Paths.get(path).getFileName().toString();
    }

    private static boolean shouldExclude(String fileName) {
        return fileName.equals("twitter_accounts.twc")
                || fileName.equals("user_accounts.twc")
                || fileName.equals("tweets.twc")
                || fileName.equals("following.twc")
                || fileName.equals("followers.twc");
    }

    public static class FileNode {

        private String name;
        private List<FileNode> children;
        private FileNode parent;

        public FileNode(String name) {
            this.name = name;
            this.children = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public List<FileNode> getChildren() {
            return children;
        }

        public FileNode getParent() {
            return parent;
        }

        public void addChild(FileNode child) {
            child.parent = this;
            children.add(child);
        }
    }
}
