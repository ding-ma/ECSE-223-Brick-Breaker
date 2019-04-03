package ca.mcgill.ecse223.block.view;


import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.model.Block223;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SignUp extends JFrame{
	
	private HashMap<String, String> currentUsers;
	Block223 block223 = Block223Application.getBlock223();

	private static final long serialVersionUID = -4706438351828664594L;

	
	public void SignUp(){
    	
        JFrame Fsignup = new JFrame();
    	JButton RegisterButton = new JButton();
    	
    	JLabel Username = new JLabel();
    	Username.setText("Username: ");
        Fsignup.add(Username); 
        Username.setBounds(150, 100, 200, 50);
        
        JLabel playPassword = new JLabel();
    	playPassword.setText("Player Password: ");
        Fsignup.add(playPassword); 
        playPassword.setBounds(100, 200, 200, 50);
        
        JLabel adPassword = new JLabel();
    	adPassword.setText("Admin Password: ");
        Fsignup.add(adPassword); 
        adPassword.setBounds(100, 300, 200, 50);

        RegisterButton.setBounds(250, 400, 200, 50);
        RegisterButton.setText("Register");
        Fsignup.add(RegisterButton);
        
        
        JTextField textUser = new JTextField(20);
        JTextField textPassword = new JTextField(20);
        JTextField textPasswordAdmin = new JTextField(20); 
        
       

        
        textUser.setBounds(250, 100, 200, 50);

        textPassword.setBounds(250, 200, 200, 50);

        textPasswordAdmin.setBounds(250, 300, 200, 50);
        textPasswordAdmin.setText("");

        
        Fsignup.add(textUser);
        Fsignup.add(textPassword); 
        Fsignup.add(textPasswordAdmin);
        
        Fsignup.setSize(800,600);
        Fsignup.setLayout(null);
        Fsignup.setVisible(true);
        
        RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	
            	String username = textUser.getText();

                String playerPassword = textPassword.getText();

                String adminPassword = textPasswordAdmin.getText();
               


                try {
                
                Block223Controller.register(username, playerPassword, adminPassword);
                Login lg = new Login();
                lg.login();
                   
                   
                } catch (InvalidInputException e1) {
                    e1.printStackTrace();
                }
                
                
      
                
            }
        });
        
	}
	
           
     
    }