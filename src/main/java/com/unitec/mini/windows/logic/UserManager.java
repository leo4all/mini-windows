/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static final String DEFAUL_USER = "admin";
    private static final String DEFAUL_PASSWORD = "admin";
    private static String projectDir = System.getProperty("user.dir") + "/src/main/users";
    private static final String USERS_FILE_PATH = "admin" + File.separator + "users.twc";
    private static final Map<String, User> users = new HashMap<>();

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
        if (!users.containsKey(user.getUsername())) {
            users.put(user.getUsername(), user);
            saveUser(user);
            return true;
        } else {
            // User already exists
            System.out.println("User '" + user.getUsername() + "' already exists.");
            return false;
        }
    }
    
    public static boolean authenticateUser(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }
    
    public static String getDefaultPassword(){
        return DEFAUL_PASSWORD;
    }

    public static String getDefaultUser(){
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
