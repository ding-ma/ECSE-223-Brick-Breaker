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
	private String error = null;
	private JLabel errorMessage;

	private static final long serialVersionUID = -5307002073320579923L;



	public void SignIn(){
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(0, 250, 400, 200);
		
    	
        JFrame Fsignup = new JFrame();
    	JButton LoginButton = new JButton();
    	
		Fsignup.add(errorMessage);

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
        
        
        
        JTextField textUser = new JTextField(20);
        JTextField textPassword = new JTextField(20);

        Fsignup.add(LoginButton);
        
        textUser.setBounds(250, 100, 200, 50);

        
        textPassword.setBounds(250, 200, 200, 50);

        
       
        
        Fsignup.add(textUser);
        Fsignup.add(textPassword);

        
        Fsignup.setSize(800,600);
        Fsignup.setLayout(null);
        Fsignup.setVisible(true);

        
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 String aPassword = textPassword.getText();
            	 String username = textUser.getText();

            
            	 try {
					Block223Controller.login(username, aPassword);

				} catch (InvalidInputException e1) {
					error = "This user does not exist";
					// TODO Auto-generated catch block
					error += e1.getMessage();
					errorRefresh();
					
				}

                refreshUserData();
              
            
            }
            
            
        });
	}
		public void refreshUserData(){
		//UserMode refresh
				TOUserMode gameMode = Block223Controller.getUserMode();
				
				if(gameMode.getMode() == TOUserMode.Mode.Play) {
					PlayGame PS = new PlayGame();
							PS.PlayGameScreen();
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


		
		
	
}