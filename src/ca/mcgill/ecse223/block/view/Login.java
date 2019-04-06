package ca.mcgill.ecse223.block.view;


import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOUserMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private String error;
	private JLabel errorMessage;

	private JTextField userNameField;
	private JPasswordField playerPasswordField;
	private JPasswordField adminPasswordField;

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
		userNameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userNameField.setText("");
			}
		});

		playerPasswordLabel = new JLabel();
		playerPasswordLabel.setText("Player password: ");
		playerPasswordLabel.setBounds(10, 100, 300, 50);
		playerPasswordLabel.setFont (title.getFont ().deriveFont (15.0f));
		playerPasswordLabel.setForeground(Color.BLACK);
		playerPasswordField = new JPasswordField();
		playerPasswordField.setBounds(130, 110, 110, 30);
		playerPasswordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playerPasswordField.setText("");
			}
		});

		AdminPasswordLabel = new JLabel();
		AdminPasswordLabel.setText("Admin password: ");
		AdminPasswordLabel.setBounds(10, 150, 300, 50);
		AdminPasswordLabel.setFont (title.getFont ().deriveFont (15.0f));
		AdminPasswordLabel.setForeground(Color.BLACK);
		adminPasswordField = new JPasswordField();
		adminPasswordField.setBounds(133, 160, 107, 30);
		adminPasswordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adminPasswordField.setText("");
			}
		});

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

	public void refreshUserData() {
		//UserMode refresh
		TOUserMode gameMode = Block223Controller.getUserMode();

		if (gameMode.getMode() == TOUserMode.Mode.Play) {
            PlayGame PS = new PlayGame();
            PS.PlayGameScreen();

		}


		if (gameMode.getMode() == TOUserMode.Mode.Design) {
			GameScreen BS = new GameScreen();
			BS.GameScreen();
		}


		if (gameMode.getMode() == TOUserMode.Mode.None) {
			System.out.println("error");

		}

	}

	private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		Block223Application.setCurrentUserRole(null);
		String userName = userNameField.getText();
		String playerPass = playerPasswordField.getText();
		String adminPass = adminPasswordField.getText();
		//TODO fix admin login
		//means its player
		if (adminPass.equals("")) {

			try {
				Block223Controller.login(userName, playerPass);
			} catch (InvalidInputException e1) {
				error = e1.getMessage();
			}
			refreshData();
			if (error.equals("")) {
				refreshUserData();
                /*PlayScreen playScreen = new PlayScreen();
                playScreen.genUI()*/
			}
		}
		//means its admin
		else if (playerPass.equals("")) {
			try {
				Block223Controller.login(userName, adminPass);
			} catch (InvalidInputException e1) {
				error = e1.getMessage();
			}
			refreshData();
			if (error.equals("")) {
				refreshUserData();

			} else {
				try {
					error += "Player Cannot Login as Admin.";
					throw new InvalidInputException("Player Cannot Login as Admin");

				} catch (InvalidInputException e) {
					e.printStackTrace();
				}
				refreshData();
			}
        } else {
			try {
				error += "Please only enter 1 password when logging in.";
				throw new InvalidInputException("Only enter 1 password");
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}
			refreshData();
		}
	}
}
