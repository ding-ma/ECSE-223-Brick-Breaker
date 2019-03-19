package ca.mcgill.ecse223.block.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ca.mcgill.ecse223.block.controller.*;

public class DeleteBlock {
    private JLabel label = new JLabel();
    private JButton Deletes = new JButton();
    private JFrame Box = new JFrame();
    private JButton noDelete = new JButton();

    public void DeleteBlock() {
        label.setText("Are you sure you want to delete this block?");
        label.setBounds(25, 25, 400, 25);
        Box.add(label);

        Deletes.setBounds(120, 100, 200, 50);
        Deletes.setText("Delete Block");
        //   Deletes.setFont(main.font);
        Box.add(Deletes);
        Deletes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = BlockScreen.getid();
                try {
                    Block223Controller.deleteBlock(id);
                    BlockScreen.refreshData();
                } catch (InvalidInputException a) {
                    a.getMessage();

                }
                Box.dispose();
                try {
                    BlockScreen.refreshData();
                } catch (InvalidInputException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        noDelete.setText("Don't Delete Block");
        noDelete.setBounds(120, 200, 200, 50);
        noDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Box.dispose();
            }
        });

        Box.add(noDelete);
        Box.getContentPane().setBackground(Color.PINK);
        Box.setSize(450, 450);
        Box.setLayout(null);
        Box.setVisible(true);
    }
}
