/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;


public class User {
    private String name;
    private String username;
    private String accountType;
    private String password;

    public User(String name, String username, String password, String accountType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setPassword(String password) {
        this.password = password;
    }
        
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", username=" + username + ", accountType=" + accountType + ", password=" + password + '}';
    }
}
