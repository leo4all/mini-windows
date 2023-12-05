/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.ui;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 *
 * @author leonel
 */
public class TimeLineEditorKit extends HTMLEditorKit {

    public String getContentType() {
        return "text/html";
    }

    @Override
    public StyleSheet getStyleSheet() {
        StyleSheet styleSheet = super.getStyleSheet();
        loadStyles(styleSheet);

        return styleSheet;
    }

    private void loadStyles(StyleSheet styleSheet) {
        try {
            styleSheet.importStyleSheet(getClass().getClassLoader().getResource("assets/style.css"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
