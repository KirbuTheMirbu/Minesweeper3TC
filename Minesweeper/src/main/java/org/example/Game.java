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
                if(bttn.text.equals("0")){
                    bttn.text = "";
                }
                switch (bttn.text){
                    case "1":
                        bttn.setForeground(Color.blue);
                        break;
                    case "2":
                        bttn.setForeground(Color.green);
                        break;
                    case "3":
                        bttn.setForeground(Color.red);
                        break;
                    case "4":
                        bttn.setForeground(Color.PINK);
                        break;
                    case "5":
                        bttn.setForeground(Color.orange);
                        break;
                    case "6":
                        bttn.setForeground(Color.cyan);
                        break;
                    case "7":
                        bttn.setForeground(Color.YELLOW);
                        break;
                    case "8":
                        bttn.setForeground(Color.GRAY);
                        break;
                    default:
                        bttn.setForeground(Color.BLACK);
                }
                if(field[i][j].isMine){
                    bttn.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            if(SwingUtilities.isRightMouseButton(e)  && !bttn.clicked){
                                if(bttn.ifFlagged){
                                    bttn.setText("");
                                    bttn.ifFlagged = false;
                                    remainFlags++;
                                }
                                else if(remainFlags > 0){
                                    bttn.setText("F");
                                    bttn.ifFlagged = true;
                                    remainFlags--;
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
                                    remainFlags++;
                                }
                                else if(remainFlags > 0){
                                    bttn.setText("F");
                                    bttn.ifFlagged = true;
                                    remainFlags--;
                                }
                            }
                            else if (SwingUtilities.isLeftMouseButton(e)) {
                                bttn.setText(bttn.text);
                                if(bttn.text.equals("")){
                                    bttn.setBackground(Color.lightGray);
                                }
                                if(!bttn.clicked){
                                    fill(bttn.tabX, bttn.tabY);
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

    void fill(int x, int y){
        if(field[x][y].minesAround == 0){
            if(x != 0){
                if(!buttons[x-1][y].clicked){
                    buttons[x-1][y].setText(String.valueOf(buttons[x-1][y].text));
                    if(buttons[x-1][y].text.equals("")){
                        buttons[x-1][y].setBackground(Color.lightGray);
                    }
                    buttons[x-1][y].clicked = true;
                    remaining--;
                    if(remaining == 0){
                        OknoGlowne.setTitle("YOU WIN :)");
                    }
                    System.out.println("Remaining: "+remaining);
                    if(field[x-1][y].minesAround == 0){
                        fill(x-1, y);
                    }
                }
                if(y != 0){
                    if(!buttons[x-1][y-1].clicked){
                        buttons[x-1][y-1].setText(String.valueOf(buttons[x-1][y-1].text));
                        if(buttons[x-1][y-1].text.equals("")){
                            buttons[x-1][y-1].setBackground(Color.lightGray);
                        }
                        buttons[x-1][y-1].clicked = true;
                        remaining--;
                        if(remaining == 0){
                            OknoGlowne.setTitle("YOU WIN :)");
                        }
                        System.out.println("Remaining: "+remaining);
                        if(field[x-1][y-1].minesAround == 0){
                            fill(x-1, y-1);
                        }
                    }
                }
                if(y != field[0].length-1){
                    if(!buttons[x-1][y+1].clicked){
                        buttons[x-1][y+1].setText(String.valueOf(buttons[x-1][y+1].text));
                        if(buttons[x-1][y+1].text.equals("")){
                            buttons[x-1][y+1].setBackground(Color.lightGray);
                        }
                        buttons[x-1][y+1].clicked = true;
                        remaining--;
                        if(remaining == 0){
                            OknoGlowne.setTitle("YOU WIN :)");
                        }
                        System.out.println("Remaining: "+remaining);
                        if(field[x-1][y+1].minesAround == 0){
                            fill(x-1, y+1);
                        }
                    }
                }
            }
            if(y != 0){
                if(!buttons[x][y-1].clicked){
                    buttons[x][y-1].setText(String.valueOf(buttons[x][y-1].text));
                    if(buttons[x][y-1].text.equals("")){
                        buttons[x][y-1].setBackground(Color.lightGray);
                    }
                    buttons[x][y-1].clicked = true;
                    remaining--;
                    if(remaining == 0){
                        OknoGlowne.setTitle("YOU WIN :)");
                    }
                    System.out.println("Remaining: "+remaining);
                    if(field[x][y-1].minesAround == 0){
                        fill(x, y-1);
                    }
                }
            }
            if(x != field.length-1){
                if(!buttons[x+1][y].clicked){
                    buttons[x+1][y].setText(String.valueOf(buttons[x+1][y].text));
                    if(buttons[x+1][y].text.equals("")){
                        buttons[x+1][y].setBackground(Color.lightGray);
                    }
                    buttons[x+1][y].clicked = true;
                    remaining--;
                    if(remaining == 0){
                        OknoGlowne.setTitle("YOU WIN :)");
                    }
                    System.out.println("Remaining: "+remaining);
                    if(field[x+1][y].minesAround == 0){
                        fill(x+1, y);
                    }
                }
                if(y != 0){
                    if(!buttons[x+1][y-1].clicked){
                        buttons[x+1][y-1].setText(String.valueOf(buttons[x+1][y-1].text));
                        if(buttons[x+1][y-1].text.equals("")){
                            buttons[x+1][y-1].setBackground(Color.lightGray);
                        }
                        buttons[x+1][y-1].clicked = true;
                        remaining--;
                        if(remaining == 0){
                            OknoGlowne.setTitle("YOU WIN :)");
                        }
                        System.out.println("Remaining: "+remaining);
                        if(field[x+1][y-1].minesAround == 0){
                            fill(x+1, y-1);
                        }
                    }
                }
                if(y != field[0].length-1){
                    if(!buttons[x+1][y+1].clicked){
                        buttons[x+1][y+1].setText(String.valueOf(buttons[x+1][y+1].text));
                        if(buttons[x+1][y+1].text.equals("")){
                            buttons[x+1][y+1].setBackground(Color.lightGray);
                        }
                        buttons[x+1][y+1].clicked = true;
                        remaining--;
                        if(remaining == 0){
                            OknoGlowne.setTitle("YOU WIN :)");
                        }
                        System.out.println("Remaining: "+remaining);
                        if(field[x+1][y+1].minesAround == 0){
                            fill(x+1, y+1);
                        }
                    }
                }
            }
            if(y != field[0].length-1){
                if(!buttons[x][y+1].clicked){
                    buttons[x][y+1].setText(String.valueOf(buttons[x][y+1].text));
                    if(buttons[x][y+1].text.equals("")){
                        buttons[x][y+1].setBackground(Color.lightGray);
                    }
                    buttons[x][y+1].clicked = true;
                    remaining--;
                    if(remaining == 0){
                        OknoGlowne.setTitle("YOU WIN :)");
                    }
                    System.out.println("Remaining: "+remaining);
                    if(field[x][y+1].minesAround == 0){
                        fill(x, y+1);
                    }
                }
            }
        }
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
