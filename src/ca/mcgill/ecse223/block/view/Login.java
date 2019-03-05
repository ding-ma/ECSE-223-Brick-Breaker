package ca.mcgill.ecse223.block.view;

import javax.swing.*;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    public void login(){
        JFrame Flogin = new JFrame();
        JButton Blogin = new JButton();
        //TODO Mairead

        Blogin.setBounds(100,200,200,50);
        Blogin.setText("Log In");
        Blogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                GameScreen GS = new GameScreen();
                //TODO check inputs
                GS.GameScreen();
                
                try {
                    
                    Block223Controller.register("user","pass","pass1");
                    Block223Controller.login("user", "pass1");
                    
                }
                catch (InvalidInputException a) {
                    a.printStackTrace();
                }
            }
        });
        Flogin.add(Blogin);

        Flogin.setSize(800,600);
        Flogin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Flogin.setLayout(null);
        Flogin.setVisible(true);
    }
}
