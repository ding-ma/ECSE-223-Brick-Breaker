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


	private static String error = null;
	private static HashMap<Integer,String> availableGames;
	private static JLabel errorMessage;
	private static JButton createGame ;
	private static JButton settingsBlock ;
	private static JButton deleteGame ;
	private static JComboBox <String> availableGamesList ;
	private static JLabel availableGamesLablel ;
	private static JLabel gameScreen ;
	private static JButton refresh ;

	public void GameScreen() {
		JFrame frame = new JFrame();

		errorMessage = new JLabel();
		createGame = new JButton();
		settingsBlock = new JButton();
		deleteGame = new JButton();
		availableGamesList = new JComboBox<String>(new String[0]);
		availableGamesLablel = new JLabel();
		gameScreen = new JLabel();
		refresh = new JButton();

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

		refresh.setText("Refresh!");
		refresh.setBounds(350, 20, 75, 30);
		frame.add(refresh);
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					refreshData();
				} catch (Exception a) {
					a.printStackTrace();
				}
			}
		});
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
				int selectedGame = availableGamesList.getSelectedIndex();
				if (selectedGame < 0)
					error = "A game needs to be selected!";
				String name = (String) availableGames.get(selectedGame);

				try {
					Block223Controller.selectGame(name);
				} catch (Exception err) {
					err.printStackTrace();
				}

				if (error.length() == 0) {
					GameSettings gameSettings = new GameSettings();
					gameSettings.GameSettings();
				}

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


		frame.setSize(450, 450);
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
	public static void refreshData() {
		// error
		errorMessage.setText(error);

		availableGames = new HashMap<Integer, String>();
		availableGamesList.removeAllItems();
		Integer index = 0;
		for (TOGame game : Block223Controller.getDesignableGames()) {
			availableGames.put(index, game.getName());
			availableGamesList.addItem("name" + game.getName());
			index ++;
		};

		error = "";
		availableGamesList.setSelectedIndex(-1);
	}

	private void deleteGameActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedGame = availableGamesList.getSelectedIndex();
		if (selectedGame < 0)
			error = "A game needs to be selected!";
		String name = (String) availableGames.get(selectedGame);
		if (error.length() == 0) {
			DeleteGame deleteGame = new DeleteGame();
			deleteGame.DeleteGame(name);
			/*try {
			Block223Controller.deleteGame(name);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}	*/

		}
		refreshData();
	}
}
