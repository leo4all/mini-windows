/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.unitec.mini.windows;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialOceanicContrastIJTheme;
import com.formdev.flatlaf.util.SystemInfo;
import com.unitec.mini.windows.logic.FolderStructureCreator;
import com.unitec.mini.windows.logic.User;
import com.unitec.mini.windows.logic.UserManager;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Taskbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.InternalFrameEvent;

public class LoginForm extends javax.swing.JFrame {
    private User userAuth;
    
    public LoginForm() {
        ImageIcon appIcon = new ImageIcon(getClass().getResource("/images/icons-ubuntu.png"));
        Image appImage = appIcon.getImage();
        setIconImage(appImage);

        try {
            if(Taskbar.isTaskbarSupported()){
                final Taskbar taskbar = Taskbar.getTaskbar();
                taskbar.setIconImage(appImage);
            }
        } catch (Exception e) {
        }
        
        this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", true);
        initComponents();
        setComponents();
        
        FolderStructureCreator.createDefaultFolderForAdmin();
        UserManager.createDefaultAdminUser();
        
        
    }
        
    public void setComponents(){ 
        updateTime(jLabel1);
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime(jLabel1);
            }
        });
        timer.start();
    }
    
    public void updateTime(JLabel label){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d   h:mm a");
        String currentTime = dateFormat.format(new Date());
        label.setText(currentTime);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Top = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton_Shutdown = new javax.swing.JButton();
        ImageIcon desktopIcon = new ImageIcon(getClass().getResource("/images/ubuntu-linux-software-software-wallpaper.jpg"));
        Image desktopImage = desktopIcon.getImage();
        jPanel_Main = new javax.swing.JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(desktopImage, 0, 0, null);
            }
        };
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextFieldUsername = new com.unitec.mini.windows.ui.JTextFieldUsername();
        jPasswordField = new com.unitec.mini.windows.ui.JPasswordFieldPass();
        jButton_Login = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel_Top.setBackground(new java.awt.Color(65, 25, 52));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(50, 15));

        jButton_Shutdown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons-power-off-button.png"))); // NOI18N
        jButton_Shutdown.setBorderPainted(false);
        jButton_Shutdown.setContentAreaFilled(false);
        jButton_Shutdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ShutdownActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_TopLayout = new javax.swing.GroupLayout(jPanel_Top);
        jPanel_Top.setLayout(jPanel_TopLayout);
        jPanel_TopLayout.setHorizontalGroup(
            jPanel_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_TopLayout.createSequentialGroup()
                .addContainerGap(598, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(534, 534, 534)
                .addComponent(jButton_Shutdown)
                .addContainerGap())
        );
        jPanel_TopLayout.setVerticalGroup(
            jPanel_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
            .addGroup(jPanel_TopLayout.createSequentialGroup()
                .addComponent(jButton_Shutdown)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel_Top, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1306, 40));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Kannada MN", 0, 36)); // NOI18N
        jLabel2.setText("WELCOME");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jSeparator1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N

        jTextFieldUsername.setText("admin");
        jTextFieldUsername.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons-locked-user.png"))); // NOI18N
        jTextFieldUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsernameActionPerformed(evt);
            }
        });

        jPasswordField.setText("admin");
        jPasswordField.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons-lock.png"))); // NOI18N

        jButton_Login.setFont(new java.awt.Font("Khmer Sangam MN", 1, 24)); // NOI18N
        jButton_Login.setText("LOGIN");
        jButton_Login.setAlignmentY(0.0F);
        jButton_Login.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Login.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(382, 382, 382)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(312, 312, 312)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_Login, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))))
                .addContainerGap(302, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jButton_Login)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_MainLayout = new javax.swing.GroupLayout(jPanel_Main);
        jPanel_Main.setLayout(jPanel_MainLayout);
        jPanel_MainLayout.setHorizontalGroup(
            jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_MainLayout.createSequentialGroup()
                .addContainerGap(194, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120))
        );
        jPanel_MainLayout.setVerticalGroup(
            jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MainLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(233, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel_Main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1310, 920));

        setSize(new java.awt.Dimension(1306, 955));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LoginActionPerformed
        String username = jTextFieldUsername.getText();
        String password = String.valueOf(jPasswordField.getPassword());
        UserManager.initialize();
        if (!UserManager.authenticateUser(username, password)) {
            JOptionPane.showMessageDialog(null, 
                "Please check your credentials", 
                "Error", 
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        userLoging=password;
        this.userAuth = UserManager.getUserByUsername(username);
        openAuthenticatedFrame();
        this.dispose();
    }//GEN-LAST:event_jButton_LoginActionPerformed
public static String userLoging;

    public static String getUserLoging() {
        return userLoging;
    }

    private void openAuthenticatedFrame() {
        SwingUtilities.invokeLater(() -> {
            Dashboard dashboard = new Dashboard(this.userAuth);
            dashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
            dashboard.setVisible(true);
        });
    }

    private void jButton_ShutdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ShutdownActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton_ShutdownActionPerformed

    private void jTextFieldUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsernameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>
        
        if( SystemInfo.isMacOS ) {
             // Trying to set Windows title menu on Mac OS
             // Set the application name for the Mac menubar
            System.setProperty("apple.laf.useScreenMenuBar", "true");

            System.setProperty("Xdock:name", "Mini Windows");
            System.setProperty("apple.awt.application.name", "Mini Windows");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Mini Windows");
            System.setProperty("Dcom.apple.mrj.application.apple.menu.about.name", "Mini Windows");
            System.setProperty( "apple.awt.application.appearance", "system" );

            if( SystemInfo.isMacFullWindowContentSupported ){
               //getRootPane().putClientProperty( "apple.awt.transparentTitleBar", true );
            }
        }
        
        try {
            UIManager.setLookAndFeel(new FlatMaterialOceanicContrastIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new LoginForm()).setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Login;
    private javax.swing.JButton jButton_Shutdown;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel_Main;
    private javax.swing.JPanel jPanel_Top;
    private com.unitec.mini.windows.ui.JPasswordFieldPass jPasswordField;
    private javax.swing.JSeparator jSeparator1;
    private com.unitec.mini.windows.ui.JTextFieldUsername jTextFieldUsername;
    // End of variables declaration//GEN-END:variables
}
