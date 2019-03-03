package ca.mcgill.ecse223.block.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;
import java.util.HashMap;

import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.controller.*;

public class BlockScreen {
    private HashMap<Integer, Integer> availableBlocks;
    private JComboBox<String> blockList;


    public void BlockScreen() {
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        JButton BAdd = new JButton();
        JButton BSettings = new JButton();
        JButton BDelete = new JButton();
        blockList = new JComboBox<String>();
        availableBlocks = new HashMap<>();

        String names[] = {"game name"};
        String data[] = {"blocks,blocks2"};

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane();

        //TODO add column header name
        //TODO show all blocks
        //TODO add action listeners to columns
        //TODO change column content to appropriate



        table.setBounds(0, 170, 900, 600);
        frame.add(scrollPane);
        frame.add(table);

        label.setText("Welcome to the Block Interface");
        label.setBounds(10, 5, 200, 50);
        frame.add(label);

        BAdd.setText("Create a Block");
        BAdd.setBounds(20, 50, 200, 50);
        BAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBlock addBlock = new AddBlock();
                addBlock.AddBlock();

            }
        });
        frame.add(BAdd);

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
}
