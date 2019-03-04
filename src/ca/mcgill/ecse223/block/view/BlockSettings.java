package ca.mcgill.ecse223.block.view;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlockSettings {
	
	private JLabel label;
	private JButton settingsButton;
	private JButton levelButton;
	
    public void BlockSettings(){
        JFrame frame = new JFrame();
        label = new JLabel();
        settingsButton = new JButton();
        levelButton = new JButton();

        label.setText("Blocks/Levels Screen");
        label.setBounds(80,0,400,50);
       

        settingsButton.setText("Block Settings");
        settingsButton.setBounds(50,50,200,50);
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              UpdateBlock updateBlock = new UpdateBlock();
              updateBlock.UpdateBlock();
              frame.dispose();
            }
        });
        

        levelButton.setText("Level Settings");
        levelButton.setBounds(50,100, 200, 50);
        levelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LevelScreen levelScreen = new LevelScreen();
                levelScreen.LevelScreen();
                frame.dispose();
            }
        });
        frame.add(settingsButton);
        frame.add(levelButton);
        frame.add(label);
        
        frame.getContentPane().setBackground(Color.PINK);
        frame.setSize(300,300);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
