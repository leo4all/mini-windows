/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leonel
 */
public class TwitterUserManager {

    private static String projectDir = System.getProperty("user.dir") + "/src/main/users";
    private static final String USERS_FILE_PATH = "admin" + File.separator + "twitter_users.twc";
    private static final Map<String, TwitterAccount> accounts = new HashMap<>();
    private static TwitterUserManager instance = null;
    
    public static TwitterUserManager getInstance() {
        if (instance == null) {
            instance = new TwitterUserManager();
        }
        return instance;
    }


    public TwitterUserManager() {

    }
    
    public static void initialize() {
        loadAccounts();
    }

    public static TwitterAccount getAccountByUsername(String username) {
        return accounts.get(username);
    }

    public static boolean authenticateUser(String username, String password) {
        TwitterAccount account = accounts.get(username);
        System.out.println(account);
        return account != null && account.getPassword().equals(password);
    }

    private static void loadAccounts() {
        try (RandomAccessFile file = new RandomAccessFile(projectDir + File.separator + USERS_FILE_PATH, "r")) {
            while (file.getFilePointer() < file.length()) {
                String name = file.readUTF();
                String username = file.readUTF();
                String password = file.readUTF();
                int age = file.readInt();
                String dateRegistered = file.readUTF();
                char gender = file.readChar();
                int status = file.readInt();
                String belongsTo = file.readUTF();

               TwitterAccount account = new TwitterAccount(
                        name,
                        username,
                        password,
                        gender,
                        age,
                        status,
                        new File(""),
                         belongsTo
                );
               
               SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy"); 
               account.setDateReg(formatter.parse(dateRegistered));
               accounts.put(username, account);
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (ParseException ex) {
            Logger.getLogger(TwitterUserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean registerUser(TwitterAccount account) {
        if (accounts.containsKey(account.getUsername())) {
            return false;
        }
        
        saveUser(account);
        accounts.put(account.getBelongsTo(), account);
        return true;
    }
    
    private static void saveUser(TwitterAccount account) {
        try (RandomAccessFile file = new RandomAccessFile(projectDir + File.separator + USERS_FILE_PATH, "rw")) {
            file.seek(file.length());
            file.writeUTF(account.getName());
            file.writeUTF(account.getUsername());
            file.writeUTF(account.getPassword());
            file.writeInt(account.getAge());
            file.writeUTF(account.getDateReg().toString());
            file.writeChar(account.getGender());
            file.writeInt(account.getStatus());
            file.writeUTF(account.getBelongsTo());
            System.out.println(account);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
