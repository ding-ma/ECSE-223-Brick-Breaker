package ca.mcgill.ecse223.block.view;

import java.awt.Color;

import javax.swing.*;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import ca.mcgill.ecse223.block.*;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.*;



public class AddGame {

	private String error = null;
	private JLabel errorMessage;
	private JTextField gameNameTextField;
	private JLabel gameNameLabel;
	private JButton createGame;

	public  void AddGame(){
		JFrame frame = new JFrame();
		JLabel label = new JLabel();


		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(150, 150, 300, 20);
		
		
		gameNameTextField = new JTextField();
		gameNameTextField.setBounds(10, 90, 200, 30);

		gameNameLabel = new JLabel();
		gameNameLabel.setText("Game Name: ");
		gameNameLabel.setBounds(10, 20, 100, 100);

		createGame = new JButton();
		createGame.setText("Create");
		createGame.setBounds(10, 175, 150, 30);

		label.setText("Create a Game");
		label.setBounds(5,5,400,50);

		createGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addGameButtonActionPerformed(evt);
			}
		});

		frame.add(label);
		frame.add(errorMessage);
		frame.add(gameNameLabel);
		frame.add(gameNameTextField);
		frame.add(createGame);
		frame.setSize(350,350);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.PINK);
	}

	private void refreshData() {
		// error
		errorMessage.setText(error);
		}
		
    private void addGameButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message
			error = null;
		// call the controller
		try {
			String name = gameNameTextField.getText();
			Block223Controller.createGame(name);
			UpdateGame gameSettings = new UpdateGame();
			gameSettings.UpdateGame();

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// update visuals
		refreshData();
	}
}