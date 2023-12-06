package com.unitec.mini.windows.component;

import com.unitec.mini.windows.LoginTwitter;
import com.unitec.mini.windows.logic.TwitterAccount;
import com.unitec.mini.windows.logic.TwitterUserManager;
import com.unitec.mini.windows.ui.Button;
import com.unitec.mini.windows.ui.TwitterComboBox;
import com.unitec.mini.windows.ui.TwitterPasswordField;
import com.unitec.mini.windows.ui.TwitterTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    LoginTwitter loginForm;

    public PanelLoginAndRegister(LoginTwitter loginForm) {
        this.loginForm = loginForm;

        initComponents();
        initRegister();
        initLogin();
    }
private void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]20[]15[]push"));
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(173, 126, 151));
        //label.setForeground(new Color(7, 164, 121));
        register.add(label);

        TwitterTextField txtUser = new TwitterTextField();
        ImageIcon txtUserIcon = new ImageIcon(getClass().getResource("/images/user.png"));
        txtUser.setPrefixIcon(txtUserIcon);
        txtUser.setHint("Name");
        register.add(txtUser, "w 60%");

        TwitterTextField txtUsername = new TwitterTextField();
        ImageIcon txtEmailIcon = new ImageIcon(getClass().getResource("/images/icons-twitter-20.png"));
        txtUsername.setPrefixIcon(txtEmailIcon);
        txtUsername.setHint("Username");
        register.add(txtUsername, "w 60%");

        TwitterPasswordField txtPass = new TwitterPasswordField();
        ImageIcon txtPassIcon = new ImageIcon(getClass().getResource("/images/pass.png"));
        txtPass.setPrefixIcon(txtPassIcon);
        txtPass.setHint("Password");
        register.add(txtPass, "w 60%");

        TwitterComboBox jComboBox1 = new TwitterComboBox();
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Male", "Female"}));
        register.add(jComboBox1, "w 60%");

        TwitterTextField txtAge = new TwitterTextField();
        ImageIcon txtAgeIcon = new ImageIcon(getClass().getResource("/images/user.png"));
        txtAge.setPrefixIcon(txtAgeIcon);
        txtAge.setHint("Age");
        register.add(txtAge, "w 20%");

        
        Button cmd = new Button();
        //cmd.setBackground(new Color(7, 164, 121));
        cmd.setBackground(new Color(173, 126, 151));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN UP");
        register.add(cmd, "w 40%, h 40");

        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String name = txtUser.getText().trim();
                String username = txtUsername.getText().trim();
                String password = String.valueOf(txtPass.getPassword());
                String gender = String.valueOf(jComboBox1.getSelectedItem());

                TwitterAccount newUser = new TwitterAccount(name, username,
                        password,
                        'c',
                        2,
                        1,
                        new File(""),
                        ""
                );

                JOptionPane.showMessageDialog(null, "Twitter accoun registered, please login.");
            }
        });
        //
        JLabel profileImageLabel = new JLabel("Select Profile Image");
    register.add(profileImageLabel);

    JButton chooseImageButton = new JButton("Choose Image");
    register.add(chooseImageButton);

    chooseImageButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Image files", "jpg", "jpeg", "png", "gif");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                // Redimensionar la imagen antes de mostrarla
                ImageIcon selectedImageIcon = resizeImage(selectedFile.getAbsolutePath(), 100, 100);
                profileImageLabel.setIcon(selectedImageIcon);
            }
        }
    });

    }
    private ImageIcon resizeImage(String imagePath, int width, int height) {
    ImageIcon originalIcon = new ImageIcon(imagePath);
    Image originalImage = originalIcon.getImage();
    Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(resizedImage);
}

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));

        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(173, 126, 151));
        //label.setForeground(new Color(7, 164, 121));

        login.add(label);

        TwitterTextField txtEmail = new TwitterTextField();
        ImageIcon txtEmailIcon = new ImageIcon(getClass().getResource("/images/icons-twitter-20.png"));
        txtEmail.setPrefixIcon(txtEmailIcon);
        txtEmail.setHint("Username");
        login.add(txtEmail, "w 60%");

        TwitterPasswordField txtPass = new TwitterPasswordField();
        ImageIcon txtPassIcon = new ImageIcon(getClass().getResource("/images/pass.png"));
        txtPass.setPrefixIcon(txtPassIcon);
        txtPass.setHint("Password");
        login.add(txtPass, "w 60%");

        Button cmd = new Button();
        cmd.setBackground(new Color(173, 126, 151));
        //cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN IN");
        login.add(cmd, "w 40%, h 40");

        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String email = txtEmail.getText().trim();
                String password = String.valueOf(txtPass.getPassword());
                System.out.println(email + " " + password);
                boolean isAuthen = TwitterUserManager.authenticateUser(email, password);
                if (isAuthen) {
                    loginForm.getParentPanelDialog().dispose();
                    loginForm.getDashboard().showTwitterApp();
                }
            }
        });
    }

    public void toggleVisibility() {
        setVisible(!isVisible());
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
