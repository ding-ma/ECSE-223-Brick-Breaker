package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.application.Block223Application;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.TOHallOfFame;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.model.PlayedBlockAssignment;
import ca.mcgill.ecse223.block.model.PlayedGame;
import ca.mcgill.ecse223.block.model.PlayedGame.PlayStatus;
import javafx.scene.shape.Circle;

import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.HallOfFameEntry;

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
	Graphics g;


	JTextArea pauseArea;
	StartPauseListener sp;

	public JTable hof_list;
	public JLabel game_lives;
	public JLabel game_level;
	public JLabel game_score;
	public JLabel game_over;

	JTextArea gameArea;
	JPanel hofPanel;
	
	private PlayObjects object = new PlayObjects();
	private PlayModeListener bp;
	public JButton startButton;

	public void PlayScreen() {
		setVisible(true);
		setSize(390,390);
		createAndShowGUI();
	}
	
	//general screen
	private void createAndShowGUI() {
		this.setTitle("Play");
		this.addComponentsToPane();
		this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		this.setBackground(Color.RED);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		if(Block223Application.getCurrentPlayableGame()!= null) {
			if(Block223Application.getCurrentPlayableGame().getPlayStatus() == PlayStatus.Moving) {
			List<HallOfFameEntry> entries = Block223Application.getCurrentPlayableGame().getGame().getHallOfFameEntries();
			ArrayList<HallOfFameEntry> entry = new ArrayList<HallOfFameEntry>();
			//List<HallOfFameEntry> entries =  Block223Application.getCurrentGame().getHallOfFameEntries();
			for (HallOfFameEntry h : entries) {
				entry.add(h);
			}
			sortEntries(entry);
			for(int i = 0; i < entry.size(); i++) {
				hof_list.setValueAt(entry.get(i).getPlayername(), i, 0);
				hof_list.setValueAt(entry.get(i).getScore(), i, 1);
			}	
			}
		}
		//info on the side
		if (Block223Application.getCurrentPlayableGame() != null) {
			game_level.setText("Level: " + Block223Application.getCurrentPlayableGame().getCurrentLevel());
			game_score.setText("Score: " +  Block223Application.getCurrentPlayableGame().getScore());
			game_lives.setText("Lives Remaining: " + Block223Application.getCurrentPlayableGame().getLives());
			
			if(Block223Application.getCurrentPlayableGame().getPlayStatus() == PlayStatus.Paused) {
				startButton.setVisible(true);

			}
			
			
			//if player wins game - Mairead
			if(Block223Application.getCurrentPlayableGame().getLives() != 0 && Block223Application.getCurrentPlayableGame().getPlayStatus() == PlayStatus.GameOver) {
				this.dispose();		
				if(GameScreen.testFlag == true) {
					System.out.println("testing");
					
				}
				else {
				GameOver gs = new GameOver();
				gs.GameOverWon();
				}
			}
			//if player loses game - George
			if (Block223Application.getCurrentPlayableGame().getLives() == 0) {
				this.dispose();
				if(GameScreen.testFlag == true) {
					System.out.println("testing");
				}
				else {
				GameOver gs = new GameOver();
				gs.GameOverLost();}
			} 
			//if player passes to the next level
			if(Block223Application.getCurrentPlayableGame().getLives() != 0 && Block223Application.getCurrentPlayableGame().getPlayStatus() == PlayStatus.Ready) {
				this.dispose();
				System.out.println(Block223Application.getCurrentPlayableGame().getCurrentLevel());
				//object.repaint();
				PlayScreen ps = new PlayScreen();
				ps.PlayScreen();
			}
			else {
				object.repaint();
			}

		}
	}
	private void addComponentsToPane() {
		
		//hall of fame
		hof_list = new JTable(31, 2);
		hof_list.setBackground(Color.lightGray);
		hof_list.setGridColor(Color.black);
		JScrollPane table_pane = new JScrollPane(hof_list);
		hof_list.setRowHeight(20);
		hof_list.setEnabled(false);
		hof_list.getColumnModel().getColumn(0).setHeaderValue("NAME");
		hof_list.getColumnModel().getColumn(1).setHeaderValue("SCORE");
		hofPanel = new JPanel();
		hofPanel.setLayout(new BorderLayout());
		hofPanel.setBackground(Color.LIGHT_GRAY);
		hofPanel.setPreferredSize(new Dimension(300, 500));
		hofPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		hofPanel.setEnabled(false);
		JLabel hof_text = new JLabel("Hall Of Fame: ");
		hof_text.setFont(new Font("Verdana", 1, 15));
		hofPanel.add(hof_text, BorderLayout.PAGE_START);
		hofPanel.add(table_pane, BorderLayout.CENTER);


		// Game area key listener
		JPanel gameArea_panel = new JPanel();
		gameArea_panel.setLayout(new BorderLayout());
		gameArea_panel.setPreferredSize(new Dimension(FRAME_WIDTH, 100));

		//Game set up - George
		JPanel top_bar = new JPanel();
		top_bar.setLayout(new BorderLayout());
		top_bar.setPreferredSize(new Dimension(FRAME_WIDTH, 100));
		top_bar.setBackground(Color.LIGHT_GRAY);
		top_bar.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel welcome_text = new JLabel("WELCOME TO BLOCK223!");
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

		// Information about the game so far - George and Ding
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

		//scrollpane
		JPanel bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(FRAME_WIDTH, 0));
		bottom.setBackground(Color.BLUE);
		gameArea = new JTextArea();
		gameArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(object);
		scrollPane.setPreferredSize(new Dimension(FRAME_WIDTH, 100));
		bottom.add(scrollPane);

		// Get Blocks Area
		object = new PlayObjects();
		object.setMinimumSize(new Dimension(PLAY_AREA_WIDTH, PLAY_AREA_HEIGHT));
		playingArea.add(object);
		//
		
		
		pauseArea = new JTextArea();
		pauseArea.setEditable(false);
		JScrollPane scrollPane2 = new JScrollPane(pauseArea);
		bottom.add(scrollPane2);

		this.getContentPane().add(top_bar, BorderLayout.PAGE_START);
		this.getContentPane().add(information, BorderLayout.CENTER);
		this.getContentPane().add(playingArea, BorderLayout.WEST);
		//checks if game is tester or actually being played - Mairead
		if(GameScreen.testFlag == false) {
		this.getContentPane().add(hofPanel, BorderLayout.EAST);
		}
		this.getContentPane().add(bottom, BorderLayout.SOUTH);


//Mairead
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
							//Mairead (fix)
							Block223Controller.startGame(PlayScreen.this);
								pauseArea.requestFocus();
								//Block223Application.getCurrentPlayableGame().pause();
						} catch (InvalidInputException e) {
						}
					}
				};
				Thread t2 = new Thread(r2);
				t2.start();
			}
		});
	}
	//Mairead
	private class StartPauseListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			/*int code = e.getKeyCode();
			if(code == KeyEvent.VK_SPACE) {
				Runnable r = new Runnable() {
					@Override
					public void run() {
						try {
							gameArea.requestFocus();
							Block223Controller.startGame(PlayScreen.this);
							System.out.println("pause space");
							
						} catch (InvalidInputException x) {
						}

					}

				};
				Thread t = new Thread(r);
				t.start();		
			}*/	
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}
//Mairead - updated
	public void backBtnActionPerformed(java.awt.event.ActionEvent evt) {
		if(Block223Application.getCurrentPlayableGame() != null) {
			Block223Application.getCurrentPlayableGame().pause();
			this.dispose();
			PlayGame pg = new PlayGame();
					pg.PlayGameScreen();
		}
		if(Block223Application.getCurrentPlayableGame() == null) {
		this.dispose();
		}
	}
//Mairead
	public void update(List<HallOfFameEntry> entry, int a, int b) {
		HallOfFameEntry temp = entry.get(a);
		HallOfFameEntry temp2 = entry.get(b);

		entry.set(b, temp);
		entry.set(a, temp2);
	}
//Mairead
	public void sortEntries(List<HallOfFameEntry> entry) {
		boolean complete = false;        
		while (complete == false){
			complete = true;     
			for (int i = 0; i < entry.size()-1; i++) {
				if (entry.get(i).getScore() < entry.get(i+1).getScore()) {
					update(entry, i, i+1);
					complete = false;  
				}
			}
		}
	}

	@Override
	public void endGame(int nrOfLives, TOHallOfFameEntry hof) {
		// TODO Auto-generated method stub
		
	}
}

