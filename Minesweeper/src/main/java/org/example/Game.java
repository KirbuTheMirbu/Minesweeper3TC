package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Console;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    int difficulty;
    Abstract_Field[][] field;
    jframeFields[][] buttons;
    int mines;
    int remainFlags;
    MyWindowType1 OknoGlowne;
    JPanel buttonPanel = new JPanel();
    int sizee;
    int remaining;

    Game(int dif) {
        System.out.println("NEW GAME");

        if(dif == 3){
            OknoGlowne = new MyWindowType1(800, 500);
        }
        else{
            OknoGlowne = new MyWindowType1(500, 500);
        }
        OknoGlowne.setVisible(true);
        OknoGlowne.setLocationRelativeTo(null);
        OknoGlowne.setTitle("Minesweeper");

        buttonPanel.setLayout(null);

        JButton returnMenu = new JButton("Return to menu");
        returnMenu.setBounds(10, 420, 150, 30);
        returnMenu.setFont(new Font("Arial", Font.PLAIN, 18));
        returnMenu.setMargin(new Insets(0, 0, 0, 0));
        returnMenu.setText("Return to menu");
        returnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    OknoGlowne.setVisible(false);
                    MainMenu menu = new MainMenu();
                };
            }

        });
        returnMenu.setLayout(buttonPanel.getLayout());
        buttonPanel.add(returnMenu);
        returnMenu.setVisible(true);

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
                field = new Abstract_Field[9][9];
                mines = 10;
                remainFlags = 10;
                remaining = 9 * 9 - mines;
                break;
            case 2:
                field = new Abstract_Field[16][16];
                mines = 40;
                remainFlags = 40;
                remaining = 16 * 16 - mines;
                break;
            case 3:
                field = new Abstract_Field[16][30];
                mines = 99;
                remainFlags = 99;
                remaining = 16 * 30 - mines;
                break;
            default:
                field = new Abstract_Field[100][100];
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
                field[tempX][tempY] = new Mine();
                field[tempX][tempY].setMine(true);
                field[tempX][tempY].setMinesAround(-1);
            }
            else {
                i--;
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
                        if(field[i-1][j].getisMine()){
                            field[i][j].setMinesAround(field[i][j].getMinesAround() + 1);
                        }
                    }
                    if(i != field.length-1){
                        if(field[i+1][j].getisMine()){
                            field[i][j].setMinesAround(field[i][j].getMinesAround() + 1);

                        }
                    }
                    if(j != 0){
                        if(field[i][j-1].getisMine()){
                            field[i][j].setMinesAround(field[i][j].getMinesAround() + 1);

                        }
                    }
                    if(j != field[0].length-1){
                        if(field[i][j+1].getisMine()){
                            field[i][j].setMinesAround(field[i][j].getMinesAround() + 1);

                        }
                    }
                    if(i != 0 && j != 0){if(field[i-1][j-1].getisMine()){
                        field[i][j].setMinesAround(field[i][j].getMinesAround() + 1);
                    }}
                    if(i != 0 && j != field[0].length-1){if(field[i-1][j+1].getisMine()){
                        field[i][j].setMinesAround(field[i][j].getMinesAround() + 1);
                    }}
                    if(i != field.length-1 && j != 0){if(field[i+1][j-1].getisMine()){
                        field[i][j].setMinesAround(field[i][j].getMinesAround() + 1);
                    }}
                    if(i != field.length-1 && j != field[0].length-1){if(field[i+1][j+1].getisMine()){
                        field[i][j].setMinesAround(field[i][j].getMinesAround() + 1);
                    }}
                }

            }
        }
        //returnTable(field);

        writeTable();
    }

    private void writeTable() {
        buttons = new jframeFields[field.length][field[0].length];

        OknoGlowne.add(buttonPanel);
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                jframeFields bttn = new jframeFields(i, j);
                bttn.text = String.valueOf(field[i][j].getMinesAround());
                if(bttn.text.equals("0")){
                    bttn.text = "";
                }
                if(field[i][j].getisMine()){
                    bttn.text = "M";
                    bttn.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            if(SwingUtilities.isRightMouseButton(e)  && !bttn.clicked){
                                if(bttn.ifFlagged){
                                    bttn.setForeground(bttn.curColor);
                                    bttn.setText("");
                                    bttn.ifFlagged = false;
                                    remainFlags++;
                                }
                                else if(remainFlags > 0){
                                    bttn.setForeground(Color.BLACK);
                                    bttn.setText("F");
                                    bttn.ifFlagged = true;
                                    remainFlags--;
                                }
                            } if (SwingUtilities.isLeftMouseButton(e)){
                                if(!bttn.ifFlagged && !bttn.clicked){
                                    for (int i = 0; i < field.length; i++) {
                                        for (int j = 0; j < field[0].length; j++) {
                                            buttons[i][j].clicked = true;
                                            buttons[i][i].setForeground(buttons[i][i].curColor);
                                            if(field[i][j].getisMine()){
                                                buttons[i][j].setText("M");
                                            }
                                            else {
                                                buttons[i][j].setText(String.valueOf(field[i][j].getMinesAround()));

                                            }

                                        }
                                    }
                                    OknoGlowne.setTitle("YOU LOSE");
                                }
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
                                    bttn.setForeground(bttn.curColor);
                                    bttn.setText("");
                                    bttn.ifFlagged = false;
                                    remainFlags++;
                                }
                                else if(remainFlags > 0){
                                    bttn.setForeground(Color.BLACK);
                                    bttn.setText("F");
                                    bttn.ifFlagged = true;
                                    remainFlags--;
                                }
                            }
                            else if (SwingUtilities.isLeftMouseButton(e) && !bttn.clicked) {
                                if(!bttn.ifFlagged){
                                    bttn.setText(bttn.text);
                                    if(bttn.text.equals("")){
                                        bttn.setBackground(Color.lightGray);
                                    }
                                    if(!bttn.clicked){
                                        bttn.clicked = true;
                                        buttons[bttn.tabX][bttn.tabY].clicked = true;
                                        remaining--;
                                        if(field[bttn.tabX][bttn.tabY].getMinesAround() == 0){
                                            fill(bttn.tabX, bttn.tabY);
                                        }
                                        System.out.println("Remaining: "+remaining);
                                    }
                                    if(remaining == 0){
                                        OknoGlowne.setTitle("YOU WIN :)");
                                    }
                                }
                            }
                        }
                    });
                }
                switch (bttn.text){
                    case "1":
                        bttn.setForeground(Color.blue);
                        bttn.curColor = Color.blue;
                        break;
                    case "2":
                        bttn.setForeground(Color.green);
                        bttn.curColor = Color.green;
                        break;
                    case "3":
                        bttn.setForeground(Color.red);
                        bttn.curColor = Color.red;
                        break;
                    case "4":
                        bttn.setForeground(Color.PINK);
                        bttn.curColor = Color.pink;
                        break;
                    case "5":
                        bttn.setForeground(Color.orange);
                        bttn.curColor = Color.orange;
                        break;
                    case "6":
                        bttn.setForeground(Color.cyan);
                        bttn.curColor = Color.cyan;
                        break;
                    case "7":
                        bttn.setForeground(Color.YELLOW);
                        bttn.curColor = Color.YELLOW;
                        break;
                    case "8":
                        bttn.setForeground(Color.GRAY);
                        bttn.curColor = Color.gray;
                        break;
                    default:
                        bttn.setForeground(Color.BLACK);
                        bttn.curColor = Color.BLACK;
                }

                bttn.setBounds(10+(j*sizee), 10+(i*sizee), sizee, sizee);
                //System.out.println(sizee);
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
        if(field[x][y].getMinesAround() == 0){
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
                    if(field[x-1][y].getMinesAround() == 0){
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
                        if(field[x-1][y-1].getMinesAround() == 0){
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
                        if(field[x-1][y+1].getMinesAround() == 0){
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
                    if(field[x][y-1].getMinesAround() == 0){
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
                    if(field[x+1][y].getMinesAround() == 0){
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
                        if(field[x+1][y-1].getMinesAround() == 0){
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
                        if(field[x+1][y+1].getMinesAround() == 0){
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
                    if(field[x][y+1].getMinesAround() == 0){
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
                } else if (tab[i][j].getisMine()) {
                    System.out.print("M ");
                }
                else {
                    System.out.print(tab[i][j].getMinesAround()+" ");
                }
            }
            System.out.println("");
        }
    }



}
