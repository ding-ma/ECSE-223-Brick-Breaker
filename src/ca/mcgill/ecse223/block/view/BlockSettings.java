package ca.mcgill.ecse223.block.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlockSettings {
    public void BlockSettings(){
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        JButton BUpdate = new JButton();
        JButton BLevel = new JButton();

        label.setText("Update Block Screen");
        label.setBounds(0,0,400,50);
        frame.add(label);

        BUpdate.setText("Update Block Settings");
        BUpdate.setBounds(20,20,100,50);
        BUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              UpdateBlock updateBlock = new UpdateBlock();
              updateBlock.UpdateBlock();
            }
        });
        frame.add(BUpdate);

        BLevel.setText("Block Level Settings");
        BLevel.setBounds(200,20,100,50);
        BLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LevelScreen levelScreen = new LevelScreen();
                levelScreen.LevelScreen();
            }
        });
        frame.add(BLevel);

        frame.setSize(400,300);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
