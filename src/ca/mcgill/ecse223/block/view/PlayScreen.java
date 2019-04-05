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

public class PlayScreen extends JPanel {
	JFrame frame = new JFrame();
	private HashMap<Integer,Integer> hof;
	private JList <String> hofList ;

	
	public void PlayScreen() {
	
	   frame.setSize(700, 500);
	   frame.setLayout(null);
	   frame.setVisible(true);

	   //frame.setContentPane(new PlayScreen());
	   
	   JPanel p = new JPanel();
	   p.setBounds(500, 0, 200, 500);
	   p.setBackground(Color.LIGHT_GRAY);
	   
	   JPanel t = new JPanel();
	   t.setBounds(0,0,500,50);
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
	}
	  
	

	    
	    
	    
	    
	    //display hall of fame
	    
	    
	    
    
}
 
