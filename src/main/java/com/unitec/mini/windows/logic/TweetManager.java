/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author leonel
 */
public class TweetManager {

    private List<TweetPost> tweetPosts;
    private static final String projectDir = System.getProperty("user.dir") + "/src/main/tweets";
    private String currentUser;
    private String username;
    private static final String DEFAULT_FILE = "tweets.twc";

    private static final Pattern USERNAME_PATTERN = Pattern.compile("data-username=\"([^\"]+)\"");
    private static final Pattern DATE_PATTERN = Pattern.compile("data-date=\"([^\"]+)\"");
    private static final Pattern CONTENT_PATTERN = Pattern.compile("<div class=\"twitter-widget\".*?>(.*?)</div>");

    public TweetManager(User userAuthen, TwitterAccount account) {
        tweetPosts = new ArrayList<>();
        currentUser = userAuthen.getUsername();
        username = account.getUsername();
    }

    public void addTweetPost(TweetPost post) {
        tweetPosts.add(post);
    }

    public void saveTweets(String username) {
        String fileName = Paths.get(projectDir, username, DEFAULT_FILE).toString();

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileWriter writer = new FileWriter(fileName)) {
                for (TweetPost tweetPost : tweetPosts) {
                    writer.write(tweetPost.toString() + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<TweetPost> loadTweetPostsByDateAndUser(Date startDate, Date endDate, String requestedAccount) {
        if (requestedAccount == null && this.username == null) {
            return null;
        }

        String fileName = Paths.get(projectDir, username, DEFAULT_FILE).toString();
        
        System.out.println(fileName);
        /*try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                TweetPost tweetPost = parseTweetPostFromHtml(line);
                if (tweetPost != null && tweetPost.getUsername().equals(username)) {
                    //tweetPosts.add(tweetPost);
                }
            }
        } catch (Exception e) {
            System.out.println("Error creating file");
            e.printStackTrace();
        }*/

        return tweetPosts;
    }
    
    
    public static void createDefaulTweetFolder(String username){
        String location = Paths.get(projectDir, username).toString();
        try {
            FolderStructureCreator.createFolder(location);
            FolderStructureCreator.createFile(location, "tweets.twc");
        } catch (Exception e) {
            System.out.println("Error creating twitter folder account.");
        }
    }

    private TweetPost parseTweetPostFromHtml(String html) {
        Matcher usernameMatcher = USERNAME_PATTERN.matcher(html);
        Matcher dateMatcher = DATE_PATTERN.matcher(html);
        Matcher contentMatcher = CONTENT_PATTERN.matcher(html);

        if (usernameMatcher.find() && dateMatcher.find() && contentMatcher.find()) {
            String username = usernameMatcher.group(1);
            String dateString = dateMatcher.group(1);
            String content = contentMatcher.group(1);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date postDate = dateFormat.parse(dateString);
                TweetPost post = new TweetPost(username, content);
                post.setPostDate(postDate);
                return post;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
