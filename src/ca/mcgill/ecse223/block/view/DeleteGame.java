package ca.mcgill.ecse223.block.view;

import javax.swing.*;

public class DeleteGame {
    public void DeleteGame(){
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        //TODO Anne-Julies
        label.setText("Delete Game");
        label.setBounds(0,0,400,50);
        frame.add(label);

        frame.setSize(300,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
