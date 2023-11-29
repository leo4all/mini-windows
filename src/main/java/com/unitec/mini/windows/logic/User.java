/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;


public class User {
    private final String name;
    private final String username;
    private final String accountType;

    public User(String name, String username, String password, String accountType) {
        this.name = name;
        this.username = username;
        this.accountType = accountType;
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
}
