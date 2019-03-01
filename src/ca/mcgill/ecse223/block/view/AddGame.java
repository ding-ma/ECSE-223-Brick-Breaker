package ca.mcgill.ecse223.block.view;

import javax.swing.*;

public class AddGame {
    public  void AddGame(){
        //TODO Yannick
        JFrame frame = new JFrame();
        JLabel label = new JLabel();

        label.setText("Define Game Settings");
        label.setBounds(0,0,400,50);
        frame.add(label);

        frame.setSize(300,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    }
