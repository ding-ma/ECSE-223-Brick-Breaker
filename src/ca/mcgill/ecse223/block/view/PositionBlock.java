package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.*;

public class PositionBlock {

	private static String error = null;
	private static JLabel errorMessage;
	
	//Labels
	private JLabel title;
	private JLabel level;
	private JLabel moveLevel;
	private JLabel horizontalGridPosition;
	private JLabel verticalGridPosition;
	private JLabel oldHorizontalGridPosition;
	private JLabel oldVerticalGridPosition;
	private JLabel newHorizontalGridPosition;
	private JLabel newVerticalGridPosition;
	
	//Fields
	private JTextField levelFild;
	private JTextField moveLeveFieldl;
	private JTextField horizontalGridPositionField;
	private JTextField verticalGridPositionField;
	private JTextField oldHorizontalGridPositionField;
	private JTextField oldVerticalGridPositionField;
	private JTextField newHorizontalGridPositionField;
	private JTextField newVerticalGridPositionField;
	

	//Buttons
	private JButton move;
	private JButton remove;
	private JButton position;
	private JFrame frame;
	
    public void PositionBlock(){
    	frame = new JFrame();
    	title = new JLabel();
    	
    	title.setBounds(10, 0, 300, 50);
    	title.setFont (title.getFont ().deriveFont (25.0f));
    	title.setForeground(Color.BLACK);
    	title.setText("Blocks in Levels Screen");
//TODO George
        

        frame.setSize(500,500);
        frame.add(title);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
