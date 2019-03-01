package ca.mcgill.ecse223.block.view;

import javax.swing.*;
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

        Bblock.setText("Block Settings");
        Bblock.setBounds(30,50,100,50);
        Bblock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlockScreen BS = new BlockScreen();
                BS.BlockScreen();
            }
        });
        frame.add(Bblock);

        BUpdateGame.setText("Add Game");
        BUpdateGame.setBounds(30,150,100,50);
        BUpdateGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddGame addGame= new AddGame();
                addGame.AddGame();
            }
        });
        frame.add(BUpdateGame);


        frame.setSize(200,300);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
