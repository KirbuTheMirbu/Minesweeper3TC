package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class jframeFields extends JButton {
    int tabX;
    int tabY;
    String text;
    Boolean clicked;
    Boolean ifFlagged;
    Color curColor;

    public jframeFields(int x, int y) {
        tabX = x;
        tabY = y;
        clicked = false;
        ifFlagged = false;
    }


}
