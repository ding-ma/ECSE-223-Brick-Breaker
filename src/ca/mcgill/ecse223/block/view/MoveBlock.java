package ca.mcgill.ecse223.block.view;

import java.awt.Color;

import javax.swing.*;
public class MoveBlock {


	private String error = null;
	private JLabel errorMessage;

	private JTextField levelField;
	private JTextField gridHorizontalField;
	private JTextField gridVerticalField;

	private JLabel moveBlockLabel;

	private JLabel levelNumber;
	private JLabel gridHorizontalValue;
	private JLabel gridVerticalValue;

	private JButton positionButton;
	private JFrame frame;
	public void PositionBlock(){
		frame = new JFrame();
		
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(0, 250, 400, 200);
		
		moveBlockLabel = new JLabel();
		moveBlockLabel.setText("Move a block from one grid location to another in a level");
		moveBlockLabel.setBounds(100, 0, 400, 50);
		
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
		gridHorizontalValue.setText("Set the horizontal position: ");
		gridHorizontalValue.setBounds(150, 120,200,50);
		
		gridVerticalValue = new JLabel();
		gridVerticalValue.setText("Set the vertical position: ");
		gridVerticalValue.setBounds(150,200, 200, 50);
		
		positionButton = new JButton();
		positionButton.setBounds(135,320,200,50);
		positionButton.setText("Position");
		

		frame.setSize(450, 450);
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
	}
}
