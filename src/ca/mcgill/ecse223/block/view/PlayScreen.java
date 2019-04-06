package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;


public class PlayScreen extends JPanel implements Block223PlayModeInterface{
    JFrame frame = new JFrame();
    private HashMap<Integer, Integer> hof;
   
    private volatile String userinputs = "lllrrr";
    //TODO change to game variables
    int paddleLength = 100;
    
    private JButton button;
	PlayListener bp;
	
	private PaddleUI paddle = new PaddleUI();
	
	public PaddleUI getPaddle() {
		return paddle;
	}


    public void PlayScreen() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button = new JButton();
        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        
        JPanel p = new JPanel();
        p.setBounds(500, 0, 200, 500);
        p.setBackground(Color.LIGHT_GRAY);
        /*paddle.setBackground(Color.black);
        paddle.setBounds(paddleXPosition,paddleYPosition,paddleLength,10);
        paddle.setVisible(true);*/
        
        button.setBounds(380, 380, 100, 30);
        button.setText("Start Game");
        button.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				button.setVisible(false);
				// initiating a thread to start listening to keyboard inputs
				bp = new PlayListener();
				Runnable r1 = new Runnable() {
					@Override
					public void run() {
						// in the actual game, add keyListener to the game window
						frame.addKeyListener(bp);
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
							button.setVisible(true);
						} catch (InvalidInputException e) {
						}
					}
				};
				Thread t2 = new Thread(r2);
				t2.start();
			}
		});

	

        //TODO start game method
       // Block223Controller.updatePaddlePosition(userinputs);

        
        frame.add(button, BorderLayout.PAGE_END);

        //hall of fame
        JPanel t = new JPanel();
        t.setBounds(0, 0, 500, 50);
        frame.add(t);
        JLabel title = new JLabel("Block 223!");
        title.setFont(new Font("Verdana", 1, 20));
        t.add(title);
        t.setBorder(new LineBorder(Color.BLACK)); // make it easy to see
        String[] data = {"one", "two", "three", "four"};
        JList<String> myList = new JList<String>(data);
        myList.setSize(200, 500);
        myList.setLocation(500, 0);

        //get
        frame.setVisible(true);


        //add list to panel 
        p.add(myList);

        frame.add(p);
    }
    
    @Override
    public void paint(Graphics g) {
        update(g);
    }

    @Override
    public void update(Graphics g) {
        //g.clearRect(0, 0, getWidth(), getHeight());
        paddle.paint(g);
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
	System.out.println("UI is refreshing now...");
}

    //display hall of fame


}


/* public static int getPaddleX(){
 return paddleXPosition;
}

public static void setPaddleX(int paddleX) {
 int paddleXPosition = paddleX;
 
 
}*/
 