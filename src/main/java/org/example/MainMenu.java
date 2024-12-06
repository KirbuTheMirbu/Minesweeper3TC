package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu {
    MyWindowType1 Menu = new MyWindowType1(500, 500);
    JPanel content = new JPanel();

    MainMenu() {
        Menu.setVisible(true);
        Menu.setLocationRelativeTo(null);
        Menu.setTitle("Minesweeper - Menu");

        content.setLayout(null);
        Menu.add(content);

        jframeFields easy = new jframeFields(0, 0);
        jframeFields medium = new jframeFields(0, 1);
        jframeFields hard = new jframeFields(0, 2);
        JLabel text = new JLabel("Choose the difficulty");
        JLabel title = new JLabel("Minesweeper");

        easy.setBounds(50, 250, 100, 50);
        easy.setFont(new Font("Arial", Font.PLAIN, 20));
        easy.setMargin(new Insets(0, 0, 0, 0));
        easy.setSize(100, 50);
        easy.setText("Easy");
        easy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    Menu.setVisible(false);
                    Game game = new Game(1);
                };
            }

        });
        easy.setLayout(content.getLayout());

        medium.setBounds(180, 250, 100, 50);
        medium.setFont(new Font("Arial", Font.PLAIN, 20));
        medium.setMargin(new Insets(0, 0, 0, 0));
        medium.setSize(100, 50);
        medium.setText("Medium");
        medium.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    Menu.setVisible(false);
                    Game game = new Game(2);
                };
            }

        });
        medium.setLayout(content.getLayout());

        hard.setBounds(310, 250, 100, 50);
        hard.setFont(new Font("Arial", Font.PLAIN, 20));
        hard.setMargin(new Insets(0, 0, 0, 0));
        hard.setSize(100, 50);
        hard.setText("Hard");
        hard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    Menu.setVisible(false);
                    Game game = new Game(3);
                };
            }

        });
        hard.setLayout(content.getLayout());

        title.setBounds(120, 50, 400, 50);
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        title.setText("Minesweeper");
        title.setSize(400, 50);
        title.setLayout(content.getLayout());

        text.setBounds(80, 100, 400, 50);
        text.setFont(new Font("Arial", Font.PLAIN, 35));
        text.setSize(400, 50);
        text.setLayout(content.getLayout());
        text.setLayout(content.getLayout());


        easy.setVisible(true);
        medium.setVisible(true);
        hard.setVisible(true);
        title.setVisible(true);
        text.setVisible(true);
        content.add(easy);
        content.add(medium);
        content.add(hard);
        content.add(title);
        content.add(text);
        content.repaint();
    }
}

