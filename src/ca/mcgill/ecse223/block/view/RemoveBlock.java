package ca.mcgill.ecse223.block.view;

import javax.swing.*;

public class RemoveBlock {
    public void RemoveBlock(){
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
//TODO Mert
        label.setText("Remove Block From a level");
        label.setBounds(0,0,400,50);
        frame.add(label);

        frame.setSize(300,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
