package ca.mcgill.ecse223.block.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MasterUI {
public MasterUI(){
    mainSettings();
    }

    Font font = new Font("Arial", Font.BOLD, 15);
    DeleteBlock DB = new DeleteBlock();
    CreateBlock CB = new CreateBlock();

    JFrame mainFrame = new JFrame();
    JButton deleteBlock = new JButton();
    JButton createBlock = new JButton();
    JLabel label = new JLabel();


public void mainSettings(){
		deleteBlock.setText("Delete Block");
		deleteBlock.setBounds(200,100,200,30);
		deleteBlock.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            DB.BlockDeletion();
        }
    });
		mainFrame.add(deleteBlock);

		createBlock.setText("CreateBlock");
		createBlock.setBounds(200,150,200,30);
		createBlock.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            CB.BlockCreation();
        }
    });
		mainFrame.add(createBlock);

		label.setText("Welcome to the setting screen, click on any of the buttons to change that setting");
		label.setBounds(100,50,500,40);
		label.setFont(font);
		mainFrame.add(label);

		mainFrame.setSize(800,600);
		mainFrame.setLayout(null);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


}
}