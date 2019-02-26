package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBlock extends  JFrame{
    public DeleteBlock(){

    }
    //TODO need to get blocks in the game
    //TODO need a way to display them on the screen
    //done with JtextField
    //TODO need a way to click on it and delete it
    //kinda done
    private JButton Deletes = new JButton();
    private JFrame Box = new JFrame();
    private JTextField blockselected = new JTextField();
    private JComboBox list = new JComboBox();
    private static Block223Controller allblock;

    public void DeleteComponents() {



        list.setBounds(100,100,200,50);
        list.addItem(allblock.getBlocksOfCurrentDesignableGame());
        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                list.getSelectedItem();
                blockselected.setText(""+list.getSelectedItem());
            }
        });
        Box.add(list);

        Deletes.setBounds(20,30,200,50);
        Deletes.setText("Delete Block");
        //   Deletes.setFont(main.font);
        Box.add(Deletes);
        Deletes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(list.getSelectedItem());

            }
        });

        blockselected.setEditable(false);
        blockselected.setVisible(true);
        blockselected.setBounds(200,200,200,50);
        blockselected.setBackground(Color.white);
        Box.add(blockselected);

        Box.setSize(800,800);
        Box.setLayout(null);
        Box.setVisible(true);
    }
}

