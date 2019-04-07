package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOPlayableGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class PlayGame {
	private String error = null;
	private static HashMap<Integer,String> availableGames;
	private JLabel errorMessage;
	private JButton playGame ;
	private JButton restartGame;
	private static JComboBox <String> availableGamesList ;
	private JComboBox <String> pausedGameList;
	private JLabel availableGamesLablel ;
	private JLabel gameScreen ;
	private JLabel selectGame;
	private JFrame frame;
	
    public void PlayGameScreen() {
    	 frame = new JFrame();

 		errorMessage = new JLabel();
 		playGame = new JButton();
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
    	    /*availableGames.put(0, "trial");
    	    availableGamesList.addItem("trial");*/
    		Integer index = 0;
		try {
			for (TOPlayableGame game : Block223Controller.getPlayableGames()) {
				availableGames.put(index, game.getName());
				availableGamesList.addItem("" + game.getName());
				index ++;
			}
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		

        //first button:

    	    playGame.setText("Play");
    	    playGame.setBounds(75, 80, 200, 50);
    	    playGame.addActionListener(new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	        	int selectedGame = availableGamesList.getSelectedIndex();
    	    		if (selectedGame < 0) {
    	    			System.out.println("A game needs to be selected for publication!");
    	        }
                    new PlayScreen();

    	      
    	        	String name = (String) availableGames.get(selectedGame);
    	    		try {
						Block223Controller.selectPlayableGame(name, selectedGame);

					} catch (InvalidInputException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}


                    //load the game at the level that the player last played it at -> 1 if they never played it before
                    //PS.PlayScreen();

    	        }
    	    });

        restartGame.setText("Restart");
        restartGame.setBounds(150, 80, 200, 50);
        restartGame.addActionListener(new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
                    //load the game at the level 1

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




}
	