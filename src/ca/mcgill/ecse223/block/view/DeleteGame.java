package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteGame {
    private String error = null;
    private JLabel errorMessage;

    private JFrame frame = new JFrame();
    private JLabel title = new JLabel();
    private JLabel label = new JLabel();
    private JLabel label2 = new JLabel();
    private JButton confirm = new JButton();

    public void DeleteGame(String game){


        errorMessage = new JLabel();
        errorMessage.setForeground(Color.RED);
        errorMessage.setBounds(125, 250, 200, 200);

        frame.setSize(350,300);
        frame.setLayout(null);
        frame.setVisible(true);

        //TODO Anne-Julies
        title.setText("Delete Game");
        title.setBounds(100,0,300,50);
        frame.add(title);

        label.setText("Are you sure you want" );
        label.setBounds(100, 100, 300, 20);
        frame.add(label);
        label2.setText("to delete the game "+game+"?");
        label2.setBounds(100, 130, 300, 20);
        frame.add(label2);

        confirm.setText("Confirm");
        confirm.setBounds(100, 200, 150, 50);
        frame.add(confirm);
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Block223Controller.deleteGame(game);
                    GameScreen gameScreen = new GameScreen();
                    gameScreen.refreshData();
                    frame.dispose();

                }
                catch (InvalidInputException a){
                    error =  a.getMessage();
                }
                refreshData();
            }
        });


    }

    private void refreshData() {
        // error
        errorMessage.setText(error);

    }
}