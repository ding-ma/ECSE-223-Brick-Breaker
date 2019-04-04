package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import javax.swing.*;
import java.awt.*;



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
		errorMessage.setBounds(400, 400, 100, 100);
		
		
		gameNameTextField = new JTextField();
		gameNameTextField.setBounds(0, 90, 100, 30);
		
		gameNameLabel = new JLabel();
		gameNameLabel.setText("Game Name: ");
		gameNameLabel.setBounds(0, 20, 100, 100);
		
		createGame = new JButton();
		createGame.setText("Create");
		createGame.setBounds(0, 300, 200, 75);

        label.setText("Create a Game");
        label.setBounds(0,0,400,50);
        
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
        frame.setSize(900,600);
        frame.setLayout(null);
        frame.setVisible(true);
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
			Block223Controller.createGame(gameNameTextField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// update visuals
		refreshData();
	}
    }
