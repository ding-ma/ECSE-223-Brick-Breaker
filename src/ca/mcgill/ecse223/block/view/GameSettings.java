package ca.mcgill.ecse223.block.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettings {
    public void GameSettings(){
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        JButton Bblock = new JButton();
        JButton BUpdateGame =  new JButton();

        label.setText("Update Menu");
        label.setBounds(0,0,300,50);
        frame.add(label);

        Bblock.setText("Blocks");
        Bblock.setBounds(30,50,150,30);
        Bblock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlockScreen BS = new BlockScreen();
                BS.BlockScreen();
                frame.dispose();
                BlockScreen.refreshData();
            }
        });
        frame.add(Bblock);

        BUpdateGame.setText("Update Game");
        BUpdateGame.setBounds(30,150,150,30);
        BUpdateGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateGame updateGame = new UpdateGame();
                updateGame.UpdateGame();
            }
        });
        frame.add(BUpdateGame);

        frame.setSize(250,300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.PINK);

    }
}