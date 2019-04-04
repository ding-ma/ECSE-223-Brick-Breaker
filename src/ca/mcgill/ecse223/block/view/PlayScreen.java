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
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;

public class PlayScreen extends JFrame implements Block223PlayModeInterface {
	private static final long serialVersionUID = -613534727974834342L;
	JTextArea gameArea;
	Block223PlayModeExampleListener bp;
	JFrame frame = new JFrame();
	private HashMap<Integer,Integer> hof;
	private JList <String> hofList ;
	
	public PlayScreen() {
		
		genUI();
	}
		
	public void genUI() {
	   frame.setSize(700, 500);
	   frame.setLayout(null);
	   frame.setVisible(true);

	   //frame.setContentPane(new PlayScreen());
	   
	   JPanel p = new JPanel();
	   p.setBounds(500, 0, 200, 500);
	   p.setBackground(Color.LIGHT_GRAY);
	   
	   JPanel padd = new JPanel();
	   padd.setBounds(175, 380, 20, 10);
	   padd.setBackground(Color.RED);
	   
	   JPanel t = new JPanel();
	   t.setBounds(0,0,500,50);
	   frame.add(t);
	   JLabel title = new JLabel("Block 223!");
	   title.setFont(new Font("Verdana",1,20));
	   t.add(title);
	   t.setBorder(new LineBorder(Color.BLACK)); // make it easy to see
	   
	   
	  /*
	    String[] data = {"one", "two", "three", "four"};
        JList<String> myList = new JList<String>(data);
        myList.setSize(200,500);
        myList.setLocation(500, 0);*/
       
        //get
        frame.setVisible(true);
        
        
        //add list to panel 
        //p.add(myList); 
   
        frame.add(p);
        frame.add(padd);
       
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.addComponentsToPane();
        
	}


private void addComponentsToPane() {

	JButton button = new JButton("Start Game");

	gameArea = new JTextArea();
	gameArea.setEditable(false);
	JScrollPane scrollPane = new JScrollPane(gameArea);
	scrollPane.setPreferredSize(new Dimension(375, 125));

	getContentPane().add(scrollPane, BorderLayout.CENTER);
	getContentPane().add(button, BorderLayout.PAGE_END);

	button.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			button.setVisible(false);
			// initiating a thread to start listening to keyboard inputs
			bp = new Block223PlayModeExampleListener();
			Runnable r1 = new Runnable() {
				@Override
				public void run() {
					// in the actual game, add keyListener to the game window
					gameArea.addKeyListener(bp);
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

	// TODO Auto-generated method stub
	
}
}


/*public class Main extends JFrame {
    //frame settings
    int height =390;
    int width = 390;
    //paddle settings
    int xLength =100;
    int pHeight = 10;
    //paddle location
    int xLocation = 0;
    int yLocation = height-30;
    DrawPanel drawPanel = new DrawPanel();

    //constructor
    public Main(){
        //actions to move its position
        Action rightAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                if (xLocation<width-xLength)
                    //1px at the time
                    xLocation ++;
                //updates the paddle
                drawPanel.repaint();
            }
        };
        Action leftAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                if(xLocation>0)
                    //updating one pixel at the time
                    xLocation --;
                drawPanel.repaint();
            }
        };
        InputMap inputMap = drawPanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = drawPanel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        actionMap.put("rightAction", rightAction);
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        actionMap.put("leftAction", leftAction);
        add(drawPanel);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    //creates paddle object
    private class DrawPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.black);
            g.fillRect(xLocation, yLocation, xLength, pHeight);
        }
        public Dimension getPreferredSize() {
            return new Dimension(width, height);
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                new Main();
            }
        });
    }
}
Action rightAction = new AbstractAction(){
    public void actionPerformed(ActionEvent e) {
        
    }
};
Action leftAction = new AbstractAction(){
    public void actionPerformed(ActionEvent e) {
        
    }};
    
    drawPanel.setLocation(xLocation--, yLocation);s
*/
 
