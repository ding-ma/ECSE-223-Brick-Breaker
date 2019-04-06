package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOCurrentBlock;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.BlockAssignment;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.model.PlayedBlockAssignment;

public class PlayScreen extends JPanel {
	JFrame frame = new JFrame();
	private HashMap<Integer,Integer> hof;
	private JList <String> hofList ;
	private JPanel paddle = new JPanel();

	
	public void PlayScreen() {
	
	   frame.setSize(700, 500);
	   frame.setLayout(null);
	   frame.setVisible(true);

	   //frame.setContentPane(new PlayScreen());
	   
	   JPanel p = new JPanel();
	   p.setBounds(390, 0, 310, 500);
	   p.setBackground(Color.LIGHT_GRAY);
	   
	   JPanel t = new JPanel();
	   //TODO GET THE ACTUAL DIMENSIONS OF THE GAME
	   t.setBounds(0,0,390,50);
	   frame.add(t);
	   JLabel title = new JLabel("Block 223!");
	   title.setFont(new Font("Verdana",1,20));
	   t.add(title);
	   t.setBorder(new LineBorder(Color.BLACK)); // make it easy to see
	   
	  
	    String[] data = {"one", "two", "three", "four"};
        JList<String> myList = new JList<String>(data);
        myList.setSize(200,500);
        myList.setLocation(500, 0);
       
        //get
        frame.setVisible(true);
        
        
        //add list to panel 
        p.add(myList);

        frame.add(p);
        
//        List<TOGridCell> gridCells = new ArrayList<TOGridCell>();
//        List<JPanel> rectangle = new ArrayList<JPanel>();
//        Level alevel = Block223Application.getCurrentGame().getLevel (1);
//		List<BlockAssignment> assignments = alevel.getBlockAssignments();
//        for (BlockAssignment assignment : assignments) {
//			JPanel to = new JPanel();
//			
//			JPanel to = new JPanel(assignment.getGridHorizontalPosition(), assignment.getGridVerticalPosition(),
//					assignment.getBlock().getId(), assignment.getBlock().getRed(), assignment.getBlock().getGreen(),
//					assignment.getBlock().getBlue(), assignment.getBlock().getPoints());
//			
//			to.setBounds(assignment.getGridHorizontalPosition(),assignment.getGridVerticalPosition(),Block.SIZE,Block.SIZE);
//			to.setBackground(new Color(assignment.getBlock().getRed(), assignment.getBlock().getBlue(), assignment.getBlock().getBlue()));
//			to.setSize(Block.SIZE, Block.SIZE);
//			frame.add(to);
//			rectangle.add(to);
			
//		}
        List<JPanel> rectangle = new ArrayList<JPanel>();
        for(int i=20 ; i <100 ; i = i +8) {
        	for(int j = 0; j<100; j = j+8) {
        	JPanel to = new JPanel();
        	to.setBounds(3 * j,3*i,Block.SIZE,Block.SIZE);
        	to.setSize(Block.SIZE, Block.SIZE);
        	to.setBackground(new Color(0,(int)(2.7*i),(int)(255-2.7*i)));
        	rectangle.add(to);
        }
        }
        for (JPanel re : rectangle) {
        	frame.add(re);
        }
	}
	

	}
	  
	

	    
	    
	    
	    
	    //display hall of fame
	    
	    
	    
    

 
