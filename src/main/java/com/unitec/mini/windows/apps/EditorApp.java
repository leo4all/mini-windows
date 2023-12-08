package com.unitec.mini.windows.apps;

import com.unitec.mini.windows.logic.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.text.MutableAttributeSet;

public class EditorApp extends JInternalFrame implements AppInterface {
    private final int DEFAULT_FONT_SIZE = 12;
    private final int DEFAULT_ALIGNMENT = StyleConstants.ALIGN_LEFT;
    private User userAuthen;
    
    public EditorApp(User user) {
        this.userAuthen = user;

        initComponents();
        setComponents();
        setDefaultEditorAttributes();
    }

    public void setComponents() {
        ImageIcon appIcon = new ImageIcon(getClass().getResource("/images/icon_editor_20.png"));
        this.setFrameIcon(appIcon);
        textPane.setFont(new Font("Arial", Font.PLAIN, 20));

        jComboBox_FontFamily.setSelectedItem("Arial");
        jComboBox_Font_Size.setSelectedItem("12");
    }
    
    private void setDefaultEditorAttributes(){
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setForeground(attrs, Color.BLACK);
        StyleConstants.setFontSize(attrs, DEFAULT_FONT_SIZE);
        StyleConstants.setAlignment(attrs, DEFAULT_ALIGNMENT);
        doc.setCharacterAttributes(0, doc.getLength(), attrs, false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser = new javax.swing.JColorChooser();
        jPanel1 = new javax.swing.JPanel();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = ge.getAvailableFontFamilyNames(Locale.getDefault());
        jComboBox_FontFamily = new javax.swing.JComboBox<>(fonts);
        jComboBox_Font_Style = new javax.swing.JComboBox<>();
        jComboBox_Font_Size = new javax.swing.JComboBox<>();
        jPanel_Align = new javax.swing.JPanel();
        jButton_Align_Left = new javax.swing.JButton();
        jButton_Align_Center = new javax.swing.JButton();
        jButton_Align_Right = new javax.swing.JButton();
        jPanel_Font_Style = new javax.swing.JPanel();
        jButton_Bold = new javax.swing.JButton();
        jButton_Italic = new javax.swing.JButton();
        jButton_Underline = new javax.swing.JButton();
        jButton_Font_Color = new javax.swing.JButton();
        Btn_SaveinDocument_user = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane_textPane = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem_New = new javax.swing.JMenuItem();
        jMenuItem_Open = new javax.swing.JMenuItem();
        jMenuItem_Save = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_Exit = new javax.swing.JMenuItem();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Editor");

        jComboBox_FontFamily.setToolTipText("Font Family");
        jComboBox_FontFamily.setFocusable(false);
        jComboBox_FontFamily.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_FontFamilyItemStateChanged(evt);
            }
        });

        jComboBox_Font_Style.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Regular", "Oblique", "Bold", "Bold Oblique" }));
        jComboBox_Font_Style.setFocusable(false);
        jComboBox_Font_Style.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_Font_StyleItemStateChanged(evt);
            }
        });

        jComboBox_Font_Size.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48" }));
        jComboBox_Font_Size.setToolTipText("Font Size");
        jComboBox_Font_Size.setFocusable(false);
        jComboBox_Font_Size.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_Font_SizeItemStateChanged(evt);
            }
        });

        jButton_Align_Left.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editor/icon-align-left.png"))); // NOI18N
        jButton_Align_Left.setToolTipText("Align Left");
        jButton_Align_Left.setFocusable(false);
        jButton_Align_Left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Align_LeftActionPerformed(evt);
            }
        });

        jButton_Align_Center.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editor/icon-align-center.png"))); // NOI18N
        jButton_Align_Center.setToolTipText("Align Center");
        jButton_Align_Center.setFocusable(false);
        jButton_Align_Center.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Align_CenterActionPerformed(evt);
            }
        });

        jButton_Align_Right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editor/icon-align-right.png"))); // NOI18N
        jButton_Align_Right.setFocusable(false);
        jButton_Align_Right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Align_RightActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_AlignLayout = new javax.swing.GroupLayout(jPanel_Align);
        jPanel_Align.setLayout(jPanel_AlignLayout);
        jPanel_AlignLayout.setHorizontalGroup(
            jPanel_AlignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AlignLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_Align_Left)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Align_Center)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Align_Right)
                .addContainerGap())
        );
        jPanel_AlignLayout.setVerticalGroup(
            jPanel_AlignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AlignLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_AlignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Align_Left)
                    .addComponent(jButton_Align_Center)
                    .addComponent(jButton_Align_Right))
                .addContainerGap())
        );

        jButton_Bold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editor/icon-bold.png"))); // NOI18N
        jButton_Bold.setToolTipText("Bold");
        jButton_Bold.setFocusable(false);
        jButton_Bold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BoldActionPerformed(evt);
            }
        });

        jButton_Italic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editor/icon-italic.png"))); // NOI18N
        jButton_Italic.setToolTipText("Italic");
        jButton_Italic.setFocusable(false);
        jButton_Italic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ItalicActionPerformed(evt);
            }
        });

        jButton_Underline.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editor/icon-underline.png"))); // NOI18N
        jButton_Underline.setToolTipText("Underline");
        jButton_Underline.setFocusable(false);
        jButton_Underline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UnderlineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Font_StyleLayout = new javax.swing.GroupLayout(jPanel_Font_Style);
        jPanel_Font_Style.setLayout(jPanel_Font_StyleLayout);
        jPanel_Font_StyleLayout.setHorizontalGroup(
            jPanel_Font_StyleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Font_StyleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_Bold)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Italic)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Underline)
                .addContainerGap())
        );
        jPanel_Font_StyleLayout.setVerticalGroup(
            jPanel_Font_StyleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Font_StyleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Font_StyleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Bold)
                    .addComponent(jButton_Italic)
                    .addComponent(jButton_Underline))
                .addContainerGap())
        );

        jButton_Font_Color.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editor/icon-text-color1.png"))); // NOI18N
        jButton_Font_Color.setFocusable(false);
        jButton_Font_Color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Font_ColorActionPerformed(evt);
            }
        });

        Btn_SaveinDocument_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editor/icon-save.png"))); // NOI18N
        Btn_SaveinDocument_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_SaveinDocument_userActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jComboBox_FontFamily, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_Font_Style, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_Font_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_Font_Style, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Font_Color)
                .addGap(16, 16, 16)
                .addComponent(jPanel_Align, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Btn_SaveinDocument_user, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton_Font_Color)
                    .addComponent(jPanel_Font_Style, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_Align, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox_FontFamily, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox_Font_Style, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox_Font_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Btn_SaveinDocument_user))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane_textPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane_textPane.setViewportView(textPane);

        jPanel2.add(jScrollPane_textPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 767, 559));

        jMenu1.setMnemonic('F');
        jMenu1.setText("File");
        jMenu1.setIconTextGap(2);

        jMenuItem_New.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_New.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editor/icon-new.png"))); // NOI18N
        jMenuItem_New.setMnemonic('N');
        jMenuItem_New.setText("New");
        jMenu1.add(jMenuItem_New);

        jMenuItem_Open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_Open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editor/icon-opened-folder.png"))); // NOI18N
        jMenuItem_Open.setMnemonic('O');
        jMenuItem_Open.setText("Open");
        jMenuItem_Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_OpenActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_Open);

        jMenuItem_Save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_Save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editor/icon-save.png"))); // NOI18N
        jMenuItem_Save.setText("Save");
        jMenuItem_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_SaveActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_Save);
        jMenu1.add(jSeparator1);

        jMenuItem_Exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_Exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editor/icon-close-window.png"))); // NOI18N
        jMenuItem_Exit.setMnemonic('E');
        jMenuItem_Exit.setText("Exit");
        jMenuItem_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_Exit);

        jMenuBar.add(jMenu1);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ExitActionPerformed
        this.closeFrame();
    }//GEN-LAST:event_jMenuItem_ExitActionPerformed

    private void jButton_Font_ColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Font_ColorActionPerformed
        int option = JOptionPane.showConfirmDialog(null, 
                jColorChooser, "Selecciona un color", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (option == JOptionPane.OK_OPTION) {
            Color selectedColor = jColorChooser.getColor();
            StyledDocument doc = textPane.getStyledDocument();
            int start = textPane.getSelectionStart();
            int end = textPane.getSelectionEnd();

            SimpleAttributeSet attrs = new SimpleAttributeSet();
            StyleConstants.setForeground(attrs, selectedColor);
            doc.setCharacterAttributes(start, end - start, attrs, false);

            MutableAttributeSet inputAttributes = textPane.getInputAttributes();
            inputAttributes.removeAttributes(inputAttributes);
            StyleConstants.setForeground(inputAttributes, selectedColor);
            
            System.out.println(selectedColor);
            System.out.println(start);
            System.out.println(end);
            System.out.println(textPane.getText());
        }
    }//GEN-LAST:event_jButton_Font_ColorActionPerformed

    private void jMenuItem_OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_OpenActionPerformed
        JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            fileChooser.setFileFilter(filter);

            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                Scanner fileIn = null;

                try {
                    fileIn = new Scanner(file);
                    if (file.isFile()) {
                        textPane.setText("");
                        textPane.getStyledDocument().remove(0, textPane.getStyledDocument().getLength());

                        int totalStyles = Integer.parseInt(fileIn.nextLine());

                        for (int i = 0; i < totalStyles; i++) {
                            String text = fileIn.nextLine();
                            Color textColor = Color.decode(fileIn.nextLine());
                            String fontFamily = fileIn.nextLine();
                            int fontSize = Integer.parseInt(fileIn.nextLine());
                            SimpleAttributeSet attrs = new SimpleAttributeSet();
                            StyleConstants.setForeground(attrs, textColor);
                            StyleConstants.setFontFamily(attrs, fontFamily);
                            StyleConstants.setFontSize(attrs, fontSize);
                            int offset = textPane.getStyledDocument().getLength();
                            textPane.getStyledDocument().insertString(offset, text, attrs);
                        }
                    }
                } catch (FileNotFoundException | BadLocationException ex) {
                    ex.printStackTrace();
                } finally {
                    if (fileIn != null) {
                        fileIn.close();
                    }
                }
            }
    }//GEN-LAST:event_jMenuItem_OpenActionPerformed

    private void jMenuItem_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_SaveActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));

        int response = fileChooser.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file;
            PrintWriter fileOut = null;
            
            file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try {
                fileOut = new PrintWriter(file);

                int totalStyles = textPane.getStyledDocument().getLength();
                fileOut.println(totalStyles);

                for (int i = 0; i < totalStyles; i++) {
                    Element element = textPane.getStyledDocument().getCharacterElement(i);
                    AttributeSet attrs = element.getAttributes();
                    String text = textPane.getStyledDocument().getText(i, 1);
                    fileOut.println(text);
                    Color textColor = StyleConstants.getForeground(attrs);
                    fileOut.println(String.format("#%06X", textColor.getRGB() & 0xFFFFFF));
                    String fontFamily = StyleConstants.getFontFamily(attrs);
                    fileOut.println(fontFamily);
                    int fontSize = StyleConstants.getFontSize(attrs);
                    fileOut.println(fontSize);
                }
            } catch (FileNotFoundException | BadLocationException ex) {
                ex.printStackTrace();
            } finally {
                if (fileOut != null) {
                    fileOut.close();
                }
            }
        }
    }//GEN-LAST:event_jMenuItem_SaveActionPerformed

    private void jComboBox_FontFamilyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_FontFamilyItemStateChanged
        StyledDocument doc = textPane.getStyledDocument();
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();

        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setFontFamily(attrs, (String) jComboBox_FontFamily.getSelectedItem());
        doc.setCharacterAttributes(start, end, attrs, false);
    }//GEN-LAST:event_jComboBox_FontFamilyItemStateChanged

    private void jComboBox_Font_SizeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_Font_SizeItemStateChanged
        StyledDocument doc = textPane.getStyledDocument();
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();
        
        
        String selectedSize = (String) jComboBox_Font_Size.getSelectedItem();
        if (selectedSize != null) {
            int size = Integer.parseInt(selectedSize);
            SimpleAttributeSet attrs = new SimpleAttributeSet();
            StyleConstants.setFontSize(attrs, size);
            doc.setCharacterAttributes(start, end - start, attrs, false);
            
            MutableAttributeSet inputAttributes = textPane.getInputAttributes();
            inputAttributes.removeAttributes(inputAttributes);
            StyleConstants.setFontSize(inputAttributes, size);
        }
    }//GEN-LAST:event_jComboBox_Font_SizeItemStateChanged

    private void jButton_BoldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BoldActionPerformed
        switchFormat(StyleConstants.Bold);
    }//GEN-LAST:event_jButton_BoldActionPerformed

    private void jButton_ItalicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ItalicActionPerformed
        switchFormat(StyleConstants.Italic);
    }//GEN-LAST:event_jButton_ItalicActionPerformed

    private void jButton_UnderlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UnderlineActionPerformed
        switchFormat(StyleConstants.Underline);
    }//GEN-LAST:event_jButton_UnderlineActionPerformed

    private void jButton_Align_LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Align_LeftActionPerformed
        switchAlignment(StyleConstants.ALIGN_LEFT);
    }//GEN-LAST:event_jButton_Align_LeftActionPerformed

    private void jButton_Align_CenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Align_CenterActionPerformed
        switchAlignment(StyleConstants.ALIGN_CENTER);
    }//GEN-LAST:event_jButton_Align_CenterActionPerformed

    private void jButton_Align_RightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Align_RightActionPerformed
        switchAlignment(StyleConstants.ALIGN_RIGHT);
    }//GEN-LAST:event_jButton_Align_RightActionPerformed

    private void jComboBox_Font_StyleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_Font_StyleItemStateChanged
        
    }//GEN-LAST:event_jComboBox_Font_StyleItemStateChanged

    private void Btn_SaveinDocument_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_SaveinDocument_userActionPerformed
        
        String fileName = JOptionPane.showInputDialog("Enter document name:");
        if (fileName != null && !fileName.trim().isEmpty()) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));

            String filePath = "src/main/users/"+userAuthen+"/"+"Documents/"+ fileName +".txt";
            File file = new File(filePath);
            PrintWriter fileOut = null;

            try {
                fileOut = new PrintWriter(file);

                int totalStyles = textPane.getStyledDocument().getLength();
                fileOut.println(totalStyles);

                for (int i = 0; i < totalStyles; i++) {
                    Element element = textPane.getStyledDocument().getCharacterElement(i);
                    AttributeSet attrs = element.getAttributes();
                    String text = textPane.getStyledDocument().getText(i, 1);
                    fileOut.println(text);
                    Color textColor = StyleConstants.getForeground(attrs);
                    fileOut.println(String.format("#%06X", textColor.getRGB() & 0xFFFFFF));
                    String fontFamily = StyleConstants.getFontFamily(attrs);
                    fileOut.println(fontFamily);
                    int fontSize = StyleConstants.getFontSize(attrs);
                    fileOut.println(fontSize);
                }
            } catch (FileNotFoundException | BadLocationException ex) {
                ex.printStackTrace();
            } finally {
                if (fileOut != null) {
                    fileOut.close();
                }
            }
        }
    }//GEN-LAST:event_Btn_SaveinDocument_userActionPerformed

    private void switchAlignment(int alignment) {
        StyledDocument doc = textPane.getStyledDocument();
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();

        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attrs, alignment);

        int paragraphStart = doc.getParagraphElement(start).getStartOffset();
        int paragraphEnd = doc.getParagraphElement(end - 1).getEndOffset();
        doc.setParagraphAttributes(paragraphStart, paragraphEnd - paragraphStart, attrs, false);

        updateButtonState(alignment);
    }
    
    private void updateButtonState(int currentAlignment){
        jButton_Align_Left.setSelected(currentAlignment == StyleConstants.ALIGN_LEFT);
        jButton_Align_Center.setSelected(currentAlignment == StyleConstants.ALIGN_CENTER);
        jButton_Align_Right.setSelected(currentAlignment == StyleConstants.ALIGN_RIGHT);
    }

    private void switchFormat(Object style){
        StyledDocument doc = textPane.getStyledDocument();
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();

        SimpleAttributeSet attr = new SimpleAttributeSet();
        boolean isStyleAplied = doc.getCharacterElement(start).getAttributes().containsAttribute(style, Boolean.TRUE);
        
        attr.addAttribute(style, true);
        if (isStyleAplied) {
            attr.addAttribute(style, false);
        }

        doc.setCharacterAttributes(start, end - start, attr, false);
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
    private javax.swing.JButton Btn_SaveinDocument_user;
    private javax.swing.JButton jButton_Align_Center;
    private javax.swing.JButton jButton_Align_Left;
    private javax.swing.JButton jButton_Align_Right;
    private javax.swing.JButton jButton_Bold;
    private javax.swing.JButton jButton_Font_Color;
    private javax.swing.JButton jButton_Italic;
    private javax.swing.JButton jButton_Underline;
    private javax.swing.JColorChooser jColorChooser;
    private javax.swing.JComboBox<String> jComboBox_FontFamily;
    private javax.swing.JComboBox<String> jComboBox_Font_Size;
    private javax.swing.JComboBox<String> jComboBox_Font_Style;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItem_Exit;
    private javax.swing.JMenuItem jMenuItem_New;
    private javax.swing.JMenuItem jMenuItem_Open;
    private javax.swing.JMenuItem jMenuItem_Save;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_Align;
    private javax.swing.JPanel jPanel_Font_Style;
    private javax.swing.JScrollPane jScrollPane_textPane;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextPane textPane;
    // End of variables declaration//GEN-END:variables
}
