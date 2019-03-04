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

	private static String error = null;
	private static HashMap<Integer, Integer> availableBlocks;
	private static JLabel errorMessage;
	private static JButton createBlock;
	private static JButton settingsBlock;
	private static JButton deleteBlock;
	private static JComboBox <String> availableBlocksList;
	private static JLabel AavailableBlocksLablel;
	private static JLabel blockScreen;


	public void BlockScreen() {
		JFrame frame = new JFrame();
		


		errorMessage = new JLabel();
		createBlock = new JButton();
		settingsBlock = new JButton();
		deleteBlock = new JButton();
		availableBlocksList = new JComboBox<String>(new String[0]);
		AavailableBlocksLablel = new JLabel();
		blockScreen = new JLabel();



		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(125, 250, 200, 200);

		blockScreen.setText("Block screen");
		blockScreen.setBounds(180, 0, 200, 50);

		availableBlocksList.setBounds(125,200,200,50);

		//first button:
		createBlock.setText("Create a Block");
		createBlock.setBounds(125, 50, 200, 50);
		createBlock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddBlock addBlock = new AddBlock();
				addBlock.AddBlock();

			}
		});

		//second button:
		settingsBlock.setText("Block/Level Settings");
		settingsBlock.setBounds(125, 100, 200, 50);
		settingsBlock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BlockSettings blockSettings = new BlockSettings();
				blockSettings.BlockSettings();

			}
		});

		//third button:
		deleteBlock.setText("Delete Block");
		deleteBlock.setBounds(125, 150, 200, 50);
		deleteBlock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteBlock deleteBlock = new DeleteBlock();
				deleteBlock.DeleteBlock();

			}
		});
		
		frame.setSize(450, 450);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.PINK);

		frame.add(errorMessage);
		frame.add(createBlock);
		frame.add(settingsBlock);
		frame.add(deleteBlock);
		frame.add(availableBlocksList);
		frame.add(AavailableBlocksLablel);
		frame.add(blockScreen);


	}
	public static void refreshData() {
		// error
		errorMessage.setText(error);
		
		availableBlocks = new HashMap<Integer, Integer>();
		availableBlocksList.removeAllItems();
		Integer index = 0;
		for (TOBlock block : Block223Controller.getBlocksOfCurrentDesignableGame()) {
			availableBlocks.put(index, block.getId());
			availableBlocksList.addItem("#" + block.getId()
			+ "Red Value: " + block.getRed()
			+ "Green Value: " + block.getGreen()
			+ "Blue Value: " + block.getBlue()
			+ "Points: " + block.getPoints());
			index ++;
		};
		availableBlocksList.setSelectedIndex(-1);
	}


	}








/*    JFrame frame = new JFrame();
        JLabel label = new JLabel();
        JButton createBlock = new JButton();
        JButton BSettings = new JButton();
        JButton BDelete = new JButton();
        blockList = new JComboBox<String>();
        availableBlocks = new HashMap<>();


       // String names[] = {"block name", "number of blocks"};



        //TODO add column header name
        //TODO show all blocks
        //TODO add action listeners to columns
        //TODO change column content to appropriate

        label.setText("Block Screen");
        label.setBounds(400, 0, 200, 50);
        frame.add(label);

        createBlock.setText("Create a Block");
        createBlock.setBounds(20, 50, 200, 50);
        createBlock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBlock addBlock = new AddBlock();
                addBlock.AddBlock();

            }
        });
        frame.add(createBlock);

        BSettings.setText("Block Settings");
        BSettings.setBounds(300, 50, 200, 50);
        BSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlockSettings blockSettings = new BlockSettings();
                blockSettings.BlockSettings();

            }
        });
        frame.add(BSettings);

        BDelete.setText("Delete Block");
        BDelete.setBounds(600, 50, 200, 50);
        BDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteBlock deleteBlock = new DeleteBlock();
                deleteBlock.DeleteBlock();

            }
        });
        frame.add(BDelete);



        frame.setSize(900, 600);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private HashMap<Integer, TOBlock> blocks;

    public void RefreshBlockScreen() {

        JLabel errorMessage = new JLabel();
        JTextField blockTextField = new JTextField();

        int index = 1;
        for (TOBlock block : Block223Controller.getBlocksOfCurrentDesignableGame()) {
            availableBlocks.put(index, block.getId());
            blockList.addItem("#" + block.getId()
                    + "Red Value: " + block.getRed()
                    + "Green Value: " + block.getGreen()
                    + "Blue Value: " + block.getBlue()
                    + "Points: " + block.getPoints());
            index ++;
        }
        }
        }*/

