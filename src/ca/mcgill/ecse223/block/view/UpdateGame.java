package ca.mcgill.ecse223.block.view;

import javax.swing.*;

public class UpdateGame {
    public void UpdateGame(){
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
//TODO Anne-Julie
        label.setText("UpdateGame");
        label.setBounds(0,0,400,50);
        frame.add(label);

        frame.setSize(300,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
