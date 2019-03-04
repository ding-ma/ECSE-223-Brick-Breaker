package ca.mcgill.ecse223.block.view;

import java.util.*; 
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignUp{
	private JButton CreateButton = new JButton();
	
    public void signup(){
    	
        JFrame Fsignup = new JFrame();
        CreateButton.setBounds(250, 600, 200, 50);
        CreateButton.setText("Register");
        
        JTextField textUser = new JTextField(20);
      
        
        CreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SUsername = textUser.getText();
   
            }
        });

       
        Fsignup.add(CreateButton);
        
        textUser.setBounds(250, 100, 200, 50);
        textUser.setText("Enter Red Value");
        //    RedValue.setFont(ui.font);
        textUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textUser.setText("");
            }
        });
        Fsignup.add(textUser);
        
        Fsignup.setSize(800,600);
        Fsignup.setLayout(null);
        Fsignup.setVisible(true);
        
        
           

        
    }
}