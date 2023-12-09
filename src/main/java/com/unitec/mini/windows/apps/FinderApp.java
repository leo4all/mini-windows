/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.unitec.mini.windows.apps;

import com.unitec.mini.windows.logic.FileSystemStructure;
import com.unitec.mini.windows.logic.User;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import com.unitec.mini.windows.logic.FolderStructureCreator;
import com.unitec.mini.windows.ui.FileButton;
import com.unitec.mini.windows.ui.FolderButton;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author leonel
 */
public class FinderApp extends JInternalFrame implements AppInterface {

    private int mouseX, mouseY;
    private User userAuthen;
    private FileSystemStructure.FileNode root = null;
    private DefaultTreeModel treeModel;
    private final String INITIAL_PATH = "Desktop";
    private String currentLocationPath = INITIAL_PATH;
    private final List<JButton> buttons;

    public FinderApp(User user) {
        this.userAuthen = user;
        buttons = new ArrayList<>();

        initComponents();
        setComponents();
    }

    public void setComponents() {
        ImageIcon appIcon = new ImageIcon(getClass().getResource("/images/icon_finder_20.png"));
        this.setFrameIcon(appIcon);

        try {

            DefaultMutableTreeNode folderNode = FileSystemStructure.getFolderTree(userAuthen.getUsername());
            jTree_Folder_Strcture.setModel(new DefaultTreeModel(folderNode));
            jTree_Folder_Strcture.setCellRenderer(getDirectoryCellRenderer());
            expandTree(jTree_Folder_Strcture);
            jTextField_Path.setText(Paths.get("Users", userAuthen.getUsername(), INITIAL_PATH).toString());
            jPanel_Finder_Dashboard.setLayout(new FlowLayout(FlowLayout.LEADING));
            refreshUI();

            jLabel_Current_Folder_Name.setText(currentLocationPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DefaultTreeCellRenderer getDirectoryCellRenderer() {
        return new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                Component c = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                c.setBackground(new Color(0, 0, 0, 0));

                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

                boolean isFolder = node.getAllowsChildren();
                Icon icon = isFolder ? getFileTypeIcon(node.toString(), isFolder) : getFileTypeIcon(node.toString(), isFolder);
                if (icon != null) {
                    setIcon(icon);
                }

                if (hasFocus) {
                    setBackground(new Color(0, 0, 0, 0));
                    setBorder(BorderFactory.createEmptyBorder());
                }

                return this;
            }
        };
    }

    private static Icon getFileTypeIcon(String fileName, boolean isFolder) {
        if (isFolder) {
            return UIManager.getIcon("FileView.directoryIcon");
        }

        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

        switch (extension) {
            case "txt":
                return UIManager.getIcon("FileView.fileIcon");
            case "jpg":
            case "jpeg":
            case "png":
            case "gif":
                return UIManager.getIcon("FileView.fileIcon");
            default:
                return UIManager.getIcon("FileView.fileIcon");
        }
    }

    private static void expandTree(JTree tree) {
        TreeNode root = (TreeNode) tree.getModel().getRoot();
        for (int i = 0; i < root.getChildCount(); i++) {
            TreeNode child = root.getChildAt(i);
            tree.expandPath(new TreePath(new Object[]{root, child}));
        }
    }

    public JPanel getElementsToPanel() {
        DefaultMutableTreeNode currentFilesInDestination = FileSystemStructure.getStructureForLocation(userAuthen.getUsername(),
                currentLocationPath
        );

        createPanelElements(currentFilesInDestination);

        JPanel panel = new JPanel();
        panel.setSize(100, 100);

        panel.removeAll();
        for (JButton button : buttons) {
            panel.add(button);
        }

        return panel;
    }

    private void createPanelElements(DefaultMutableTreeNode parentNode) {
        int count = parentNode.getChildCount();
        for (int i = 0; i < count; i++) {
            DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) parentNode.getChildAt(i);
            File file = (File) childNode.getUserObject();
            
            JButton button;
            if (file.isDirectory()) {
                button = new FolderButton(file);
            } else {
                button = new FileButton(file);
            }

            button.addMouseListener(new DoubleClickListener(button));
            buttons.add(button);
        }
    }

    class DoubleClickListener extends MouseAdapter {
        private int doubleClick = 2;
        private final JButton button;

        public DoubleClickListener(JButton button) {
            this.button = button;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == doubleClick) {
                if (button instanceof FolderButton) {
                    jPanel_Finder_Dashboard.removeAll();
                    moveToFolder(button);
                } else if (button instanceof FileButton) {
                    FileButton fileButton = (FileButton) button;
                    System.out.println("Double-clicked on FileButton with extension: " + fileButton.getFileExtension());
                    openAppBasedOnExtension(fileButton);
                }
            }
        }
    }
    
    private void moveToFolder(JButton btn){
        if (btn != null) {
            FolderButton bt = (FolderButton)btn;
            System.out.println(bt.getFolderName());
            System.out.println(bt.getFolderLocation());
            refreshUI();
        }
    }

    private void refreshUI(){
        
        jPanel_Finder_Dashboard.add(getElementsToPanel());
        jPanel_Finder_Dashboard.revalidate();
        jPanel_Finder_Dashboard.repaint();
    }
    
    private static void openAppBasedOnExtension(FileButton btn){
        System.out.println(btn.getName());
        System.out.println(btn.getFileLocation());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu_Finder_Options = new javax.swing.JPopupMenu();
        jMenuItem_new_folder = new javax.swing.JMenuItem();
        jPanel_Finder = new javax.swing.JPanel();
        jPanel_Top_Panel = new javax.swing.JPanel();
        jPanel_Top_bar = new javax.swing.JPanel();
        jPanel_Path_bar = new javax.swing.JPanel();
        jLabel_Current_Folder_Name = new javax.swing.JLabel();
        jButton_Go_To_Home_Folder = new javax.swing.JButton();
        jTextField_Path = new javax.swing.JTextField();
        jButton_New_Folder = new javax.swing.JButton();
        jButton_Go_Back = new javax.swing.JButton();
        jPanel_Main_Wrapper = new javax.swing.JPanel();
        jPanel_Sidebar = new javax.swing.JPanel();
        jScrollPane_Folder_Structure = new javax.swing.JScrollPane();
        jTree_Folder_Strcture = new javax.swing.JTree();
        jPanel_Finder_Dashboard = new javax.swing.JPanel();

        jMenuItem_new_folder.setText("New folder");
        jMenuItem_new_folder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_new_folderActionPerformed(evt);
            }
        });
        jPopupMenu_Finder_Options.add(jMenuItem_new_folder);

        setClosable(true);
        setIconifiable(true);
        setTitle("Finder");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel_Finder.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Finder.setFocusable(false);
        jPanel_Finder.setOpaque(false);
        jPanel_Finder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_FinderMousePressed(evt);
            }
        });

        jPanel_Top_bar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_Current_Folder_Name.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N

        jButton_Go_To_Home_Folder.setBackground(new Color(0, 0, 0, 0)
        );
        jButton_Go_To_Home_Folder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/finder/icon-home.png"))); // NOI18N
        jButton_Go_To_Home_Folder.setFocusable(false);
        jButton_Go_To_Home_Folder.setOpaque(false);
        jButton_Go_To_Home_Folder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Go_To_Home_FolderActionPerformed(evt);
            }
        });

        jTextField_Path.setEditable(false);
        jTextField_Path.setAutoscrolls(false);
        jTextField_Path.setFocusable(false);

        jButton_New_Folder.setBackground(new Color(0, 0, 0, 0)
        );
        jButton_New_Folder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/finder/icon-add-folder.png"))); // NOI18N
        jButton_New_Folder.setToolTipText("New Folder");
        jButton_New_Folder.setFocusable(false);
        jButton_New_Folder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_New_FolderActionPerformed(evt);
            }
        });

        jButton_Go_Back.setBackground(new Color(0, 0, 0, 0)
        );
        jButton_Go_Back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/finder/icon-go-back.png"))); // NOI18N
        jButton_Go_Back.setFocusable(false);
        jButton_Go_Back.setSize(new java.awt.Dimension(26, 26));
        jButton_Go_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Go_BackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Path_barLayout = new javax.swing.GroupLayout(jPanel_Path_bar);
        jPanel_Path_bar.setLayout(jPanel_Path_barLayout);
        jPanel_Path_barLayout.setHorizontalGroup(
            jPanel_Path_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Path_barLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel_Current_Folder_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Go_To_Home_Folder, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField_Path, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Go_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_New_Folder, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel_Path_barLayout.setVerticalGroup(
            jPanel_Path_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Path_barLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Path_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Current_Folder_Name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Go_To_Home_Folder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_Path, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_Go_Back)
                    .addComponent(jButton_New_Folder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        jLabel_Current_Folder_Name.getAccessibleContext().setAccessibleDescription("");

        jPanel_Top_bar.add(jPanel_Path_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 40));

        javax.swing.GroupLayout jPanel_Top_PanelLayout = new javax.swing.GroupLayout(jPanel_Top_Panel);
        jPanel_Top_Panel.setLayout(jPanel_Top_PanelLayout);
        jPanel_Top_PanelLayout.setHorizontalGroup(
            jPanel_Top_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Top_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Top_bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_Top_PanelLayout.setVerticalGroup(
            jPanel_Top_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Top_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Top_bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_Sidebar.setBackground(new Color(0, 0, 0, 0));

        jScrollPane_Folder_Structure.setBackground(new Color(0, 0, 0, 0)
        );
        jScrollPane_Folder_Structure.setBorder(null);

        jTree_Folder_Strcture.setBackground(new Color(0, 0, 0, 0));
        jTree_Folder_Strcture.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Places", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Helvetica Neue", 0, 14))); // NOI18N
        jTree_Folder_Strcture.setFocusable(false);
        jScrollPane_Folder_Structure.setViewportView(jTree_Folder_Strcture);

        javax.swing.GroupLayout jPanel_SidebarLayout = new javax.swing.GroupLayout(jPanel_Sidebar);
        jPanel_Sidebar.setLayout(jPanel_SidebarLayout);
        jPanel_SidebarLayout.setHorizontalGroup(
            jPanel_SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SidebarLayout.createSequentialGroup()
                .addComponent(jScrollPane_Folder_Structure, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_SidebarLayout.setVerticalGroup(
            jPanel_SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane_Folder_Structure, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_Finder_Dashboard.setBackground(new Color(0, 0, 0, 0));
        jPanel_Finder_Dashboard.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel_Finder_Dashboard.setFocusable(false);
        jPanel_Finder_Dashboard.setOpaque(false);
        jPanel_Finder_Dashboard.setPreferredSize(new java.awt.Dimension(800, 620));
        jPanel_Finder_Dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_Finder_DashboardMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Finder_DashboardLayout = new javax.swing.GroupLayout(jPanel_Finder_Dashboard);
        jPanel_Finder_Dashboard.setLayout(jPanel_Finder_DashboardLayout);
        jPanel_Finder_DashboardLayout.setHorizontalGroup(
            jPanel_Finder_DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 792, Short.MAX_VALUE)
        );
        jPanel_Finder_DashboardLayout.setVerticalGroup(
            jPanel_Finder_DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 618, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel_Main_WrapperLayout = new javax.swing.GroupLayout(jPanel_Main_Wrapper);
        jPanel_Main_Wrapper.setLayout(jPanel_Main_WrapperLayout);
        jPanel_Main_WrapperLayout.setHorizontalGroup(
            jPanel_Main_WrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Main_WrapperLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_Finder_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_Main_WrapperLayout.setVerticalGroup(
            jPanel_Main_WrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Main_WrapperLayout.createSequentialGroup()
                .addGroup(jPanel_Main_WrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Main_WrapperLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel_Sidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_Main_WrapperLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jPanel_Finder_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_FinderLayout = new javax.swing.GroupLayout(jPanel_Finder);
        jPanel_Finder.setLayout(jPanel_FinderLayout);
        jPanel_FinderLayout.setHorizontalGroup(
            jPanel_FinderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FinderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_FinderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_Top_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_Main_Wrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_FinderLayout.setVerticalGroup(
            jPanel_FinderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FinderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Top_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Main_Wrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel_Finder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel_Finder_DashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_Finder_DashboardMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            mouseX = evt.getX();
            mouseY = evt.getY();
            jPopupMenu_Finder_Options.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jPanel_Finder_DashboardMouseClicked

    private void jPanel_FinderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_FinderMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_jPanel_FinderMousePressed

    private void jMenuItem_new_folderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_new_folderActionPerformed
        String folderName = JOptionPane.showInputDialog(null, "Folder name");
        /*String folderName = input.getText().trim();
        if (folderName != null && !folderName.isEmpty()) {

            // position is irrelevant in this case find other way.
            int x, y;
            x = y = 0;

            Object button = FolderStructureCreator.createButtonFolderAtPosition(
                    userAuthen.getUsername(),
                    defaultFoldertPath,
                    folderName,
                    x,
                    y
            );

            if (button != null) {

            } else {
                JOptionPane.showMessageDialog(this,
                        "An error occur creating a folder.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }*/

    }//GEN-LAST:event_jMenuItem_new_folderActionPerformed

    private void jButton_New_FolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_New_FolderActionPerformed
        try {

            JPanel panel = new JPanel();
            panel.add(new JLabel("Folder name"));
            JTextField input = new JTextField(10);
            panel.add(input);

            int result = JOptionPane.showOptionDialog(null,
                    panel,
                    "New Folder",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new String[]{"Create", "Cancel"},
                    null
            );

            if (result == JOptionPane.OK_OPTION) {
                String folderName = input.getText().trim();
                if (folderName != null && !folderName.isEmpty()) {
                    int x, y;
                    x = y = 0;

                    Object button = FolderStructureCreator.createButtonFolderAtPosition(userAuthen.getUsername(),
                            currentLocationPath,
                            folderName,
                            x,
                            y
                    );

                    if (button == null) {
                        JOptionPane.showMessageDialog(this,
                                "An error occur creating a folder.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                           
                    } 

                    refreshUI();
                }
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton_New_FolderActionPerformed

    private void jButton_Go_To_Home_FolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Go_To_Home_FolderActionPerformed
        currentLocationPath = INITIAL_PATH;
        refreshUI();
    }//GEN-LAST:event_jButton_Go_To_Home_FolderActionPerformed

    private void jButton_Go_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Go_BackActionPerformed
        
    }//GEN-LAST:event_jButton_Go_BackActionPerformed

    @Override
    public void closeFrame() {
        try {
            this.setClosed(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Go_Back;
    private javax.swing.JButton jButton_Go_To_Home_Folder;
    private javax.swing.JButton jButton_New_Folder;
    private javax.swing.JLabel jLabel_Current_Folder_Name;
    private javax.swing.JMenuItem jMenuItem_new_folder;
    private javax.swing.JPanel jPanel_Finder;
    private javax.swing.JPanel jPanel_Finder_Dashboard;
    private javax.swing.JPanel jPanel_Main_Wrapper;
    private javax.swing.JPanel jPanel_Path_bar;
    private javax.swing.JPanel jPanel_Sidebar;
    private javax.swing.JPanel jPanel_Top_Panel;
    private javax.swing.JPanel jPanel_Top_bar;
    private javax.swing.JPopupMenu jPopupMenu_Finder_Options;
    private javax.swing.JScrollPane jScrollPane_Folder_Structure;
    private javax.swing.JTextField jTextField_Path;
    private javax.swing.JTree jTree_Folder_Strcture;
    // End of variables declaration//GEN-END:variables
}
