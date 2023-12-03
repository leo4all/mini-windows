/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.logic;

/**
 *
 * @author leonel
 */
public class KeyConstantsNowAllowed {
    public static final int VK_ESCAPE = 27;
    public static final int VK_P = 112;
    public static final int VK_Q = 113;
    public static final int VK_R = 114;
    public static final int VK_S = 115;
    public static final int VK_T = 116;
    public static final int VK_U = 117;
    public static final int VK_V = 118;
    public static final int VK_W = 119;
    public static final int VK_X = 120;
    public static final int VK_Y = 121;
    public static final int VK_Z = 122;
    public static final int VK_OPEN_BRACKET = 123;
    public static final int VK_PAUSE = 19;
    public static final int VK_PRINTSCREEN = 155;
    public static final int VK_DELETE = 127;
    public static final int VK_HOME = 36;
    public static final int VK_PAGE_UP = 33;
    public static final int VK_PAGE_DOWN = 34;
    public static final int VK_END = 35;
    public static final int VK_SHIFT = 16;
    public static final int VK_UP = 38;
    public static final int VK_LEFT = 37;
    public static final int VK_DOWN = 40;
    public static final int VK_RIGHT = 39;
    public static final int VK_CONTROL = 17;
    public static final int VK_ALT = 18;
    
    public static final int[] VALID_KEY_CODES = {
            VK_ESCAPE, VK_P, VK_Q, VK_R, VK_S, VK_T, VK_U, VK_V, VK_W, VK_X,
            VK_Y, VK_Z, VK_OPEN_BRACKET, VK_PAUSE, VK_PRINTSCREEN, VK_DELETE,
            VK_HOME, VK_PAGE_UP, VK_PAGE_DOWN, VK_END, VK_SHIFT, VK_UP, VK_LEFT,
            VK_DOWN, VK_RIGHT, VK_CONTROL, VK_ALT
    };
    
    public static boolean exists(int keyCode) {
        for (int validKeyCode : VALID_KEY_CODES) {
            if (keyCode == validKeyCode) {
                return true;
            }
        }
        return false;
    }
}
