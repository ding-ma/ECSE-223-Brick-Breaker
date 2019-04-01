package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOPlayableGame;

public class PlayGame {
	private String error = null;
	private HashMap<Integer,String> availableGames;
	private JLabel errorMessage;
	private JButton playGame ;
	private JButton resumeGame;
	private JButton restartGame;
	private JComboBox <String> availableGamesList ;
	private JComboBox <String> pausedGameList;
	private JLabel availableGamesLablel ;
	private JLabel gameScreen ;
	private JLabel selectGame;

    public void PlayGameScreen() {
    	 JFrame frame = new JFrame();

 		errorMessage = new JLabel();
 		playGame = new JButton();
 		resumeGame = new JButton();
 		restartGame = new JButton();
 		availableGamesList = new JComboBox<String>(new String[0]);
 		availableGamesLablel = new JLabel();
 		gameScreen = new JLabel();
 		selectGame = new JLabel();

 		gameScreen.setText("Welcome to Block223!");
 		gameScreen.setBounds(170, 0, 200, 50);
 		selectGame.setText("Select Game from menu below and press play to begin!");
 		selectGame.setBounds(50, 40, 400, 400);

 		availableGamesList.setBounds(125,150,200,50);
    	    
 
    	    errorMessage = new JLabel();
    		errorMessage.setForeground(Color.RED);
    		errorMessage.setBounds(125, 250, 200, 200);
    		
    		
    	    availableGames = new HashMap<Integer, String>();
    	    availableGamesList.removeAllItems();
    		Integer index = 0;
		try {
			for (TOPlayableGame game : Block223Controller.getPlayableGames()) {
				availableGames.put(index, game.getName());
				availableGamesList.addItem("name" + game.getName());
				index ++;
			}
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		;
    		
    	    
    		
    		//first button:
    	    playGame.setText("Play");
    	    playGame.setBounds(75, 80, 200, 50);
    	    playGame.addActionListener(new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	            System.out.println("ok");

    	        }
    	    });
    	    
    	    resumeGame.setText("Resume");
    	    resumeGame.setBounds(150, 80, 200, 50);
    	    playGame.addActionListener(new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	            System.out.println("ok");

    	        }
    	    });
    	    
    	   
				
    		frame.setSize(450, 450);
    	    frame.setLayout(null);
    	    frame.setVisible(true);
    	    
    	    errorMessage.setText(error);
    		
    		frame.add(errorMessage);
    		frame.add(playGame);
    		
    		frame.add(availableGamesList);
    		frame.add(availableGamesLablel);
    		frame.add(gameScreen);
    		frame.add(selectGame);
    		
    		
    		
    	  }
    private void refreshData() {
		// error
		errorMessage.setText(error);
    }


		}
	