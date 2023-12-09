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
    private static final String DEFAULT_USER = "admin";
    private static final String DEFAULT_PASSWORD = "admin";

    private static String projectDir = System.getProperty("user.dir") + "/src/main/users";
    private static final String USERS_FILE_PATH = DEFAULT_USER + File.separator + "users.twc";
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
     
    public static void createDefaultAdminUser() {
        if (!users.containsKey("admin")) {
            User adminUser = new User("John Doe", DEFAULT_USER, DEFAULT_PASSWORD, "administrator");
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

    public static boolean editUser(User user, User newUser) {
        if (!users.containsKey(user.getUsername())) {
            return false;
        }

        users.remove(user.getUsername());
        users.put(newUser.getUsername(), newUser);
   
        return true;
    }

    public static boolean deleteUser(String username) {
      
        return true;
    }

    public static User getUserByUsername(String username) {
        return users.get(username);
    }

    public static List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public static String getDefaultPassword() {
        return DEFAULT_PASSWORD;
    }

    public static String getDefaultUser() {
        return DEFAULT_USER;
    }
}
