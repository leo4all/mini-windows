/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author leonel
 */
public class TwitterUserManager {

    private static final String DEFAULT_ACCOUNT_FILE = "twitter_accounts.dat";
    private static String projectDir = System.getProperty("user.dir") + "/src/main/users";
    private Map<String, TwitterAccount> accounts = new HashMap<>();
    private static TwitterUserManager instance = null;

    public static TwitterUserManager getInstance() {
        if (instance == null) {
            instance = new TwitterUserManager();
        }
        return instance;
    }

    

    public void saveAccounts() {
        String path = Paths.get(projectDir, UserManager.getDefaultUser(), DEFAULT_ACCOUNT_FILE).toString();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error writing accounts to file: " + path);
            e.printStackTrace();
        }
    }

    public void loadAccounts() {
        String path = Paths.get(projectDir, UserManager.getDefaultUser(), DEFAULT_ACCOUNT_FILE).toString();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            accounts = (Map<String, TwitterAccount>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + path);
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading accounts from file: " + path);
            e.printStackTrace();
        }
    }

    public boolean saveUser(TwitterAccount account) {
        accounts.put(account.getUsername(), account);
        saveAccounts();

        try {
            TweetManager.createDefaultTweetFolder(account.getUsername());
            return true;
        } catch (Exception e) {
            System.out.println("Error creating tweet folder for user: " + account.getUsername());
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticateUser(String username, String password) {
        if (accounts.containsKey(username)) {
            TwitterAccount account = accounts.get(username);
            return account.getPassword().equals(password);
        }
        return false;
    }

    public TwitterAccount getAccountByUsername(String username) {
        return accounts.get(username);
    }
}
