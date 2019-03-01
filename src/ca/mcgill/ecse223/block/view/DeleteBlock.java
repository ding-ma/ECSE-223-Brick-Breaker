package ca.mcgill.ecse223.block.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ca.mcgill.ecse223.block.controller.*;

public class DeleteBlock {
    private JButton Deletes = new JButton();
    private JFrame Box = new JFrame();
    private JTextField blockselected = new JTextField();
    private JComboBox list = new JComboBox();

    public void DeleteBlock (){
        //TODO need to get blocks in the game
        //TODO need a way to display them on the screen
        //done with JtextField
        //TODO need a way to click on it and delete it
        //kinda done

            list.setBounds(100,100,200,50);
            for (int k=0; k<3; k++){ //100 will be the number of blocks or something close to that
                list.addItem("123");//this should be all the blocks
            }
            list.addItem("asd");
            list.addItem("jdps");
            list.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    list.getSelectedItem();
                    blockselected.setText(""+list.getSelectedItem());

                    int id;
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
                    Block223Controller block223Controller = new Block223Controller();
                    block223Controller.getCurrentDesignableGame().delete();

                    RefreshData refreshData = new RefreshData();
                    refreshData.RefreshData();

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
