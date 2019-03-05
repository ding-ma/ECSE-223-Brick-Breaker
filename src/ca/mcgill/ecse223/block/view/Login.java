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


        BRegister.setBounds(100, 400, 200, 50);
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
