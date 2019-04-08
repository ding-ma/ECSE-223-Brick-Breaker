package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOPlayableGame;
import ca.mcgill.ecse223.block.model.PlayedGame;
import ca.mcgill.ecse223.block.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class PlayGame {
	private String error = null;
	private HashMap<Integer,String> availableGames;
	private JLabel errorMessage;
	private JButton playGame ;
	private JComboBox <String> availableGamesList ;
	private JComboBox <String> pausedGameList;
	private JLabel availableGamesLablel ;
	private JLabel gameScreen ;
	private JLabel selectGame;

    public void PlayGameScreen() {
    	 JFrame frame = new JFrame();

 		errorMessage = new JLabel();
 		playGame = new JButton();
		availableGamesList = new JComboBox<String>();
 		availableGamesLablel = new JLabel();
 		gameScreen = new JLabel();
 		selectGame = new JLabel();

 		gameScreen.setText("Welcome to Block223!");
 		gameScreen.setBounds(160, 0, 200, 50);
 		selectGame.setText("Select Game from menu below and press play to begin!");
 		selectGame.setBounds(60, 50, 390, 20);

 		availableGamesList.setBounds(125,150,200,50);
    	    
 
    	    errorMessage = new JLabel();
    		errorMessage.setForeground(Color.RED);
    		errorMessage.setBounds(125, 250, 200, 200);
    		
    		Player player = (Player) Block223Application.getCurrentUserRole();
    	    availableGames = new HashMap<Integer, String>();
    	    availableGamesList.removeAllItems();
    		Integer index = 0;
		try {
			for (TOPlayableGame game : Block223Controller.getPlayableGames()) {
				availableGames.put(index, game.getName());
				availableGamesList.addItem(game.getName());
				index ++;
			}
			for (PlayedGame pGame : player.getPlayedGames()) {
				availableGamesList.removeItem(pGame.getGame().getName());
			}
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		
        //first button:

    	    playGame.setText("Play");
    	    playGame.setBounds(100, 200, 200, 50);
    	    playGame.addActionListener(new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	        	GameScreen.testFlag = false;
    	        	String name = (String) availableGamesList.getSelectedItem();
                    //load the game at the level that the player last played it at -> 1 if they never played it before
    	        	try {
						Block223Controller.selectPlayableGame(name, -1);
						frame.dispose();
						 PlayScreen PS = new PlayScreen();
		                    PS.PlayScreen();
					} catch (InvalidInputException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                   

    	        }
    	    });

        
        //logout
        JButton logout = new JButton();
		logout.setBounds(100,300,200,50);
		logout.setText("Logout");
		
		logout.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Block223Controller.logout();
				frame.dispose();
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
    		frame.add(logout);
    		
    		
    		
    	  }




}
	