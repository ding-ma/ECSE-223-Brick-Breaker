package ca.mcgill.ecse223.block.view;


import ca.mcgill.ecse.btms.controller.BtmsController;
import ca.mcgill.ecse.btms.controller.TOBusVehicle;
import ca.mcgill.ecse223.block.controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class UpdateBlock extends JFrame {
	
	private JTextField updateupdateBlue;
	private JTextField updateupdateRed;
	private JTextField updateupdateGreen;
	private JTextField updateupdatePoints;

	private JLabel availableBlockstoggleLabel;
	private JLabel color;
	private JLabel updateBlue;
	private JLabel updateRed;
	private JLabel updateGreen;
	private JLabel updatePoints;
	
	private String error = null;
	
	private JComboBox<String> availableBlocksList;
	
	private HashMap<Integer, Integer> availableBlocks;

	private JButton updateBlockButton;
	
	//errorMessage
	private JLabel errorMessage;
	
		private void initComponents() {
			// elements for error message
			errorMessage = new JLabel();
			errorMessage.setForeground(Color.RED);
			
			// elements for update a Block
			updateupdateBlue = new JTextField();
			updateupdateRed = new JTextField();
			updateupdateGreen = new JTextField();
			updateupdatePoints = new JTextField();
			
			availableBlockstoggleLabel = new JLabel();
			availableBlockstoggleLabel.setText("Select A block");
			color = new JLabel();
			updateBlue = new JLabel();
			updateBlue.setText("Update the color blue");
			updateBlue = new JLabel ();
			updateBlue.setText("Update the color red");
			updateGreen = new JLabel();
			updateGreen.setText("Udate the color Green");
			updatePoints = new JLabel();
			updatePoints.setText("Update the value of points");
			
			availableBlocksList = new JComboBox<Integer>(new String[0]);
			 
			updateBlockButton = new JButton();
			updateBlockButton.setText("Update Block");
			
			// global settings
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setTitle("Update a Block");
			
			updateBlockButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					updateBlockButtonActionPerformed(evt);
				}
			});
			
			//TODO check lines 696 in BtmsPage:
			// availableBlocks - block
			availableBlocks = new HashMap<Integer, Integer>();
			availableBlocksList.removeAllItems();
			int index = 0;
			for (TOBlock block : Block223Controller.getBlocksOfCurrentDesignableGame()) {
				availableBlocks.put(index, block.getId());
				availableBlocksList.addItem(block.getId());
				index++;
			});
			
			
			
			
				private void updateBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
					error ="";
					int selectedBlock = availableBlocksList.getSelectedIndex();
					if (selectedBlock < 0)
						error = "Block needs to be selected to be updated!";
					if (error.length() == 0) {
						// call the controller
						try {
							Block223Controller.updateBlock(234, Integer.parseInt(updateupdateRed.getText()),Integer.parseInt(updateupdateGreen.getText()),Integer.parseInt(updateupdateBlue.getText()),
									Integer.parseInt(updateupdatePoints.getText()));
						} catch (InvalidInputException e) {
							error = e.getMessage();
						}
					}
					// update visuals
					refreshData();
				}
				}
			
			
			
		}
			
}
