package ca.mcgill.ecse223.block.view;

import java.awt.Color;

import javax.swing.*;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
public class PositionBlock {


	private String error = null;
	private JLabel errorMessage;

	private JTextField levelField;
	private JTextField gridHorizontalField;
	private JTextField gridVerticalField;

	private JLabel postionBlockLabel;

	private JLabel levelNumber;
	private JLabel gridHorizontalValue;
	private JLabel gridVerticalValue;

	private JButton positionButton;
	private JFrame frame;
	public void PositionBlock(){
		frame = new JFrame();
		
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(0, 380, 400, 30);
		
		postionBlockLabel = new JLabel();
		postionBlockLabel.setText("Position a block at grid location in a level");
		postionBlockLabel.setBounds(100, 0, 400, 50);
		
		levelField = new JTextField();
		levelField.setBounds(150, 80, 150, 30);
		
		gridHorizontalField = new JTextField();
		gridHorizontalField.setBounds(150, 160, 150, 30);
		
		gridVerticalField = new JTextField();
		gridVerticalField.setBounds(150,240,150,30);
 		
		levelNumber = new JLabel();
		levelNumber.setText("Set the level: ");
		levelNumber.setBounds(150, 40,200,50);
		
		gridHorizontalValue = new JLabel();
		gridHorizontalValue.setText("Set the horizontal grid position: ");
		gridHorizontalValue.setBounds(150, 120,240,50);
		
		gridVerticalValue = new JLabel();
		gridVerticalValue.setText("Set the vertical grid position: ");
		gridVerticalValue.setBounds(150,200, 240, 50);
		
		positionButton = new JButton();
		positionButton.setBounds(135,320,200,50);
		positionButton.setText("Position");
		

		positionButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				positionButtonActionPerformed(evt);
			}
		});
		
		

		frame.setSize(450, 450);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.PINK);

		frame.add(errorMessage);
		
		frame.add(levelField);
		frame.add(gridHorizontalField);
		frame.add(gridVerticalField);
		
		frame.add(postionBlockLabel);
		
		frame.add(levelNumber);
		frame.add(gridHorizontalValue);
		frame.add(gridVerticalValue);
		frame.add(positionButton);
	}
	
	private void refreshData() {
		// error
		errorMessage.setText(error);
		

	}
	
	private void positionButtonActionPerformed(java.awt.event.ActionEvent evt) {
		int id = BlockScreen.getid();
		
		
		String sLevel = levelField.getText();
		if(sLevel == null) {
			error = "level can't be empty. ";
			refreshData();
		}
		int level = Integer.parseInt(sLevel);
		
		String sGridHorizontalPosition = gridHorizontalField.getText();
		int gridHorizontalPosition = Integer.parseInt(sGridHorizontalPosition);
		
		String sGridVerticalPosition = gridVerticalField.getText();
		int gridVerticalPosition = Integer.parseInt(sGridVerticalPosition);
		
		
		try {
            Block223Controller.positionBlock(id, level, gridHorizontalPosition, gridVerticalPosition);

        }
        catch (InvalidInputException a){
          error =  a.getMessage();
          refreshData();
	}	
	if (error == null) {
		 frame.dispose();	
		 BlockScreen.refreshData();
		}
	}
}

