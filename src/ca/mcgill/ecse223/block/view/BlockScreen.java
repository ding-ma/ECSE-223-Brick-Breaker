package ca.mcgill.ecse223.block.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;

public class BlockScreen {
    public void BlockScreen(){
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        JButton BAdd = new JButton();
        JButton BSettings = new JButton();
        JButton BDelete = new JButton();
        String names[]={"block name","number of blocks"};
        String data[][]={{"block1","32"},
                {"block2","12"}};
        JTable BTable = new JTable(data, names);
        JScrollPane sp = new JScrollPane(BTable);

        //TODO add column header name
        //TODO show all blocks
        //TODO add action listeners to columns
        //TODO change column content to appropriate

        label.setText("Block Screen");
        label.setBounds(0,0,200,50);
        frame.add(label);

        BAdd.setText("Create a Block");
        BAdd.setBounds(20,50,200,50);
        BAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBlock addBlock = new AddBlock();
                addBlock.AddBlock();
            }
        });
        frame.add(BAdd);

        BSettings.setText("Block Settings");
        BSettings.setBounds(300,50,200,50);
        BSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlockSettings blockSettings = new BlockSettings();
                blockSettings.BlockSettings();
            }
        });
        frame.add(BSettings);

        BDelete.setText("Delete Block");
        BDelete.setBounds(600,50,200,50);
        BDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteBlock deleteBlock = new DeleteBlock();
                deleteBlock.DeleteBlock();
            }
        });
        frame.add(BDelete);

        BTable.setBounds(0,200,800,600);
        frame.add(sp);
        frame.add(BTable);

        frame.setSize(900,600);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
