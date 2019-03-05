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



public class GameScreen {


	private String error = null;
	private HashMap<Integer,String> availableGames;
	private JLabel errorMessage;
	private JButton createGame ;
	private JButton settingsBlock ;
	private JButton deleteGame ;
	private JComboBox <String> availableGamesList ;
	private JLabel availableGamesLablel ;
	private JLabel gameScreen ;

    public void GameScreen() {
    	 JFrame frame = new JFrame();

 		errorMessage = new JLabel();
 		createGame = new JButton();
 		settingsBlock = new JButton();
 		deleteGame = new JButton();
 		availableGamesList = new JComboBox<String>(new String[0]);
 		availableGamesLablel = new JLabel();
 		gameScreen = new JLabel();

 		gameScreen.setText("Game screen");
 		gameScreen.setBounds(180, 0, 200, 50);

 		availableGamesList.setBounds(125,200,200,50);
    	    
 
    	    errorMessage = new JLabel();
    		errorMessage.setForeground(Color.RED);
    		errorMessage.setBounds(125, 250, 200, 200);
    		
    		

    	    availableGames = new HashMap<Integer, String>();
    	    availableGamesList.removeAllItems();
    		Integer index = 0;
    		for (TOGame game : Block223Controller.getDesignableGames()) {
    			availableGames.put(index, game.getName());
    			availableGamesList.addItem("name" + game.getName());
    			index ++;
    		};
    		
    	    
    		
    		//first button:
    	    createGame.setText("Create a Game");
    	    createGame.setBounds(125, 50, 200, 50);
    	    createGame.addActionListener(new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	            AddGame addGame = new AddGame();
    	           	addGame.AddGame();

				}
    	    });
    	    
    	    //second button:
    	    settingsBlock.setText("Game Settings");
    	    settingsBlock.setBounds(125, 100, 200, 50);
    	    settingsBlock.addActionListener(new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	            GameSettings gameSettings = new GameSettings();
    	            gameSettings.GameSettings();

    	        }
    	    });
    	    
    	    //third button:
    	    deleteGame.setText("Delete a Game");
    	    deleteGame.setBounds(125, 150, 200, 50);
    	    deleteGame.addActionListener(new java.awt.event.ActionListener() {
    			public void actionPerformed(java.awt.event.ActionEvent evt) {
    				deleteGameActionPerformed(evt);
    				
    			}
	
				
    	    });
				
  
    		frame.setSize(600, 700);
    	    frame.setLayout(null);
    	    frame.setVisible(true);
    	    
    	    errorMessage.setText(error);
    		
    		frame.add(errorMessage);
    		frame.add(createGame);
    		frame.add(settingsBlock);
    		frame.add(deleteGame);
    		frame.add(availableGamesList);
    		frame.add(availableGamesLablel);
    		frame.add(gameScreen);
    		
    		
    		
    	    }
    private void refreshData() {
		// error
		errorMessage.setText(error);
    }

	private void deleteGameActionPerformed(java.awt.event.ActionEvent evt) {
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
    	}

/*
        JFrame FGameScreem = new JFrame();
        JButton BDelete = new JButton();
        JButton BSettings = new JButton();
        JButton BAdd = new JButton();
        JButton BLogout = new JButton();
        JLabel label = new JLabel();
        JButton BSave = new JButton();
        JComboBox <String> availableGames = new JComboBox<String>(new String[0]);

        String names[] = {"game name", "number of level"};
        String data[][] = {{"game 1", "32"},
                {"game 2", "12"}
        };
        JTable GameTable = new JTable(data, names);
        JScrollPane sp = new JScrollPane(GameTable);

        FGameScreem.add(availableGames);       
        //TODO add column header name
        //TODO show all games
        //TODO add action listeners to columns
        //TODO change column content to appropriate

        label.setText("Game screen");
        label.setBounds(0, 0, 200, 200);
        FGameScreem.add(label);

        BDelete.setText("Delete Game");
        BDelete.setBounds(20, 20, 100, 50);
        BDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteGame deleteGame = new DeleteGame();
                deleteGame.DeleteGame("game1");
            }
        });
        FGameScreem.add(BDelete);

        BSettings.setText("Settings");
        BSettings.setBounds(200, 20, 100, 50);
        BSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameSettings GS = new GameSettings();
                GS.GameSettings();
            }
        });
        FGameScreem.add(BSettings);

        BAdd.setText("Add Game");
        BAdd.setBounds(400, 20, 100, 50);
        BAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddGame addGame = new AddGame();
                addGame.AddGame();
            }
        });
        FGameScreem.add(BAdd);

        BLogout.setText("Logout");
        BLogout.setBounds(500, 300, 100, 50);
        BLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO log out @Mairead
                System.exit(0);
            }
        });
        FGameScreem.add(BLogout);


        GameTable.setBounds(0, 200, 800, 600);

        FGameScreem.add(sp);
        FGameScreem.add(GameTable);
        BSave.setText("Save Game");
        BSave.setBounds(550, 20, 100, 50);
        BSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO save game @Mairead
            }
        });
        FGameScreem.add(BSave);

        FGameScreem.setSize(800, 600);
        FGameScreem.setLayout(null);
        FGameScreem.setVisible(true);
    }

    private HashMap<Integer, String> availableGames;
    private JComboBox<String> gameList;

    public void RefreshGameSCreen() {
        //TODO refresh
        JLabel errorMessage = new JLabel();
        JTextField blockTextField = new JTextField();

        int index = 1;
        for (TOGame game : Block223Controller.getDesignableGames()) {
            availableGames.put(index, game.getName());
            gameList.addItem("#" + index + "Game Name: " + game.getName());
            index++;
        }
    }
}
 */