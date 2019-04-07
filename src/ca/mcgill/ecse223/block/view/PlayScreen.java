package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;


public class PlayScreen extends JFrame implements Block223PlayModeInterface{
	private static final long serialVersionUID = -613534727974834342L;

	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 1000;
	private final int PLAY_AREA_WIDTH = 390;
	private final int PLAY_AREA_HEIGHT = 390;
	
	public JButton button;
	
	public PlayUI playUI;

	public JFrame mainFrame = this;

	JTextArea gameArea;
	PlayListener bp;
	JPanel gamePanel;
	
	Graphics g;
	
	public PlayScreen() {
		createAndShowGUI();
	}
	
	private void createAndShowGUI() {
		// Create and set up the window.
		this.setTitle("Block223 PlayMode Example");
		this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add components to window pane
		this.addComponentsToPane();

		// Display the window.
		this.pack();
		this.setVisible(true);
	}

	private void addComponentsToPane() {
		JPanel gameArea_panel = new JPanel();
		gameArea_panel.setLayout(new BorderLayout());
		gameArea_panel.setPreferredSize(new Dimension(FRAME_WIDTH, 100));

		JButton button = new JButton("Start Game");
		JTextField title = new JTextField();
		Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 35);
		title.setText("Block223 Game");
		title.setFont(f2);
		

		gameArea = new JTextArea();
		gameArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(gameArea);
		scrollPane.setPreferredSize(new Dimension(FRAME_WIDTH, 100));
		PlayUI gameAreaView = new PlayUI();
		gameAreaView.setMinimumSize(new Dimension(PLAY_AREA_WIDTH, PLAY_AREA_HEIGHT));
		gameArea.add(gameAreaView);

		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.getContentPane().add(button, BorderLayout.PAGE_END);
		this.getContentPane().add(title, BorderLayout.PAGE_START);
		//getContentPane().setSize(390, 390);
		
		

		button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				button.setVisible(false);
			
				// initiating a thread to start listening to keyboard inputs
                bp = new PlayListener();

				Runnable r1 = new Runnable() {
					@Override
					public void run() {
						// in the actual game, add keyListener to the game window
						gameArea.addKeyListener(bp);
						gameArea.requestFocus();

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
							//button.setVisible(true);
							
						} catch (InvalidInputException e) {
						}
					}
				};
				Thread t2 = new Thread(r2);
				t2.start();
				//panel.repaint();
				
			}
		});
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
		//panel.setLocationPaddle((int)Block223Application.getCurrentPlayableGame().getCurrentPaddleX(),(int)Block223Application.getCurrentPlayableGame().getCurrentPaddleY());
		gameArea.repaint();
	}
	
	
	
	
	
		
		//panel.removeAll();
		/*gameArea.revalidate();
		gameArea.repaint();*/
		
	
	
	/*public int getPaddleX() {
		return paddleXPosition;
	}
	
	public void setPaddleX(int deltaX) {
		paddleXPosition+=deltaX;
		}*/
	/*public void paint(Graphics g) {
		System.out.println("UI is refreshing now...");
		
		Rectangle2D shape = new Rectangle2D.Float();
	      shape.setFrame(150, 150, 200,200);
	      Graphics2D g2 = (Graphics2D) g; 
	      g2.draw (shape);
	}*/
	
	
   
}
