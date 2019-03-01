package ca.mcgill.ecse223.block.view;

import javax.swing.*;

public class MoveBlock {
    public void MoveBlock(){
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        //TODO Mert
        label.setText("Move a Block from one gird location to another in a level");
        label.setBounds(0,0,400,50);
        frame.add(label);

        frame.setSize(300,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
