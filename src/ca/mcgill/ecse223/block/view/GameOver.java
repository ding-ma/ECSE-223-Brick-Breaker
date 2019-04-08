package ca.mcgill.ecse223.block.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ca.mcgill.ecse223.block.controller.Block223Controller;


public class GameOver extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;
	private JFrame GameOver;
	private JLabel titleLost;
	private JButton returnToList;
	private JButton logout;
	private JButton logoutAndExit;

	
	private JLabel titleWon;
	private JButton replay;
	
	public void GameOverLost() {
		GameOver = new JFrame();
		titleLost = new JLabel();
		titleLost.setText("YOU LOST...");
		titleLost.setBounds(10, 0, 300, 50);
		titleLost.setFont (titleLost.getFont ().deriveFont (25.0f));
		titleLost.setForeground(Color.BLACK);
		
		returnToList = new JButton();
		returnToList.setBounds(100,100,200,50);
		returnToList.setText("Play Another Game");
		
		returnToList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				returnButtonActionPerformed(evt);
			}
		});
		
		logout = new JButton();
		logout.setBounds(100,200,200,50);
		logout.setText("Logout");
		
		logout.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logoutButtonActionPerformed(evt);
			}
		});
		
		logoutAndExit = new JButton();
		logoutAndExit.setBounds(100,300,200,50);
		logoutAndExit.setText("Logout and Exit Game");
		
		logoutAndExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logoutAndExitActionPerformed(evt);
			}
		});
		
		GameOver.setSize(390, 390);
		GameOver.setLayout(null);
		GameOver.add(titleLost);
		GameOver.add(returnToList);
		GameOver.add(logout);
		GameOver.add(logoutAndExit);
		GameOver.setVisible(true);

	}
	
	public void GameOverWon() {
		GameOver = new JFrame();
		titleWon = new JLabel();
		titleWon.setText("CONGRATULARIONS, YOU WON");
		titleWon.setBounds(10, 0, 390, 50);
		titleWon.setFont (titleWon.getFont ().deriveFont (25.0f));
		titleWon.setForeground(Color.BLACK);
		
		returnToList = new JButton();
		returnToList.setBounds(100,100,200,50);
		returnToList.setText("Play Another Game");
		
		returnToList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				returnButtonActionPerformed(evt);
			}
		});
		
		logout = new JButton();
		logout.setBounds(100,200,200,50);
		logout.setText("Logout");
		
		logout.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logoutButtonActionPerformed(evt);
			}
		});
		
		logoutAndExit = new JButton();
		logoutAndExit.setBounds(100,300,200,50);
		logoutAndExit.setText("Logout and Exit Game");
		
		logoutAndExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logoutAndExitActionPerformed(evt);
			}
		});
		
		
		GameOver.setSize(390, 390);
		GameOver.setLayout(null);
		GameOver.add(titleWon);
		GameOver.add(returnToList);
		GameOver.add(logout);
		GameOver.add(logoutAndExit);
		GameOver.setVisible(true);

	}
	
	private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {
		 PlayGame pg = new PlayGame();
		 pg.PlayGameScreen();
		}
	
	private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {
		 Block223Controller.logout();
		 Login lg = new Login();
		 lg.login();
		}
	
	private void logoutAndExitActionPerformed(java.awt.event.ActionEvent evt) {
		 Block223Controller.logout();
		 System.exit(0);
		}

	

}
