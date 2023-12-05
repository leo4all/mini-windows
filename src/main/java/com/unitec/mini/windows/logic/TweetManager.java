/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author leonel
 */
public class TweetManager {
    private List<TweetPost> tweetPosts = new ArrayList<>();
    private String projectDir = System.getProperty("user.dir") + "/src/main/users";
    private String currentUser;
     
    public TweetManager(String loggedUser){
        this.currentUser = loggedUser;
    }
    
    public void addTweetPost(TweetPost post) {
        tweetPosts.add(post);
    }
     
    public void saveTweetPostsToFile() {
        String fileName = projectDir + File.separator + currentUser + File.separator + "_tweetPosts.txt";
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileWriter writer = new FileWriter(fileName)) {
                for (TweetPost tweetPost : tweetPosts) {
                     writer.write(tweetPost.toString()+ "\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public List<TweetPost> loadTweetPostsByDateAndUser(Date startDate, Date endDate, String username) {
        List<TweetPost> filteredPosts = new ArrayList<>();
        for (TweetPost tweetPost : tweetPosts) {
            if (tweetPost.getUsername().equals(username) &&
                    tweetPost.getPostDate().compareTo(startDate) >= 0 &&
                    tweetPost.getPostDate().compareTo(endDate) <= 0) {
                filteredPosts.add(tweetPost);
            }
        }

        return filteredPosts;
    }
}
