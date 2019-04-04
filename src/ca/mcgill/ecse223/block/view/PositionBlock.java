package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.*;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

public class PositionBlock {

	private static String error = null;
	private static JLabel errorMessage;

	//Labels
	private JLabel title;
	private JLabel level;
	private JLabel horizontalGridPosition;
	private JLabel verticalGridPosition;
	private JLabel newHorizontalGridPosition;
	private JLabel newVerticalGridPosition;

	//Fields
	private JTextField levelField;
	private JTextField horizontalGridPositionField;
	private JTextField verticalGridPositionField;
	private JTextField newHorizontalGridPositionField;
	private JTextField newVerticalGridPositionField;


	//Buttons
	private JButton move;
	private JButton remove;
	private JButton position;
	private JFrame frame;

	public void PositionBlock(){
		frame = new JFrame();
		title = new JLabel();

		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(0, 200, 400, 200);

		level = new JLabel();
		level.setText("Level: ");
		level.setBounds(10, 50, 300, 50);
		level.setFont (level.getFont ().deriveFont (15.0f));
		level.setForeground(Color.BLACK);

		horizontalGridPosition = new JLabel();
		horizontalGridPosition.setText("Horizontal Grid Position: ");
		horizontalGridPosition.setBounds(10, 100, 300, 50);
		horizontalGridPosition.setFont (level.getFont ().deriveFont (15.0f));
		horizontalGridPosition.setForeground(Color.BLACK);

		verticalGridPosition = new JLabel();
		verticalGridPosition.setText("Vertical Grid Position: ");
		verticalGridPosition.setBounds(10, 150, 300, 50);
		verticalGridPosition.setFont (level.getFont ().deriveFont (15.0f));
		verticalGridPosition.setForeground(Color.BLACK);

		newHorizontalGridPosition = new JLabel();
		newHorizontalGridPosition.setText("New Horizontal Grid Position: ");
		newHorizontalGridPosition.setBounds(10, 200, 300, 50);
		newHorizontalGridPosition.setFont (level.getFont ().deriveFont (15.0f));
		newHorizontalGridPosition.setForeground(Color.BLACK);

		newVerticalGridPosition = new JLabel();
		newVerticalGridPosition.setText("New Vertical Grid Position: ");
		newVerticalGridPosition.setBounds(10, 250, 300, 50);
		newVerticalGridPosition.setFont (level.getFont ().deriveFont (15.0f));
		newVerticalGridPosition.setForeground(Color.BLACK);

		levelField = new JTextField();
		levelField.setBounds(250, 60, 100, 30);

		horizontalGridPositionField = new JTextField();
		horizontalGridPositionField.setBounds(250, 110, 100, 30);

		verticalGridPositionField = new JTextField();
		verticalGridPositionField.setBounds(250, 160, 100, 30);

		newHorizontalGridPositionField = new JTextField();
		newHorizontalGridPositionField.setBounds(250, 210, 100, 30);

		newVerticalGridPositionField = new JTextField();
		newVerticalGridPositionField.setBounds(250, 260, 100, 30);

		position = new JButton();
		position.setText("Position Block");
		position.setBounds(360,75,100,40);
		position.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				positionButtonActionPerformed(evt);
			}
		});

		remove = new JButton();
		remove.setText("Remove Block");
		remove.setBounds(360,125,100,40);
		remove.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeButtonActionPerformed(evt);
			}
		});

		move = new JButton();
		move.setText("Move Block");
		move.setBounds(360,225,100,40);
		move.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				moveButtonActionPerformed(evt);
			}
		});
		

		title.setBounds(10, 0, 300, 50);
		title.setFont (title.getFont ().deriveFont (25.0f));
		title.setForeground(Color.BLACK);
		title.setText("Blocks in Levels Screen");


		frame.setSize(480,400);

		frame.add(errorMessage);
		frame.add(title);
		frame.add(level);
		frame.add(horizontalGridPosition);
		frame.add(verticalGridPosition);
		frame.add(newHorizontalGridPosition);
		frame.add(newVerticalGridPosition);

		frame.add(levelField);
		frame.add(horizontalGridPositionField);
		frame.add(verticalGridPositionField);
		frame.add(newHorizontalGridPositionField);
		frame.add(newVerticalGridPositionField);

		frame.add(move);
		frame.add(remove);
		frame.add(position);

		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	private void refreshData() {
		// error
		errorMessage.setText(error);
	}
	private void positionButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		int id = BlockScreen.getid();
		if (levelField.getText().equals("") ||  horizontalGridPositionField.getText().equals("")|| verticalGridPositionField.getText().equals("")) {
			error = "One or more of the fields are empty.";
			refreshData();
		}
		if (error == null || error == "") {
			String level = levelField.getText();
			int aLevel = Integer.parseInt(level);


			String horizontalGridPosition = horizontalGridPositionField.getText();
			int ahorizontalGridPosition = Integer.parseInt(horizontalGridPosition);


			String verticalGridPosition = verticalGridPositionField.getText();
			int averticalGridPosition = Integer.parseInt(verticalGridPosition);

			try {
				Block223Controller.positionBlock(id, aLevel,ahorizontalGridPosition, averticalGridPosition);

			}
			catch (InvalidInputException a){
				error =  a.getMessage();
				refreshData();
			} 
			if (error == null || error == "") {
				error = "done!";
				refreshData();
				BlockScreen.refreshData();
			}
		}
	}
	private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		int id = BlockScreen.getid();
		if (levelField.getText().equals("") ||  horizontalGridPositionField.getText().equals("")|| verticalGridPositionField.getText().equals("")) {
			error = "One or more of the fields are empty.";
			refreshData();
		}
		if (error == null || error == "") {
			String level = levelField.getText();
			int aLevel = Integer.parseInt(level);


			String horizontalGridPosition = horizontalGridPositionField.getText();
			int ahorizontalGridPosition = Integer.parseInt(horizontalGridPosition);


			String verticalGridPosition = verticalGridPositionField.getText();
			int averticalGridPosition = Integer.parseInt(verticalGridPosition);

			try {
				Block223Controller.removeBlock(aLevel, ahorizontalGridPosition,averticalGridPosition);

			}
			catch (InvalidInputException a){
				error =  a.getMessage();
				refreshData();
			} 
			if (error == null || error == "") {
				error = "done!";
				refreshData();
				BlockScreen.refreshData();
			}
		}
	}
	private void moveButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		int id = BlockScreen.getid();
		if (levelField.getText().equals("") ||  horizontalGridPositionField.getText().equals("")|| verticalGridPositionField.getText().equals("")||  newHorizontalGridPositionField.getText().equals("")|| newVerticalGridPositionField.getText().equals("")) {
			error = "One or more of the fields are empty.";
			refreshData();
		}
		if (error == null || error == "") {
			String level = levelField.getText();
			int aLevel = Integer.parseInt(level);


			String horizontalGridPosition = horizontalGridPositionField.getText();
			int ahorizontalGridPosition = Integer.parseInt(horizontalGridPosition);


			String verticalGridPosition = verticalGridPositionField.getText();
			int averticalGridPosition = Integer.parseInt(verticalGridPosition);
			
			String newhorizontalGridPosition = newHorizontalGridPositionField.getText();
			int anewhorizontalGridPosition = Integer.parseInt(newhorizontalGridPosition);


			String newverticalGridPosition = newVerticalGridPositionField.getText();
			int anewverticalGridPosition = Integer.parseInt(newverticalGridPosition);

			try {
				Block223Controller.moveBlock(aLevel, ahorizontalGridPosition,averticalGridPosition,anewhorizontalGridPosition,anewverticalGridPosition);

			}
			catch (InvalidInputException a){
				error =  a.getMessage();
				refreshData();
			} 
			if (error == null || error == "") {
				error = "done!";
				refreshData();
				BlockScreen.refreshData();
			}
		}

	}
}
