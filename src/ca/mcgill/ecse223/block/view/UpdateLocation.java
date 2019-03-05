package ca.mcgill.ecse223.block.view;

import java.awt.Color;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
public class UpdateLocation {

	
	private String error = null;
	private JLabel errorMessage;

	private JTextField levelField;
	private JTextField oldGridHorizontalField;
	private JTextField oldGridVerticalField;
	private JTextField gridHorizontalField;
	private JTextField gridVerticalField;

	private JLabel moveBlockLabel;

	private JLabel levelNumber;
	private JLabel oldGridHorizontalValue;
	private JLabel oldGridVerticalValue;
	private JLabel gridHorizontalValue;
	private JLabel gridVerticalValue;

	private JButton positionButton;
	private JFrame frame;
	public void UpdateLocation(){
		frame = new JFrame();
		
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(0, 250, 400, 200);
		
		moveBlockLabel = new JLabel();
		moveBlockLabel.setText("Move a block from one grid location to another in a level");
		moveBlockLabel.setBounds(80, 0, 450, 50);

		levelNumber = new JLabel();
		levelNumber.setText("Set the level: ");
		levelNumber.setBounds(0, 60,200,30);

		levelField = new JTextField();
		levelField.setBounds(200, 60, 200, 30);
		
		oldGridHorizontalValue = new JLabel();
		oldGridHorizontalValue.setText("Current horizontal position: ");
		oldGridHorizontalValue.setBounds(0, 115, 200, 30);

		oldGridHorizontalField = new JTextField();
		oldGridHorizontalField.setBounds(200, 115, 200, 30);

		oldGridVerticalValue = new JLabel();
		oldGridVerticalValue.setText("Current vertical position: ");
		oldGridVerticalValue.setBounds(0, 165, 200, 30);

		oldGridVerticalField = new JTextField();
		oldGridVerticalField.setBounds(200, 165, 200, 30);

		gridHorizontalValue = new JLabel();
		gridHorizontalValue.setText("New horizontal position: ");
		gridHorizontalValue.setBounds(0, 215,200,30);

		gridHorizontalField = new JTextField();
		gridHorizontalField.setBounds(200, 215, 200, 30);
		
		gridVerticalValue = new JLabel();
		gridVerticalValue.setText("New vertical position: ");
		gridVerticalValue.setBounds(0,265, 200, 30);

		gridVerticalField = new JTextField();
		gridVerticalField.setBounds(200,265,200,30);
 		
		positionButton = new JButton();
		positionButton.setBounds(135,320,200,50);
		positionButton.setText("Position");
		
		frame.setSize(550, 450);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.PINK);

		frame.add(errorMessage);
		
		frame.add(levelField);
		frame.add(gridHorizontalField);
		frame.add(gridVerticalField);
		
		frame.add(moveBlockLabel);
		
		frame.add(levelNumber);
		frame.add(gridHorizontalValue);
		frame.add(gridVerticalValue);
		frame.add(positionButton);
		frame.add(oldGridHorizontalField);
		frame.add(oldGridHorizontalValue);
		frame.add(oldGridVerticalField);
		frame.add(oldGridVerticalValue);

		
		positionButton.addActionListener(new ActionListener() {

						
			@Override
			public void actionPerformed(ActionEvent e) {
			int level = Integer.parseInt(levelField.getText());
			int newGridVerticalPosition = Integer.parseInt(gridVerticalField.getText());
			int newGridHorizontalPosition = Integer.parseInt(gridHorizontalField.getText());
			int oldGridHorizontalPosition = Integer.parseInt(oldGridHorizontalField.getText());
			int oldGridVerticalPosition = Integer.parseInt(oldGridVerticalField.getText());
			
				try {
					Block223Controller.moveBlock(level, oldGridHorizontalPosition, oldGridVerticalPosition, newGridHorizontalPosition, newGridVerticalPosition);;
					frame.dispose();
				} catch (Exception er) {
					er.printStackTrace();
				}
	
			}
		});
	}
}
