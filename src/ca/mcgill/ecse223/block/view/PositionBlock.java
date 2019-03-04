package ca.mcgill.ecse223.block.view;

import javax.swing.*;

public class PositionBlock {
    public void PositionBlock(){
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
//TODO George
        label.setText("Position a block at grid location in a level");
        label.setBounds(0,0,400,50);
        frame.add(label);

        frame.setSize(300,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
