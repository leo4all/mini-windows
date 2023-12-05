/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leonel
 */
public class UserFollowingList {
    private List<String> followingUsers = new ArrayList<>();

    public void followUser(String username) {
        followingUsers.add(username);
    }

    public List<String> getFollowingUsers() {
        return followingUsers;
    }
}
