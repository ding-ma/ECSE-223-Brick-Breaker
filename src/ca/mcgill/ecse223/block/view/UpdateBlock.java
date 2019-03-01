package ca.mcgill.ecse223.block.view;

import javax.swing.*;

public class UpdateBlock {
    public void UpdateBlock(){
        JFrame frame=new JFrame();
        JLabel label = new JLabel();
//TODO George
        label.setText("Update block Screen");
        label.setBounds(0,0,400,300);
        frame.add(label);

        frame.setSize(400,300);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
