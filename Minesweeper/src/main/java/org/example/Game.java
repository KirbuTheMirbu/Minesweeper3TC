package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    int difficulty;
    Field[][] field;
    jframeFields[][] buttons;
    int mines;
    int remainFlags;
    MyWindowType1 OknoGlowne = new MyWindowType1();
    JPanel buttonPanel = new JPanel();
    int sizee;
    int remaining;

    Game(int dif) {
        OknoGlowne.setVisible(true);
        if(dif == 3){
            OknoGlowne.setSize(800, 500);
        }
        else{
            OknoGlowne.setSize(500, 500);
        }
        OknoGlowne.setLocationRelativeTo(null);
        OknoGlowne.setTitle("Minesweeper");

        //buttonPanel.setBounds(100, 100, 300, 300);
        /*JButton panel = new JButton("1");
        JButton panel2 = new JButton("1");
        panel.setBounds(0,0,20,20);
        panel2.setBounds(50,50,20,20);
        panel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel2.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        panel.setMargin(new Insets(0, 0, 0, 0));
        panel2.setMargin(new Insets(0, 0, 0, 0));
        panel.setVisible(true);
        panel2.setVisible(true);
        panel.setLayout(null);
        panel2.setLayout(null);
        //buttonPanel.add(panel);
        //buttonPanel.add(panel2);
        OknoGlowne.add(panel);
        OknoGlowne.add(panel2);*/



        difficulty = dif;
        switch (difficulty) {
            case 1:
                field = new Field[9][9];
                mines = 10;
                remainFlags = 10;
                remaining = 9 * 9 - mines;
                break;
            case 2:
                field = new Field[16][16];
                mines = 40;
                remainFlags = 40;
                remaining = 16 * 16 - mines;
                break;
            case 3:
                field = new Field[16][30];
                mines = 99;
                remainFlags = 99;
                remaining = 16 * 30 - mines;
                break;
            default:
                field = new Field[100][100];
                mines = 999;
                remainFlags = 999;
                remaining = 1000 * 1000 - mines;
                break;
        }
        sizee = 400 / field.length;
        Random rand = new Random();
        for (int i = 0; i < mines; i++) {
            int tempX = rand.nextInt(field.length);
            int tempY = rand.nextInt(field[0].length);
            if(field[tempX][tempY] == null){
                field[tempX][tempY] = new Field();
                field[tempX][tempY].isMine = true;
                field[tempX][tempY].minesAround = -1;
            }
        }

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == null) {
                    field[i][j] = new Field();
                }

            }
        }

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] != null) {
                    if(i != 0){
                        if(field[i-1][j].isMine){
                            field[i][j].minesAround++;
                        }
                    }
                    if(i != field.length-1){
                        if(field[i+1][j].isMine){
                            field[i][j].minesAround++;
                        }
                    }
                    if(j != 0){
                        if(field[i][j-1].isMine){
                            field[i][j].minesAround++;
                        }
                    }
                    if(j != field[0].length-1){
                        if(field[i][j+1].isMine){
                            field[i][j].minesAround++;
                        }
                    }
                    if(i != 0 && j != 0){if(field[i-1][j-1].isMine){field[i][j].minesAround++;}}
                    if(i != 0 && j != field[0].length-1){if(field[i-1][j+1].isMine){field[i][j].minesAround++;}}
                    if(i != field.length-1 && j != 0){if(field[i+1][j-1].isMine){field[i][j].minesAround++;}}
                    if(i != field.length-1 && j != field[0].length-1){if(field[i+1][j+1].isMine){field[i][j].minesAround++;}}
                }

            }
        }
        returnTable(field);

        writeTable();
    }

    private void writeTable() {
        buttons = new jframeFields[field.length][field[0].length];
        buttonPanel.setLayout(null);
        OknoGlowne.add(buttonPanel);
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                jframeFields bttn = new jframeFields(i, j);
                bttn.text = String.valueOf(field[i][j].minesAround);
                if(field[i][j].isMine){
                    bttn.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            if(SwingUtilities.isRightMouseButton(e)  && !bttn.clicked){
                                if(bttn.ifFlagged){
                                    bttn.setText("");
                                    bttn.ifFlagged = false;
                                }
                                else{
                                    bttn.setText("F");
                                    bttn.ifFlagged = true;
                                }
                            } if (SwingUtilities.isLeftMouseButton(e)){
                                for (int i = 0; i < field.length; i++) {
                                    for (int j = 0; j < field[0].length; j++) {
                                        if(field[i][j].isMine){
                                            buttons[i][j].setText("M");
                                        }
                                        else {
                                            buttons[i][j].setText(String.valueOf(field[i][j].minesAround));
                                        }

                                    }
                                }
                                OknoGlowne.setTitle("YOU LOSE");
                            }
                        }
                    });
                }
                else {
                    bttn.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            if(SwingUtilities.isRightMouseButton(e) && !bttn.clicked) {
                                if(bttn.ifFlagged){
                                    bttn.setText("");
                                    bttn.ifFlagged = false;
                                }
                                else{
                                    bttn.setText("F");
                                    bttn.ifFlagged = true;
                                }
                            }
                            else if (SwingUtilities.isLeftMouseButton(e)) {
                                bttn.setText(bttn.text);
                                if(!bttn.clicked){
                                    bttn.clicked = true;
                                    remaining--;
                                    System.out.println("Remaining: "+remaining);
                                }
                                if(remaining == 0){
                                    OknoGlowne.setTitle("YOU WIN :)");
                                }
                            }
                        }
                    });
                }

                bttn.setBounds(10+(j*sizee), 10+(i*sizee), sizee, sizee);
                System.out.println(sizee);
                bttn.setFont(new Font("Arial", Font.PLAIN, 20));
                bttn.setMargin(new Insets(0, 0, 0, 0));
                bttn.setSize(sizee, sizee);
                bttn.setLayout(buttonPanel.getLayout());
                //bttn.repaint();
                buttonPanel.add(bttn);
                //OknoGlowne.add(bttn);
                buttons[i][j] = bttn;
                //bttn.repaint();
                bttn.setVisible(true);
            }
        }
        buttonPanel.repaint();
        OknoGlowne.add(buttonPanel);
        OknoGlowne.repaint();
    }

    void returnTable(Field[][] tab){
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == null){
                    System.out.print("0 ");
                } else if (tab[i][j].isMine) {
                    System.out.print("M ");
                }
                else {
                    System.out.print(tab[i][j].minesAround+" ");
                }
            }
            System.out.println("");
        }
    }



}
