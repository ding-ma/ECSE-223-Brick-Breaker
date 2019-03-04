package ca.mcgill.ecse223.block.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelScreen {
    public void LevelScreen(){
        JFrame FGameScreem = new JFrame();
        JButton BDelete = new JButton();
        JButton BSettings = new JButton();
        JButton BAdd = new JButton();
        JLabel label = new JLabel();
        String names[]={"game name","number of level"};
        String data[][]={{"level 1","32"},
                {"level 2","12"}
        };
        JTable LTable = new JTable(data, names);
        JScrollPane scrollPane =  new JScrollPane(LTable);
        //TODO add column header name
        //TODO show all levels
        //TODO add action listeners to columns
        //TODO change column content to appropriate

        label.setText("Level Screen");
        label.setBounds(0,0,200,200);
        FGameScreem.add(label);

        BDelete.setText("Position a block at grid location in a level");
        BDelete.setBounds(20,20,100,50);
        BDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PositionBlock positionBlock = new PositionBlock();
                positionBlock.PositionBlock();
            }
        });
        FGameScreem.add(BDelete);

        BSettings.setText("Move a block from one grid location to another in a level");
        BSettings.setBounds(200,20,100,50);
        BSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MoveBlock moveBlock = new MoveBlock();
                moveBlock.MoveBlock();

            }
        });
        FGameScreem.add(BSettings);

        BAdd.setText("Remove Block From a Level");
        BAdd.setBounds(400,20,100,50);
        BAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveBlock removeBlock = new RemoveBlock();
                removeBlock.RemoveBlock();
            }
        });
        FGameScreem.add(BAdd);



        LTable.setBounds(0,200,800,600);
        JScrollPane sp = new JScrollPane(LTable);
        FGameScreem.add(scrollPane);
        FGameScreem.add(LTable);


        FGameScreem.setSize(800,600);
        FGameScreem.setLayout(null);
        FGameScreem.setVisible(true);
    }
    public void RefreshLevelScreen(){
        //TODO refresh
    }
}
