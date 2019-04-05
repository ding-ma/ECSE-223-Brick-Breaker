package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class BlockScreen {

	private static int SELECTEDBLOCK;
	private static int BLOCKID;

	private static String error = null;
	private static HashMap<Integer, Integer> availableBlocks;
	private static JLabel errorMessage;
	private static JButton createBlock;
	private static JButton updateBlock;
	private static JButton deleteBlock;

	private static JButton positionBlock;
	private static JButton removeBlock;
	//private static JButton updateLocation;

	private static JComboBox <String> availableBlocksList;
	private static JLabel AavailableBlocksLablel;
	private static JLabel blockScreen;
	private JLabel createdBlocks;
	private JFrame frame;

	public void BlockScreen() {
		error = null;
		frame = new JFrame();
		createdBlocks = new JLabel();
		errorMessage = new JLabel();
		createBlock = new JButton();
		updateBlock = new JButton();
		deleteBlock = new JButton();
		positionBlock = new JButton();
		removeBlock = new JButton();
		//updateLocation = new JButton();

		availableBlocksList = new JComboBox<String>(new String[0]);
		AavailableBlocksLablel = new JLabel();
		blockScreen = new JLabel();

		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(0, 200, 200, 50);

		blockScreen.setBounds(10, 0, 300, 50);
		blockScreen.setFont (blockScreen.getFont ().deriveFont (25.0f));
		blockScreen.setForeground(Color.BLACK);
		blockScreen.setText("Block Screen");

		availableBlocksList.setBounds(200,50,200,50);

		//first button:
		createBlock.setText("Create a Block");
		createBlock.setBounds(0, 50, 200, 50);
		createBlock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				error ="";
				AddBlock addBlock = new AddBlock();
				addBlock.AddBlock();

			}
		});

		//second button: //TODO ERROR
		updateBlock.setText("Update Block");
		updateBlock.setBounds(0, 100, 200, 50);
		updateBlock.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateBlockActionPerformed(evt);

			}				
		});

		//third button:
		deleteBlock.setText("Delete Block");
		deleteBlock.setBounds(200, 100, 200, 50);
		deleteBlock.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteBlockActionPerformed(evt);

			}				
		});

		//fourth button
		//TODO ERROR
		positionBlock.setText("Level Settings");
		positionBlock.setBounds(0, 150, 200, 50);
		positionBlock.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				levelActionPerformed(evt);
			}
		});

		//fifth button
		removeBlock.setText("Back");
		removeBlock.setBounds(200, 150, 200, 50);
		removeBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		//
		//		//sixth button
		//		updateLocation.setText("Update Grid Position");
		//		updateLocation.setBounds(125, 300, 200, 50);
		//		updateLocation.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				UpdateLocation updateLocation = new UpdateLocation();
		//				updateLocation.UpdateLocation();
		//			}
		//		});

		frame.setSize(400, 300);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);

		frame.add(errorMessage);
		frame.add(createBlock);
		frame.add(updateBlock);
		frame.add(deleteBlock);
		frame.add(availableBlocksList);
		frame.add(AavailableBlocksLablel);
		frame.add(blockScreen);
		frame.add(positionBlock);
		frame.add(removeBlock);
		//frame.add(updateLocation);
		refreshData();

	}
	public static void refreshData() {
		// error
		errorMessage.setText(error);
		availableBlocks = new HashMap<Integer, Integer>();
		availableBlocksList.removeAllItems();
		Integer index = 0;

		try {
			for (TOBlock block : Block223Controller.getBlocksOfCurrentDesignableGame()) {
				availableBlocks.put(index, block.getId());
				availableBlocksList.addItem(" Red Value: " + block.getRed()
				+ " Green Value: " + block.getGreen()
				+ " Blue Value: " + block.getBlue()
				+ " Points: " + block.getPoints());
				index ++;
			}
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}
	public static int getid() {
		return BLOCKID;
	}
	private void deleteBlockActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		int selectedBlock = availableBlocksList.getSelectedIndex();
		if (selectedBlock < 0)
			error = "A Block needs to be selected for deletion!";


		if (error.length() == 0) {
			int aID = availableBlocks.get(selectedBlock);
			try {
				Block223Controller.deleteBlock(aID);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}	

		}
		refreshData();
	}
	private void updateBlockActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		int selectedBlock = availableBlocksList.getSelectedIndex();
		if (selectedBlock < 0)
			error = "A Block needs to be selected!";
		refreshData();

		if (error == "" || error == null) {
			BLOCKID = availableBlocks.get(selectedBlock);
			UpdateBlock updateBlock = new UpdateBlock();
			updateBlock.UpdateBlock();
		}
	}
	private void levelActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		int selectedBlock = availableBlocksList.getSelectedIndex();
		if (selectedBlock < 0)
			error = "A Block needs to be selected!";
		refreshData();

		if (error == "" || error == null) {
			BLOCKID = availableBlocks.get(selectedBlock);
			PositionBlock positionBlock = new PositionBlock();
			positionBlock.PositionBlock();
		}
	}
}
