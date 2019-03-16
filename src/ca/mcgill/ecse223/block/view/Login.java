package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.controller.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6987093818977061800L;

	public void login() {
        JFrame Flogin = new JFrame();
        JButton Blogin = new JButton();
        JButton BRegister = new JButton();
        JButton PlayAsAdmin = new JButton();


        Blogin.setBounds(100, 200, 200, 50);
        Blogin.setText("Log In");
        Blogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	SignIn sg = new SignIn();
            	sg.SignIn();
            }
            	
        });

        Flogin.add(Blogin);
        
        PlayAsAdmin.setBounds(100,400,200,50);
        PlayAsAdmin.setText("Play As Admin");
        PlayAsAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GameScreen gs = new GameScreen();
            	gs.GameScreen();
            	try {
					Block223Controller.register("pass","playPass","adminPass");
				} catch (InvalidInputException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	try {
					Block223Controller.login("pass", "adminPass");
				} catch (InvalidInputException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            	
        });
        Flogin.add(PlayAsAdmin);


        BRegister.setBounds(100, 300, 200, 50);
        BRegister.setText("Register");

        BRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp su = new SignUp();
                su.SignUp();
            }
        });

        Flogin.add(BRegister);


        Flogin.add(Blogin);

        Flogin.setSize(800, 600);
        Flogin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Flogin.setLayout(null);
        Flogin.setVisible(true);
    }
        
        
            
    	}