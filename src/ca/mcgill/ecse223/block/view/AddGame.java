package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.controller.*;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.model.Game;

import javax.swing.*;
import java.util.ArrayList;

public class AddGame {
    public  void AddGame(){
        //TODO Yannick
        JFrame frame = new JFrame();
        JLabel label = new JLabel();

        label.setText("Define Game Settings");
        label.setBounds(0,0,400,50);
        frame.add(label);
        try{

            Block223Controller.createGame("abc1");
            Block223Controller.setGameDetails(20,20,20,
                    20,1,20,20);
        }
        catch (InvalidInputException a){
            a.printStackTrace();
        }


        frame.setSize(300,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    }
