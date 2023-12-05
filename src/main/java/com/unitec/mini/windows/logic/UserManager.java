/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {

    private static final String DEFAUL_USER = "admin";
    private static final String DEFAUL_PASSWORD = "admin";

    private static String projectDir = System.getProperty("user.dir") + "/src/main/users";
    private static final String USERS_FILE_PATH = "admin" + File.separator + "users.twc";
    private static final Map<String, User> users = new HashMap<>();
    private static UserManager instance = null;

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public static void initialize() {
        loadUserOffsets();
    }

    public static void createDefaultAdminUser() {
        if (!users.containsKey("admin")) {
            User adminUser = new User("John Doe", DEFAUL_USER, DEFAUL_PASSWORD, "administrator");
            users.put("admin", adminUser);
            saveUser(adminUser);
        }
    }

    public static boolean registerUser(User user) {
        if (users.containsKey(user.getUsername())) {
            System.out.println("User '" + user.getUsername() + "' already exists.");
            return false;
        }

        users.put(user.getUsername(), user);
        saveUser(user);
        return true;
    }

    public static boolean authenticateUser(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }

    public static String getDefaultPassword() {
        return DEFAUL_PASSWORD;
    }

    public static String getDefaultUser() {
        return DEFAUL_USER;
    }

    private static void loadUserOffsets() {
        try (RandomAccessFile file = new RandomAccessFile(projectDir + File.separator + USERS_FILE_PATH, "r")) {
            while (file.getFilePointer() < file.length()) {
                String username = file.readUTF();
                String password = file.readUTF();
                String name = file.readUTF();
                String accountType = file.readUTF();
                User user = new User(name, username, password, accountType);
                users.put(username, user);
            }
        } catch (IOException e) {

        }
    }

    private static void saveUser(User user) {
        try (RandomAccessFile file = new RandomAccessFile(projectDir + File.separator + USERS_FILE_PATH, "rw")) {
            file.seek(file.length());
            file.writeUTF(user.getUsername());
            file.writeUTF(user.getPassword());
            file.writeUTF(user.getName());
            file.writeUTF(user.getAccountType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean editUser(User userToEdit, User newUser) {
        if (!users.containsKey(userToEdit.getUsername())) {
            System.out.println("User '" + userToEdit.getUsername() + "' does not exist.");
            return false;
        }

        users.remove(userToEdit.getUsername());
        users.put(newUser.getUsername(), newUser);
        saveUsersToFile();
        String oldFolderPath = "src/main/users" + "/" + userToEdit.getUsername();
        String newFolderName = newUser.getUsername();
        File oldFolder = new File(oldFolderPath);
        String parentPath = oldFolder.getParent();
        String newFolderPath = parentPath + File.separator + newFolderName;
        File oldFolderFile = new File(oldFolderPath);
        File newFolderFile = new File(newFolderPath);
        boolean success = oldFolderFile.renameTo(newFolderFile);

        if (success) {
            System.out.println("Carpeta renombrada exitosamente.");
        } else {
            System.out.println("No se pudo cambiar el nombre de la carpeta.");
            return false;
        }

        return true;
    }

    public static boolean deleteUser(String username) {
        users.remove(username);
        saveUsersToFile(); 
        String Url = "src/main/users" + "/" + username;
        if (!deleteFolderByUrl(Url)) {
            return false;
        }

        return true;
    }

    public static boolean deleteFolderByUrl(String folderPath) {
        File folderToDelete = new File(folderPath);
        return deleteFolder(folderToDelete);
    }

    public static boolean deleteFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file);
                    } else {
                        file.delete();
                    }
                }
            }
            return folder.delete();
        }
        return false;
    }

    private static void saveUsersToFile() {
        try (RandomAccessFile file = new RandomAccessFile(projectDir + File.separator + USERS_FILE_PATH, "rw")) {
            file.setLength(0);

            for (User user : users.values()) {
                file.writeUTF(user.getUsername());
                file.writeUTF(user.getPassword());
                file.writeUTF(user.getName());
                file.writeUTF(user.getAccountType());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getUserByUsername(String username) {
        return users.get(username);
    }

    public static List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public static class User {

        private final String name;
        private final String username;
        private final String password;
        private final String accountType;

        public User(String name, String username, String password, String accountType) {
            this.name = name;
            this.username = username;
            this.password = password;
            this.accountType = accountType;
        }

        public String getName() {
            return name;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getAccountType() {
            return accountType;
        }
    }
}
