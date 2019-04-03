package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.*;
import javafx.scene.text.Font;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
	
	private String error = null;
	private JLabel errorMessage;

	private JTextField userNameField;
	private JTextField playerPasswordField;
	private JTextField adminPasswordField;

	private JLabel title;

	private JLabel userNameLabel;
	private JLabel playerPasswordLabel;
	private JLabel AdminPasswordLabel;
	
	private JButton register;
	private JButton login;
	
	private JFrame frame;
	private static final long serialVersionUID = -6987093818977061800L;

	public void login() {

		frame = new JFrame();
		
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(0, 170, 400, 200);
		
		title = new JLabel();
		title.setText("Welcome to Block223");
		title.setBounds(10, 0, 300, 50);
		title.setFont (title.getFont ().deriveFont (25.0f));
		title.setForeground(Color.BLACK);
		
		userNameLabel = new JLabel();
		userNameLabel.setText("Username: ");
		userNameLabel.setBounds(10, 50, 300, 50);
		userNameLabel.setFont (title.getFont ().deriveFont (15.0f));
		userNameLabel.setForeground(Color.BLACK);
 		
		userNameField = new JTextField();
		userNameField.setBounds(90, 60, 150, 30);
		
		playerPasswordLabel = new JLabel();
		playerPasswordLabel.setText("Player password: ");
		playerPasswordLabel.setBounds(10, 100, 300, 50);
		playerPasswordLabel.setFont (title.getFont ().deriveFont (15.0f));
		playerPasswordLabel.setForeground(Color.BLACK);
		
		playerPasswordField = new JTextField();
		playerPasswordField.setBounds(130, 110, 110, 30);
		
		AdminPasswordLabel = new JLabel();
		AdminPasswordLabel.setText("Admin password: ");
		AdminPasswordLabel.setBounds(10, 150, 300, 50);
		AdminPasswordLabel.setFont (title.getFont ().deriveFont (15.0f));
		AdminPasswordLabel.setForeground(Color.BLACK);
		
		adminPasswordField = new JTextField();
		adminPasswordField.setBounds(133, 160, 107, 30);
		
		register = new JButton();
		register.setBounds(10,200,100,50);
		register.setText("Register");
		
		login = new JButton();
		login.setBounds(140,200,100,50);
		login.setText("Login");
				
		//Visible:
		frame.setSize(350, 350);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);

		frame.add(errorMessage);
		frame.add(title);
		frame.add(userNameLabel);
		frame.add(userNameField);
		frame.add(playerPasswordLabel);
		frame.add(playerPasswordField);
		frame.add(AdminPasswordLabel);
		frame.add(adminPasswordField);
		
		frame.add(register);
		frame.add(login);
		
		register.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				registerButtonActionPerformed(evt);
			}
		});
		
		login.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loginButtonActionPerformed(evt);
			}
		});
		
	}
	private void refreshData() {
		// error
		errorMessage.setText(error);

	}
	
	private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		Block223Application.setCurrentUserRole(null);
		String userName = userNameField.getText();
		String adminPass = adminPasswordField.getText();
		String playerPassword = playerPasswordField.getText();
		
		try {
            
            Block223Controller.register(userName, playerPassword, adminPass);
		}
		catch (InvalidInputException e1) {
			error = e1.getMessage();
		}
        	refreshData();
        }
	
	private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		Block223Application.setCurrentUserRole(null);
		String userName = userNameField.getText();
		String adminPass = adminPasswordField.getText();

		try {
            
            Block223Controller.login(userName, adminPass);
		}
		catch (InvalidInputException e1) {
			error = e1.getMessage();
		}
        	refreshData();
        	if (error == "") {
        		GameScreen BS = new GameScreen();
				BS.GameScreen();
				}
        	}
        }
