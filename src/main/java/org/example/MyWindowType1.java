package org.example;

import javax.swing.*;
import java.awt.*;

public class MyWindowType1 extends JFrame {
    public MyWindowType1() throws HeadlessException {
        this.setVisible(true);
        this.setSize(300, 500);
    }
    public MyWindowType1(int w, int h) throws HeadlessException {
        this.setVisible(true);
        this.setSize(w, h);
    }
}
