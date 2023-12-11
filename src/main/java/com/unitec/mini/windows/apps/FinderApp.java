/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.unitec.mini.windows.apps;

import com.unitec.mini.windows.Dashboard;
import com.unitec.mini.windows.LoginForm;
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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
/**
 *
 * @author leonel
 */
public class FinderApp extends JInternalFrame implements AppInterface {
PaintApp paint;
PlayerApp music;
EditorApp word;
    private int mouseX, mouseY;
    private User userAuthen;
    private FileSystemStructure.FileNode root = null;
    private DefaultTreeModel treeModel;
    private final String INITIAL_PATH = "Desktop";
    private String currentLocationPath = INITIAL_PATH;
    private final List<JButton> buttons;
    DefaultMutableTreeNode folderNode;
    
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
            folderNode = FileSystemStructure.getFolderTree(userAuthen.getUsername());
            jTree_Folder_Structure.setModel(new DefaultTreeModel(folderNode));
            jTree_Folder_Structure.setCellRenderer(getDirectoryCellRenderer());
            jTree_Folder_Structure.addTreeSelectionListener(new SelectorListener());
            expandRootTree(jTree_Folder_Structure);
            jTextField_Path.setText(Paths.get("Users", userAuthen.getUsername(), INITIAL_PATH).toString());
            jPanel_Finder_Dashboard.setLayout(new FlowLayout(FlowLayout.LEADING));
            refreshUI();
            jLabel_Current_Folder_Name.setText(currentLocationPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private DefaultTreeCellRenderer getDirectoryCellRenderer() {
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
//    private class SelectorListener  implements TreeSelectionListener {
//        public void valueChanged(TreeSelectionEvent evt) {
//            JTree tree = (JTree) evt.getSource();
//            DefaultMutableTreeNode selectedNode; 
//            selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
//            if (selectedNode !=null && selectedNode.isLeaf()) {
//               String selectedNodeName = selectedNode.toString();
//                System.out.println(selectedNodeName);
//                
//                
//                
//                //Object obj = evt.getNewLeadSelectionPath().getLastPathComponent();
//                //System.out.println("" + obj.toString().length()   
//            }
//            jTree_Folder_Structure.setBackground(new Color(0, 0, 0, 0));
//            jTree_Folder_Structure.setFocusable(false);
//            jTree_Folder_Structure.setOpaque(false);
//        }
//    }
    private class SelectorListener implements TreeSelectionListener {
    public void valueChanged(TreeSelectionEvent evt) {
        JTree tree = (JTree) evt.getSource();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        if (selectedNode != null && selectedNode.isLeaf()) {
            String selectedNodeName = selectedNode.toString();
            String fullPath = currentLocationPath + File.separator + selectedNodeName;
            File selectedFile = new File(fullPath);

            String fileExtension = getFileExtension(selectedFile);

            if ("txt".equalsIgnoreCase(fileExtension)) {
               sendtxt("src/main/users/" + LoginForm.getUserLoging() + "/Documents/" + selectedNodeName);
            } else if ("png".equalsIgnoreCase(fileExtension)) {
             sendImage("src/main/users/" + LoginForm.getUserLoging() + "/Images/" + selectedNodeName);
            } else if ("mp3".equalsIgnoreCase(fileExtension)) {
             sendMusic("src/main/users/" + LoginForm.getUserLoging() + "/Music/" + selectedNodeName,selectedNodeName);
            } else {
            }
        }

        jTree_Folder_Structure.setBackground(new Color(0, 0, 0, 0));
        jTree_Folder_Structure.setFocusable(false);
        jTree_Folder_Structure.setOpaque(false);
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1).toLowerCase();
    }
}



    private static void expandRootTree(JTree tree) {
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
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree_Folder_Structure.getLastSelectedPathComponent();
                    //if (selectedNode != null) {
                         //updateFinderDashboard(selectedNode)
                    //moveToFolder(button);                   
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
     private void updateFinderDashboard(DefaultMutableTreeNode selectedNode) {
        jPanel_Finder_Dashboard.removeAll();
        JLabel label = new JLabel("Selected Node: " + selectedNode.getUserObject().toString());
        jPanel_Finder_Dashboard.add(label);
        jPanel_Finder_Dashboard.revalidate();
        jPanel_Finder_Dashboard.repaint();
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu_JTreeMenuFolderOptions = new javax.swing.JPopupMenu();
        jMenu_New = new javax.swing.JMenu();
        jMenuItem_JTree_Create_Folder = new javax.swing.JMenuItem();
        jMenuItem_JTree_Create_File = new javax.swing.JMenuItem();
        jSeparator_1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_JTree_Cut = new javax.swing.JMenuItem();
        jMenuItem_JTree_Copy = new javax.swing.JMenuItem();
        jMenuItem_JTree_Paste = new javax.swing.JMenuItem();
        jSeparator_2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_JTree_Delete = new javax.swing.JMenuItem();
        jPopupMenu_JTreeMenuFileOptions = new javax.swing.JPopupMenu();
        jMenuItem_JTree_File_Cut = new javax.swing.JMenuItem();
        jMenuItem_JTree_File_Copy = new javax.swing.JMenuItem();
        jMenuItem_JTree_File_Paste = new javax.swing.JMenuItem();
        jSeparator_4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_JTree_File_Delete = new javax.swing.JMenuItem();
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
        jTree_Folder_Structure = new javax.swing.JTree();
        jPanel_Finder_Dashboard = new javax.swing.JPanel();

        jMenu_New.setText("New");
        jMenu_New.setToolTipText("");

        jMenuItem_JTree_Create_Folder.setText("Folder");
        jMenuItem_JTree_Create_Folder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_JTree_Create_FolderActionPerformed(evt);
            }
        });
        jMenu_New.add(jMenuItem_JTree_Create_Folder);

        jMenuItem_JTree_Create_File.setText("File");
        jMenuItem_JTree_Create_File.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_JTree_Create_FileActionPerformed(evt);
            }
        });
        jMenu_New.add(jMenuItem_JTree_Create_File);

        jPopupMenu_JTreeMenuFolderOptions.add(jMenu_New);
        jPopupMenu_JTreeMenuFolderOptions.add(jSeparator_1);

        jMenuItem_JTree_Cut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_JTree_Cut.setText("Cut");
        jPopupMenu_JTreeMenuFolderOptions.add(jMenuItem_JTree_Cut);

        jMenuItem_JTree_Copy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_JTree_Copy.setText("Copy");
        jPopupMenu_JTreeMenuFolderOptions.add(jMenuItem_JTree_Copy);

        jMenuItem_JTree_Paste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_JTree_Paste.setText("Paste");
        jPopupMenu_JTreeMenuFolderOptions.add(jMenuItem_JTree_Paste);
        jPopupMenu_JTreeMenuFolderOptions.add(jSeparator_2);

        jMenuItem_JTree_Delete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_JTree_Delete.setText("Delete");
        jPopupMenu_JTreeMenuFolderOptions.add(jMenuItem_JTree_Delete);

        jMenuItem_JTree_File_Cut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_JTree_File_Cut.setText("Cut");
        jPopupMenu_JTreeMenuFileOptions.add(jMenuItem_JTree_File_Cut);

        jMenuItem_JTree_File_Copy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_JTree_File_Copy.setText("Copy");
        jPopupMenu_JTreeMenuFileOptions.add(jMenuItem_JTree_File_Copy);

        jMenuItem_JTree_File_Paste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_JTree_File_Paste.setText("Paste");
        jPopupMenu_JTreeMenuFileOptions.add(jMenuItem_JTree_File_Paste);
        jPopupMenu_JTreeMenuFileOptions.add(jSeparator_4);

        jMenuItem_JTree_File_Delete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_JTree_File_Delete.setText("Delete");
        jPopupMenu_JTreeMenuFileOptions.add(jMenuItem_JTree_File_Delete);

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
        jScrollPane_Folder_Structure.setFocusable(false);
        jScrollPane_Folder_Structure.setOpaque(false);

        jTree_Folder_Structure.setBackground(new Color(0, 0, 0, 0));
        jTree_Folder_Structure.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Places", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Helvetica Neue", 0, 14))); // NOI18N
        jTree_Folder_Structure.setFocusable(false);
        jTree_Folder_Structure.setOpaque(false);
        jTree_Folder_Structure.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTree_Folder_StructureMousePressed(evt);
            }
        });
        jScrollPane_Folder_Structure.setViewportView(jTree_Folder_Structure);

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
            .addGap(0, 766, Short.MAX_VALUE)
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
                .addComponent(jPanel_Finder_Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
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
            //jPopupMenu_Finder_Options.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jPanel_Finder_DashboardMouseClicked

    private void jPanel_FinderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_FinderMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_jPanel_FinderMousePressed

    private void jButton_New_FolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_New_FolderActionPerformed
        try {
            JPanel panel = new JPanel();
            panel.add(new JLabel("Folder name"));
            JTextField input = new JTextField(20);
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
            if (result != JOptionPane.OK_OPTION) {
                return;
            }
            String folderName = input.getText().trim();
            if (folderName != null && !folderName.isEmpty()) {
                int x, y; x = y = 0;
                Object button = FolderStructureCreator.createButtonFolderAtPosition(userAuthen.getUsername(),
                        currentLocationPath,
                        folderName,
                        x,
                        y
                );
                if (button != null) {
                    updateJTreeFolderStructure();
                    return;   
                } 
                JOptionPane.showMessageDialog(
                        this,
                            "An error occur creating a folder.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton_New_FolderActionPerformed

    private void updateJTreeFolderStructure(){
        folderNode = FileSystemStructure.getFolderTree(userAuthen.getUsername());
        jTree_Folder_Structure.setModel(new DefaultTreeModel(folderNode));
        jTree_Folder_Structure.removeAll();
        jTree_Folder_Structure.revalidate();
        jTree_Folder_Structure.repaint();
        jTree_Folder_Structure.setBackground(new Color(0, 0, 0, 0));
        jTree_Folder_Structure.setFocusable(false);
        jTree_Folder_Structure.setOpaque(false);
        jTree_Folder_Structure.setCellRenderer(getDirectoryCellRenderer());
        expandRootTree(jTree_Folder_Structure);
    }
    
    private void jButton_Go_To_Home_FolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Go_To_Home_FolderActionPerformed
        currentLocationPath = INITIAL_PATH;
        refreshUI();
    }//GEN-LAST:event_jButton_Go_To_Home_FolderActionPerformed

    private void jButton_Go_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Go_BackActionPerformed
        
    }//GEN-LAST:event_jButton_Go_BackActionPerformed

    private void jTree_Folder_StructureMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree_Folder_StructureMousePressed
        if (evt.isPopupTrigger()) {
            jPopupMenu_JTreeMenuFolderOptions.show(this, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jTree_Folder_StructureMousePressed

    private void jMenuItem_JTree_Create_FolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_JTree_Create_FolderActionPerformed
        JPanel panel = new JPanel();
            panel.add(new JLabel("Folder name"));
            JTextField input = new JTextField(20);
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
            if (result != JOptionPane.OK_OPTION) {
                return;
            }
    }//GEN-LAST:event_jMenuItem_JTree_Create_FolderActionPerformed

    private void jMenuItem_JTree_Create_FileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_JTree_Create_FileActionPerformed
        JPanel panel = new JPanel();
            panel.add(new JLabel("File name"));
            JTextField input = new JTextField(20);
            panel.add(input);
            int result = JOptionPane.showOptionDialog(null,
                    panel,
                    "New File",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new String[]{"Create", "Cancel"},
                    null
            );
            if (result != JOptionPane.OK_OPTION) {
                return;
            }
    }//GEN-LAST:event_jMenuItem_JTree_Create_FileActionPerformed
    private void setInternalFrameCenterLocation(JInternalFrame frame){
        Dimension desktopSize = jPanel_Finder_Dashboard.getSize();
        Dimension jISize = frame.getSize();
        frame.setLocation((desktopSize.width - jISize.width)/2,(desktopSize.height- jISize.height)/2);
    }
     public static ImageIcon loadImageIcon(String imagePath) {
        return new ImageIcon(imagePath);
    }
    
    public void sendImage(String Path){
        ImageIcon imagen =loadImageIcon(Path);
    paint= new PaintApp(true,imagen);
    setInternalFrameCenterLocation(paint);
    Dashboard.jDesktopPane_Window.add(paint).setVisible(true);
    }
    public void sendtxt(String Path){
    word= new EditorApp(true,Path);
    setInternalFrameCenterLocation(word);
    Dashboard.jDesktopPane_Window.add(word).setVisible(true);
    }
    public void sendMusic(String Path,String name){
    music = new PlayerApp(true,Path,name);
    setInternalFrameCenterLocation(music);
    Dashboard.jDesktopPane_Window.add(music).setVisible(true);
    }
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
    private javax.swing.JMenuItem jMenuItem_JTree_Copy;
    private javax.swing.JMenuItem jMenuItem_JTree_Create_File;
    private javax.swing.JMenuItem jMenuItem_JTree_Create_Folder;
    private javax.swing.JMenuItem jMenuItem_JTree_Cut;
    private javax.swing.JMenuItem jMenuItem_JTree_Delete;
    private javax.swing.JMenuItem jMenuItem_JTree_File_Copy;
    private javax.swing.JMenuItem jMenuItem_JTree_File_Cut;
    private javax.swing.JMenuItem jMenuItem_JTree_File_Delete;
    private javax.swing.JMenuItem jMenuItem_JTree_File_Paste;
    private javax.swing.JMenuItem jMenuItem_JTree_Paste;
    private javax.swing.JMenu jMenu_New;
    private javax.swing.JPanel jPanel_Finder;
    private javax.swing.JPanel jPanel_Finder_Dashboard;
    private javax.swing.JPanel jPanel_Main_Wrapper;
    private javax.swing.JPanel jPanel_Path_bar;
    private javax.swing.JPanel jPanel_Sidebar;
    private javax.swing.JPanel jPanel_Top_Panel;
    private javax.swing.JPanel jPanel_Top_bar;
    private javax.swing.JPopupMenu jPopupMenu_JTreeMenuFileOptions;
    private javax.swing.JPopupMenu jPopupMenu_JTreeMenuFolderOptions;
    private javax.swing.JScrollPane jScrollPane_Folder_Structure;
    private javax.swing.JPopupMenu.Separator jSeparator_1;
    private javax.swing.JPopupMenu.Separator jSeparator_2;
    private javax.swing.JPopupMenu.Separator jSeparator_4;
    private javax.swing.JTextField jTextField_Path;
    private javax.swing.JTree jTree_Folder_Structure;
    // End of variables declaration//GEN-END:variables
}
