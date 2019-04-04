package ca.mcgill.ecse223.block.view;


import java.util.*; 


import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

import ca.mcgill.ecse223.block.controller.*;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.User;
import ca.mcgill.ecse223.block.model.UserRole;
import ca.mcgill.ecse223.block.application.*;
import ca.mcgill.*;



public class SignIn extends JFrame{
	private JTextField textPassword;
	private JTextField textUser;
	
	private String error = null;
	private JLabel errorMessage;

	private static final long serialVersionUID = -5307002073320579923L;



	public void SignIn(){
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(75, 250, 400, 200);
		
    	
        JFrame Fsignup = new JFrame();
    	JButton LoginButton = new JButton();
    	JButton Logout = new JButton();
    	
		Fsignup.add(errorMessage);
		Fsignup.add(Logout);

    	JLabel Username = new JLabel();
    	Username.setText("Username: ");
        Fsignup.add(Username); 
        Username.setBounds(150, 100, 200, 50);
        
        JLabel playPassword = new JLabel();
    	playPassword.setText("Password: ");
        Fsignup.add(playPassword); 
        playPassword.setBounds(150, 200, 200, 50);
        

        LoginButton.setBounds(250, 400, 200, 50);
        LoginButton.setText("Log In");
        
        Logout.setBounds(250, 600, 200, 50);
        
        
        
         textUser = new JTextField(20);
         textPassword = new JTextField(20);

        Fsignup.add(LoginButton);
        
        textUser.setBounds(250, 100, 200, 50);

        
        textPassword.setBounds(250, 200, 200, 50);

        
       
        
        Fsignup.add(textUser);
        Fsignup.add(textPassword);

        
        Fsignup.setSize(800,600);
        Fsignup.setLayout(null);
        Fsignup.setVisible(true);
        
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					LoginActionPerformed(evt);
				} catch (InvalidInputException e) {
					error = e.getMessage();
					errorRefresh();
					e.printStackTrace();
				}
			}
		});
        
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					LoginActionPerformed(evt);
				} catch (InvalidInputException e) {
					error = e.getMessage();
					errorRefresh();
					e.printStackTrace();
				}
			}
		});
        
	}
		public void refreshUserData(){
		//UserMode refresh
				TOUserMode gameMode = Block223Controller.getUserMode();
				
				if(gameMode.getMode() == TOUserMode.Mode.Play) {
					PlayScreen PS = new PlayScreen();
							PS.genUI();
				}
				
				
				if(gameMode.getMode() == TOUserMode.Mode.Design) {
					GameScreen BS = new GameScreen();
					BS.GameScreen();
				}
			
				
				if(gameMode.getMode() == TOUserMode.Mode.None) {
					Login lg = new Login();
					lg.login();

				}
		
			}
		
		 private void errorRefresh(){
				// error
	        	errorMessage.setText(error);
			}
		 
		 private void LoginActionPerformed(java.awt.event.ActionEvent evt) throws InvalidInputException{
	            
	            	 String aPassword = textPassword.getText();
	            	 String username = textUser.getText();
	            	 
					Block223Controller.login(username, aPassword);
					if(Block223Application.getCurrentUserRole() == null) {
						System.out.println("ok");
						error = "This user does not exist";
						errorRefresh();
					}
					if(error == null) {
						try {
							Block223Controller.login(username, aPassword);
						}
					 catch (InvalidInputException a){
				             error =  a.getMessage();
				             errorRefresh();
					 } 
					}
					
	                refreshUserData();
	            }
		 private void LogoutActionPerformed(java.awt.event.ActionEvent evt) throws InvalidInputException{
	            
        	Block223Controller.logout();
			
            refreshUserData();
        }
	        

		 

		
		
	
}