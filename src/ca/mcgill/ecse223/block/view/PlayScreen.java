package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.model.PlayedBlockAssignment;
import ca.mcgill.ecse223.block.model.PlayedGame;
import ca.mcgill.ecse223.block.model.PlayedGame.PlayStatus;
import javafx.scene.shape.Circle;
import ca.mcgill.ecse223.block.model.Block;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayScreen extends JFrame implements Block223PlayModeInterface {

	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 1000;
	private final int PLAY_AREA_WIDTH = 390;
	private final int PLAY_AREA_HEIGHT = 390;
	//	public PlayObjects playObjects;
	Graphics g;

	//	public PlayScreen() {
	//		createAndShowGUI();
	//	}
	public JLabel game_over;
	JTextArea pauseArea;
	StartPauseListener sp;

	public JLabel game_lives;
	public JLabel game_level;
	public JLabel game_score;
	//	public JLabel game_multiplier;

	JTextArea gameArea;
	private JFrame frame = new JFrame();
	private PlayObjects object = new PlayObjects();
	private PlayModeListener bp;
	public JButton startButton;

	public void PlayScreen() {

		//	add(object);
		setVisible(true);
		setSize(390,390);
		createAndShowGUI();
	}
	
	private void createAndShowGUI() {
		// Create and set up the window.
		this.setTitle("Block223 Play Mode");
		this.addComponentsToPane();
		this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		this.setBackground(Color.RED);
	
		//	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add components to window pane
		// Display the window.
		this.pack();
		this.setVisible(true);
	}

	@Override
	public String takeInputs() {
		if (bp == null) {
			return "";
		}
		return bp.takeInputs();
	}

	@Override
	public void refresh() {
		if (Block223Application.getCurrentPlayableGame() != null) {
			game_level.setText("Level: " + Block223Application.getCurrentPlayableGame().getCurrentLevel());
			game_score.setText("Score: " +  Block223Application.getCurrentPlayableGame().getScore());
			game_lives.setText("Lives Remaining: " + Block223Application.getCurrentPlayableGame().getLives());

			if(Block223Application.getCurrentPlayableGame().getLives() != 0 && Block223Application.getCurrentPlayableGame().getPlayStatus() == PlayStatus.GameOver) {
				game_over.setText("GAME WON");
				game_over.setForeground(Color.GREEN);
				startButton.setVisible(false);
				startButton.setEnabled(false);

			}
			if (Block223Application.getCurrentPlayableGame().getLives() == 0) {
				System.out.println("DONE");

				game_over.setText("GAME OVER");
				startButton.setVisible(false);
				startButton.setEnabled(false);
			} else {
				object.repaint();
			}

		}
	}
	private void addComponentsToPane() {

		// Game area key listener
		JPanel gameArea_panel = new JPanel();
		gameArea_panel.setLayout(new BorderLayout());
		gameArea_panel.setPreferredSize(new Dimension(FRAME_WIDTH, 100));

		// Top bar
		JPanel top_bar = new JPanel();
		top_bar.setLayout(new BorderLayout());
		top_bar.setPreferredSize(new Dimension(FRAME_WIDTH, 100));
		top_bar.setBackground(Color.LIGHT_GRAY);
		top_bar.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel welcome_text = new JLabel("WLECOME TO BLOCK223!");
		game_over = new JLabel("");
		game_over.setForeground(Color.red);
		game_over.setFont(new Font("Verdana",1, 25));
		top_bar.add(game_over);
		top_bar.add(welcome_text, BorderLayout.CENTER);
		welcome_text.setFont(new Font("Verdana",1,30));
		JButton back_btn = new JButton("Back");

		back_btn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backBtnActionPerformed(evt);
			}
		});

		// Playing Area
		JPanel playingArea = new JPanel();
		playingArea.setLayout(new BorderLayout());
		playingArea.setBackground(Color.GREEN);
		playingArea.setPreferredSize(new Dimension(390, 390));

		// Information Bar
		int font_size = 15;
		PlayedGame curr_game = Block223Application.getCurrentPlayableGame();
		JPanel information = new JPanel();
		information.setLayout(new BorderLayout());
		startButton = new JButton("Start");
		JPanel information_panel = new JPanel();
		information_panel.setLayout(new GridLayout(5, 1));
		information_panel.setBorder(new EmptyBorder(10, 20, 10, 20));
		information_panel.setBackground(Color.LIGHT_GRAY);
		information.setPreferredSize(new Dimension(300, 500));
		JLabel game_name = new JLabel("Current Game: " + curr_game.getGame().getName());
		game_name.setFont(new Font("Verdana", 1, font_size));
		game_level = new JLabel("Level: " + curr_game.getCurrentLevel());
		game_level.setFont(new Font("Verdana", 1, font_size));
		game_score = new JLabel("Score: " + curr_game.getScore());
		game_score.setFont(new Font("Verdana", 1, font_size));
		game_lives = new JLabel("Lives Remaining: " + curr_game.getLives());
		game_lives.setFont(new Font("Verdana", 1, font_size));
		information.setBackground(Color.LIGHT_GRAY);
		information.add(startButton, BorderLayout.PAGE_START);
		information_panel.add(game_name);
		information_panel.add(game_level);
		information_panel.add(game_score);
		information_panel.add(game_lives);
		information.add(information_panel, BorderLayout.CENTER);
		information.add(back_btn, BorderLayout.PAGE_END);

		// Bottom bar
		JPanel bottom_bar = new JPanel();
		bottom_bar.setPreferredSize(new Dimension(FRAME_WIDTH, 0));
		bottom_bar.setBackground(Color.BLUE);
		gameArea = new JTextArea();
		gameArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(object);
		scrollPane.setPreferredSize(new Dimension(FRAME_WIDTH, 100));
		bottom_bar.add(scrollPane);

		// Get Blocks Area
		object = new PlayObjects();
		object.setMinimumSize(new Dimension(PLAY_AREA_WIDTH, PLAY_AREA_HEIGHT));
		playingArea.add(object);
		//
		
		
		pauseArea = new JTextArea();
		pauseArea.setEditable(false);
		JScrollPane scrollPane2 = new JScrollPane(pauseArea);
		bottom_bar.add(scrollPane2);

		this.getContentPane().add(top_bar, BorderLayout.PAGE_START);
		this.getContentPane().add(information, BorderLayout.CENTER);
		this.getContentPane().add(playingArea, BorderLayout.WEST);
		//this.getContentPane().add(hofArea, BorderLayout.EAST);
		this.getContentPane().add(bottom_bar, BorderLayout.SOUTH);



		startButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startButton.setVisible(false);
				// initiating a thread to start listening to keyboard inputs

				bp = new PlayModeListener();
				sp = new StartPauseListener();
				Runnable r1 = new Runnable() {
					@Override
					public void run() {
						// in the actual game, add keyListener to the game window
						object.addKeyListener(bp);
						object.requestFocus();
						back_btn.addKeyListener(bp);
						object.addKeyListener(sp);
					}
				};
				Thread t1 = new Thread(r1);
				t1.start();
				// to be on the safe side use join to start executing thread t1 before executing
				// the next thread
				try {
					t1.join();
				} catch (InterruptedException e1) {
				}

				// initiating a thread to start the game loop
				Runnable r2 = new Runnable() {
					@Override
					public void run() {
						try {

							Block223Controller.startGame(PlayScreen.this);

								pauseArea.requestFocus();
						} catch (InvalidInputException e) {
						}
					}
				};
				Thread t2 = new Thread(r2);
				t2.start();
			}
		});
	}
	private class StartPauseListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int code = e.getKeyCode();
			if(code == KeyEvent.VK_SPACE) {
				Runnable r = new Runnable() {
					@Override
					public void run() {
						try {
							gameArea.requestFocus();
							Block223Controller.startGame(PlayScreen.this);
							pauseArea.requestFocus();
							System.out.println("run method");
						} catch (InvalidInputException x) {
						}

					}

				};
				Thread t = new Thread(r);
				t.start();		
			}	
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}
	public void backBtnActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}
}

