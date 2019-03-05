package ca.mcgill.ecse223.block.view;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.*;

public class BlockScreen {
	
	private static int SELECTEDBLOCK;
	private static int BLOCKID;

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
	private static JButton updateLocation;

	private static JComboBox <String> availableBlocksList;
	private static JLabel AavailableBlocksLablel;
	private static JLabel blockScreen;


	public void BlockScreen() {
		JFrame frame = new JFrame();



		errorMessage = new JLabel();
		createBlock = new JButton();
		updateBlock = new JButton();
		deleteBlock = new JButton();
		positionBlock = new JButton();
		removeBlock = new JButton();
		updateLocation = new JButton();
		availableBlocksList = new JComboBox<String>(new String[0]);
		AavailableBlocksLablel = new JLabel();
		blockScreen = new JLabel();



		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(0, 380, 200, 50);

		blockScreen.setText("Block screen");
		blockScreen.setBounds(180, 0, 200, 50);

		availableBlocksList.setBounds(50,375,400,50);

		//first button:
		createBlock.setText("Create a Block");
		createBlock.setBounds(125, 50, 200, 50);
		createBlock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddBlock addBlock = new AddBlock();
				addBlock.AddBlock();
				refreshData();
			}
		});

		//second button: //TODO ERROR
		updateBlock.setText("Update Block");
		updateBlock.setBounds(125, 100, 200, 50);
		updateBlock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SELECTEDBLOCK = availableBlocksList.getSelectedIndex();
				BLOCKID = availableBlocks.get(SELECTEDBLOCK);
				if (SELECTEDBLOCK < 0)
					error = "Block needs to be selected!";
				refreshData();
				if (error == null) {
					UpdateBlock updateBlock = new UpdateBlock();
					updateBlock.UpdateBlock();
				}
			}
		});

		//third button:
		deleteBlock.setText("Delete Block");
		deleteBlock.setBounds(125, 150, 200, 50);
		deleteBlock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				SELECTEDBLOCK = availableBlocksList.getSelectedIndex();
				BLOCKID = availableBlocks.get(SELECTEDBLOCK);
				if (SELECTEDBLOCK < 0)
					error = "Block needs to be selected!";
				refreshData();
				if (error == null) {
					DeleteBlock deleteBlock = new DeleteBlock();
					deleteBlock.DeleteBlock();
				}
			}
		});

		//fourth button
		//TODO ERROR
		positionBlock.setText("Postition Block");
		positionBlock.setBounds(125, 200, 200, 50);
		positionBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PositionBlock positionBlock = new PositionBlock();
				positionBlock.PositionBlock();
			}
		});

		//fifth button
		removeBlock.setText("Remove Block");
		removeBlock.setBounds(125, 250, 200, 50);
		removeBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveBlock removeBlock = new RemoveBlock();
				removeBlock.RemoveBlock();
			}
		});

		//sixth button
		updateLocation.setText("Update Grid Position");
		updateLocation.setBounds(125, 300, 200, 50);
		updateLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateLocation updateLocation = new UpdateLocation();
				updateLocation.UpdateLocation();
			}
		});

		frame.setSize(550, 700);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.PINK);

		frame.add(errorMessage);
		frame.add(createBlock);
		frame.add(updateBlock);
		frame.add(deleteBlock);
		frame.add(availableBlocksList);
		frame.add(AavailableBlocksLablel);
		frame.add(blockScreen);
		frame.add(positionBlock);
		frame.add(removeBlock);
		frame.add(updateLocation);


	}
	public static void refreshData() {
		// error
		errorMessage.setText(error);
		availableBlocks = new HashMap<Integer, Integer>();
		availableBlocksList.removeAllItems();
		Integer index = 0;

		for (TOBlock block : Block223Controller.getBlocksOfCurrentDesignableGame()) {
			availableBlocks.put(index, block.getId());
			availableBlocksList.addItem(" Red Value: " + block.getRed()
					+ " Green Value: " + block.getGreen()
					+ " Blue Value: " + block.getBlue()
					+ " Points: " + block.getPoints());
			index ++;
		};
		availableBlocksList.setSelectedIndex(-1);
	}
	public static int getid() {
		return BLOCKID;
	}

}
