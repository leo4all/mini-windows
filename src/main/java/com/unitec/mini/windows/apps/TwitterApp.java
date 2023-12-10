/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.unitec.mini.windows.apps;

import com.unitec.mini.windows.logic.TweetManager;
import com.unitec.mini.windows.logic.TweetPost;
import com.unitec.mini.windows.logic.TwitterAccount;
import com.unitec.mini.windows.logic.User;
import com.unitec.mini.windows.ui.TimeLineEditorKit;
import java.awt.Image;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author leonel
 */
public class TwitterApp extends JInternalFrame implements AppInterface {
    private TwitterAccount tweetAccount;
    private TweetManager tweetManager;
    private User userAuthen;
    private Image backgroundImage;
    List<TweetPost> loadedPosts;
    
    /**
     * Creates new form Twitter
     */
    public TwitterApp(User userAuth, TwitterAccount account) {
        this.tweetAccount = account;
        this.userAuthen = userAuth;

        initComponents();
        setComponents();
        setVisible(true);
    }

    public void setComponents() {
        ImageIcon appIcon = new ImageIcon(getClass().getResource("/images/icon_twitter_20.png"));
        this.setFrameIcon(appIcon);
        
        ImageIcon backgroundImageIcon = new ImageIcon(getClass().getResource("/images/twitter-wallpaper.jpg"));
        backgroundImage = backgroundImageIcon.getImage();
        
        textPane.setContentType("text/html");
        timeLinePane.setContentType("text/html");
        timeLinePane.setEditorKit(new TimeLineEditorKit());
        timeLinePane.addHyperlinkListener(new TimelineHyperlinkListener());
        timeLinePane.setText("");

        addInternalFrameListener(new TwitterInternalFrameListener());
        tweetManager = new TweetManager(userAuthen, tweetAccount);
        
        SwingWorker<List<TweetPost>, Void> worker = new SwingWorker<>() {
                @Override
                protected List<TweetPost> doInBackground() {
                    try {
                        Date startDate = new Date(122, 0, 1);
                        Date endDate = new Date();
                        return tweetManager.loadTweetPostsByDateAndUser(startDate, endDate, null);
                    } catch (Exception ex) {
                        ex.printStackTrace(); 
                        return null;
                    }
                }

                @Override
                protected void done() {
                    try {
                        List<TweetPost> tweets = get();
                        if (tweets != null) {
                             StringBuilder sb = new StringBuilder();
                            for (TweetPost tweet : tweets) {
                                sb.append(tweet.getContent()).append("\n");
                            }
                            timeLinePane.setText(sb.toString());
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            };

           worker.execute();
    }
    
    public void setAuthenticatedUser(User user) {
        this.userAuthen = user;
    }
    
    public User getAuthenticatedUser(){
        if (this.userAuthen != null) {
            return this.userAuthen;
        }

        return null;
    }
    
    public void setAccountAuth(TwitterAccount loggedAccount) {
        this.tweetAccount = loggedAccount;
    }
    
    private TwitterAccount getTwitterAccount(){
        return tweetAccount;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane_timeLine = new javax.swing.JScrollPane();
        timeLinePane = new javax.swing.JTextPane();
        jPanel_AddTweet = new javax.swing.JPanel();
        jButton_OpenAddImage = new javax.swing.JButton();
        jButton_SubmitPost = new javax.swing.JButton();
        jButton_OPenAddEmoji = new javax.swing.JButton();
        jScrollPane_AddTweet = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();

        setClosable(true);
        setIconifiable(true);
        setTitle("Twitter");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setLayout(new java.awt.CardLayout());

        jScrollPane_timeLine.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        timeLinePane.setEditable(false);
        timeLinePane.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane_timeLine.setViewportView(timeLinePane);

        jButton_OpenAddImage.setText("I");
        jButton_OpenAddImage.setToolTipText("Media");
        jButton_OpenAddImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_OpenAddImageActionPerformed(evt);
            }
        });

        jButton_SubmitPost.setText("Post");
        jButton_SubmitPost.setEnabled(false);
        jButton_SubmitPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SubmitPostActionPerformed(evt);
            }
        });

        jButton_OPenAddEmoji.setText("E");
        jButton_OPenAddEmoji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_OPenAddEmojiActionPerformed(evt);
            }
        });

        textPane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textPaneKeyPressed(evt);
            }
        });
        jScrollPane_AddTweet.setViewportView(textPane);

        javax.swing.GroupLayout jPanel_AddTweetLayout = new javax.swing.GroupLayout(jPanel_AddTweet);
        jPanel_AddTweet.setLayout(jPanel_AddTweetLayout);
        jPanel_AddTweetLayout.setHorizontalGroup(
            jPanel_AddTweetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AddTweetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_AddTweetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_AddTweetLayout.createSequentialGroup()
                        .addComponent(jScrollPane_AddTweet, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_SubmitPost))
                    .addGroup(jPanel_AddTweetLayout.createSequentialGroup()
                        .addComponent(jButton_OpenAddImage)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_OPenAddEmoji)))
                .addContainerGap())
        );
        jPanel_AddTweetLayout.setVerticalGroup(
            jPanel_AddTweetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AddTweetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_AddTweetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_AddTweetLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jButton_SubmitPost)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_AddTweetLayout.createSequentialGroup()
                        .addComponent(jScrollPane_AddTweet, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel_AddTweetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_OpenAddImage)
                    .addComponent(jButton_OPenAddEmoji))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel_AddTweet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane_timeLine, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_AddTweet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane_timeLine, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane_timeLine.getAccessibleContext().setAccessibleName("");

        jPanel3.add(jPanel1, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_OpenAddImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_OpenAddImageActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File imageFile = fileChooser.getSelectedFile();
            displayImage(imageFile);
        }
    }//GEN-LAST:event_jButton_OpenAddImageActionPerformed

    private void jButton_SubmitPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SubmitPostActionPerformed

        String content = processTextPaneContent(parseContent(textPane.getText()));
        TweetPost post = new TweetPost(tweetAccount.getUsername(), content);
        String textStringFormatted = post.toString();

        String timeStringProcessed = processTextPaneContent(parseContent(timeLinePane.getText()));
        if (!timeStringProcessed.isEmpty()) {
            textStringFormatted += timeStringProcessed;
        }

        timeLinePane.setContentType("text/html");
        timeLinePane.setText(textStringFormatted);
        textPane.setText("");

        jScrollPane_timeLine.getVerticalScrollBar().setValue(0);
        timeLinePane.revalidate();
        timeLinePane.repaint();
        tweetManager.addTweetPost(post);
        jButton_SubmitPost.setEnabled(false);
    }//GEN-LAST:event_jButton_SubmitPostActionPerformed

    private void textPaneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textPaneKeyPressed
        jButton_SubmitPost.setEnabled(true);
    }//GEN-LAST:event_textPaneKeyPressed

    private void jButton_OPenAddEmojiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_OPenAddEmojiActionPerformed
        String[] emojis = {"😊", "❤️", "🎉", "🌟", "👍"};
        JList<String> emojiList = new JList<>(emojis);

        emojiList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedEmoji = emojiList.getSelectedValue();
            }
        });

        JScrollPane scrollPane = new JScrollPane(emojiList);
    }//GEN-LAST:event_jButton_OPenAddEmojiActionPerformed

    public static String processTextPaneContent(String html) {
        Document doc = Jsoup.parse(html);
        Element body = doc.body();
        Elements emptyPTags = body.select("p:empty");
        emptyPTags.remove();

        String processedContent = "";
        for (Element child : body.children()) {
            if (!child.tagName().equalsIgnoreCase("p") || !child.text().trim().isEmpty()) {
                processedContent += child.outerHtml();
            }
        }

        return processedContent;
    }

    private String parseContent(String content) {
        content = content.replaceAll("#(\\w+)", "<a href=\"hashtag\">$1</a>");
        return content.replaceAll("@(\\w+)", "<a href=\"mention\">$1</a>");
    }

    private void handleLinkClick(String link) {
        if ("hashtag".equals(link)) {
            String hashtag = extractHashtag(link);
            
        }

        if ("mention".equals(link)) {
            String mention = extractMention(link);
            
        }
    }

    private String extractHashtag(String link) {
        String regex = "<a\\s+href=\"hashtag\">(.*?)</a>";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(link);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return link;
        }
    }

    private String extractMention(String link) {
        String regex = "<a\\s+href=\"mention\">(.*?)</a>";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(link);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return link;
        }
    }

    private void displayImage(File file) {
        if (file != null) {
            String imagePath = file.getAbsolutePath();
            String currentContent = textPane.getText();
            String updatedContent = updateHtmlWithImage(currentContent, imagePath);
            textPane.setText(updatedContent);
        }
    }

    private String updateHtmlWithImage(String currentHtml, String imagePath) {
        currentHtml = currentHtml.replaceAll("<img[^>]*>", "");
        return currentHtml.replace("</body>", String.format("<br/><img src='file:%s' width='100' height='100'/></body>", imagePath));
    }

    @Override
    public void closeFrame() {
        try {
            this.setClosed(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class TwitterInternalFrameListener extends InternalFrameAdapter {
        @Override
        public void internalFrameOpened(InternalFrameEvent e) {
            try {
                tweetManager.saveTweets(title);
            } catch (Exception exception) {
                System.out.println("Error saving tweet file.");
            }

            super.internalFrameClosing(e);
        }

        @Override
        public void internalFrameClosed(InternalFrameEvent e) {
        
        
        }
    }
    
    class TimelineHyperlinkListener implements HyperlinkListener {
        @Override
        public void hyperlinkUpdate(HyperlinkEvent e) {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                handleLinkClick(e.getDescription());
            }
        }

        private void handleLinkClick(String description) {
            System.out.println("Link clicked: " + description);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_OPenAddEmoji;
    private javax.swing.JButton jButton_OpenAddImage;
    private javax.swing.JButton jButton_SubmitPost;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel_AddTweet;
    private javax.swing.JScrollPane jScrollPane_AddTweet;
    private javax.swing.JScrollPane jScrollPane_timeLine;
    private javax.swing.JTextPane textPane;
    private javax.swing.JTextPane timeLinePane;
    // End of variables declaration//GEN-END:variables
}
