/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import java.io.File;
import java.util.Date;

/**
 *
 * @author leonel
 */
public class TwitterAccount {

    private String name;
    private String username;
    private String password;
    private char gender;
    int age;
    private int status;
    private String picturePath;
    private Date dateReg;
    private String belongsTo;

    public TwitterAccount() {

    }

    public TwitterAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public TwitterAccount(
            String name,
            String username,
            String password,
            char gender,
            int age,
            int status,
            String picturePath,
            String belongsTo
    ) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.status = status;
        this.picturePath = picturePath;
        this.belongsTo = belongsTo;
        this.dateReg = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPictFile() {
        return picturePath;
    }

    public void setPictFile(String path) {
        this.picturePath = path;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

    public String getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(String belongsTo) {
        this.belongsTo = belongsTo;
    }

    @Override
    public String toString() {
        return "TwitterAccount{" + "name=" + name + ", "
                + "username=" + username + ", "
                + "password=" + password + ", "
                + "gender=" + gender + ", "
                + "age=" + age + ", "
                + "status=" + status + ", "
                + "pictFile=" + picturePath + ", "
                + "dateReg=" + dateReg + ", "
                + "belongsTo=" + belongsTo + 
        '}';
    }

}
