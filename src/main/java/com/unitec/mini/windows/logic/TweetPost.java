/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author leonel
 */
public class TweetPost {
    private String username;
    private String content;
    private Date postDate;
    
    public TweetPost(String username, String content) {
        this.username = username;
        this.content = content;
        this.postDate = new Date();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(postDate);

        return String.format("<div class=\"twitter-widget\" data-username=\"%s\" data-date=\"%s\">%s</div>",
                username, formattedDate, content);
    }
}
