package ca.mcgill.ecse223.block.view;

import javax.swing.*;

public class DeleteGame {
    public void DeleteGame(String game){
        JFrame frame = new JFrame();
        JLabel title = new JLabel();
        JLabel label = new JLabel();
        JButton confirm = new JButton();

        frame.setSize(300,400);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //TODO Anne-Julies
        title.setText("Delete Game");
        title.setBounds(0,0,300,50);
        frame.add(title);

        label.setText("Retrieving the game "+game+"\n will not be possible after confirmation");
        label.setBounds(0, 100, 300, 50);
        frame.add(label);

        confirm.setText("Confirm");
        confirm.setBounds(150, 200, 50, 50);
        frame.add(confirm);

    }
}
