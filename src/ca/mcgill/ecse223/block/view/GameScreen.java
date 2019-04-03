package ca.mcgill.ecse223.block.view;

import javax.swing.*;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import ca.mcgill.ecse223.block.*;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.*;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;



public class GameScreen {


	private static String error = null;
	private static HashMap<Integer,String> availableGames;
	private static JLabel errorMessage;
	private JButton createGame ;
	private JButton settingsBlock ;
	private JButton deleteGame ;
	private static JComboBox <String> availableGamesList ;
	private JLabel availableGamesLablel ;
	private JLabel gameScreen ;
	private JLabel gameNameLabel;
	private JTextField gameNameTextField;
	private JLabel availableGamesLabel;
	private JButton updateaGame;
	private JButton logout;
	private JFrame frame;

    public void GameScreen() {
    	frame = new JFrame();

 		errorMessage = new JLabel();
 		createGame = new JButton();
 		settingsBlock = new JButton();
 		updateaGame = new JButton();
 		deleteGame = new JButton();
 		availableGamesList = new JComboBox<String>(new String[0]);
 		availableGamesLablel = new JLabel();
 		gameScreen = new JLabel();
 		logout = new JButton();
 		

 		gameScreen.setBounds(10, 0, 300, 50);
		gameScreen.setFont (gameScreen.getFont ().deriveFont (25.0f));
		gameScreen.setForeground(Color.BLACK);
 		gameScreen.setText("Game Screen");

 		gameNameLabel = new JLabel();
		gameNameLabel.setText("Game Name: ");
		gameNameLabel.setBounds(10, 50, 300, 50);
		gameNameLabel.setFont (gameNameLabel.getFont ().deriveFont (15.0f));
		gameNameLabel.setForeground(Color.BLACK);
		
 		availableGamesLabel = new JLabel();
 		availableGamesLabel.setText("Created Games");
 		availableGamesLabel.setBounds(10, 100, 300, 50);
 		availableGamesLabel.setFont (gameNameLabel.getFont ().deriveFont (15.0f));
 		availableGamesLabel.setForeground(Color.BLACK);

 		availableGamesList.setBounds(125,100,180,50);
    	    
 
    	    errorMessage = new JLabel();
    		errorMessage.setForeground(Color.RED);
    		errorMessage.setBounds(0, 170, 400, 200);
    		
    		gameNameTextField = new JTextField();
    		gameNameTextField.setBounds(100, 60, 100, 30);
    		

    	    availableGames = new HashMap<Integer, String>();
    	    availableGamesList.removeAllItems();
    		Integer index = 0;
		try {
			for (TOGame game : Block223Controller.getDesignableGames()) {
				availableGames.put(index, game.getName());
				availableGamesList.addItem("name" + game.getName());
				index ++;
			}
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		;
    			
    		//first button:
    	    createGame.setText("Create a Game");
    	    createGame.setBounds(200,50,120,50);
    	    createGame.addActionListener(new ActionListener() {
    	        @Override
    	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
    				createGameButtonActionPerformed(evt);
    			}
    		});
    	    
    	    //second button:
    	    settingsBlock.setText("Block Settings");
    	    settingsBlock.setBounds(120, 150, 120, 50);
    	    settingsBlock.addActionListener(new ActionListener() {
    	        @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
    	        	blockSettingsActionPerformed(evt);
                }
            });  
    	    //third button:
    	    deleteGame.setText("Delete a Game");
    	    deleteGame.setBounds(240, 150, 120, 50);
    	    deleteGame.addActionListener(new java.awt.event.ActionListener() {
    			public void actionPerformed(java.awt.event.ActionEvent evt) {
    				deleteGameActionPerformed(evt);
    				
    			}				
    	    });
    	    
    	    logout.setText("logout");
    	    logout.setBounds(240, 200, 120, 50);
    	    logout.addActionListener(new java.awt.event.ActionListener() {
    			public void actionPerformed(java.awt.event.ActionEvent evt) {
    				logoutActionPerformed(evt);
    				
    			}				
    	    });
    	    
    	    updateaGame.setText("Update a Game");
    	    updateaGame.setBounds(0, 150, 120, 50);
    	    updateaGame.addActionListener(new java.awt.event.ActionListener() {
    			public void actionPerformed(java.awt.event.ActionEvent evt) {
    				updateGameActionPerformed(evt);
    				
    			}				
    	    });
				
    		frame.setSize(380, 380);
    	    frame.setLayout(null);
    	    frame.setVisible(true);
    	    frame.getContentPane().setBackground(Color.LIGHT_GRAY);
    	    
    	    errorMessage.setText(error);
    		
    		frame.add(errorMessage);
    		frame.add(createGame);
    		frame.add(settingsBlock);
    		frame.add(deleteGame);
    		frame.add(availableGamesList);
    		frame.add(availableGamesLablel);
    		frame.add(gameScreen);
    		frame.add(gameNameTextField);
    		frame.add(gameNameLabel);
    		frame.add(availableGamesLabel);
    		frame.add(updateaGame);
    		frame.add(logout);
    		refreshData();
    		
    		
    	    }
    public static void refreshData() {
		// error
		errorMessage.setText(error);
	    availableGamesList.removeAllItems();
		Integer index = 0;
	try {
		for (TOGame game : Block223Controller.getDesignableGames()) {
			availableGames.put(index, game.getName());
			availableGamesList.addItem(game.getName());
			index ++;
		}
	} catch (InvalidInputException e) {
		e.printStackTrace();
	}
	;
    }
    
    
    private void createGameButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		String name = gameNameTextField.getText();
		try {
			Block223Controller.createGame(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// update visuals
		refreshData();
	}
   private void blockSettingsActionPerformed(java.awt.event.ActionEvent evt) {
	   	error = "";
		int selectedGame = availableGamesList.getSelectedIndex();
		if (selectedGame < 0) 
		error = "A game needs to be selected before!";
		String name = (String) availableGames.get(selectedGame);
		if (error.length() == 0) {
		try {
			Block223Controller.selectGame(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}	
		
   }
		refreshData();
		if (error.length() == 0 || error == "") {
			 BlockScreen BS = new BlockScreen();
             BS.BlockScreen();		
		}
   }
   private void updateGameActionPerformed(java.awt.event.ActionEvent evt) {
	   	error = "";
		int selectedGame = availableGamesList.getSelectedIndex();
		if (selectedGame < 0) 
		error = "A game needs to be selected before!";
		String name = (String) availableGames.get(selectedGame);
		if (error.length() == 0) {
		try {
			Block223Controller.selectGame(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}	
		
  }
		refreshData();
		if (error.length() == 0 || error == "") {
			 UpdateGame BS = new UpdateGame();
            BS.UpdateGame();		
		}
   }
	private void deleteGameActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		int selectedGame = availableGamesList.getSelectedIndex();
		if (selectedGame < 0)
		 error = "A game needs to be selected for deletion!";
		String name = (String) availableGames.get(selectedGame);
		if (error.length() == 0) {
			try {
			Block223Controller.deleteGame(name);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}	

	}
		refreshData();
	}
	
	private void logoutActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		Block223Controller.logout();
		frame.dispose();

		
	}
}
    	
