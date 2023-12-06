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

/**
 *
 * @author leonel
 */
public class TwitterUserManager {

    private static String projectDir = System.getProperty("user.dir") + "/src/main/users";
    private static final String USERS_FILE_PATH = "admin" + File.separator + "twitter_users.twc";
    private static final Map<String, TwitterAccount> users = new HashMap<>();
    private static TwitterUserManager instance = null;
    
    public static TwitterUserManager getInstance() {
        if (instance == null) {
            instance = new TwitterUserManager();
        }
        return instance;
    }


    public TwitterUserManager() {

    }

    public static boolean authenticateUser(String username, String password) {
        return true;
        /*if (username.equals("admin") && password.equals("admin")) {
            //return true;
        }*/
    }
    
    public static boolean registerUser(TwitterAccount user) {
        if (users.containsKey(user.getUsername())) {
            return false;
        }
        
        saveUser(user);
        users.put(user.getBelongsTo(), user);
        return true;
    }
    
    private static void saveUser(TwitterAccount user) {
        try (RandomAccessFile file = new RandomAccessFile(projectDir + File.separator + USERS_FILE_PATH, "rw")) {
            file.seek(file.length());
            file.writeUTF(user.getUsername());
            file.writeUTF(user.getPassword());
            file.writeUTF(user.getName());
            file.writeUTF(user.getBelongsTo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
