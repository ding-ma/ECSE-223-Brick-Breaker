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
    private JLabel label = new JLabel();

    public void DeleteBlock (){
        //TODO need to get blocks in the game
        //TODO need a way to display them on the screen
        //done with JtextField
        //TODO need a way to click on it and delete it
        //kinda done
        label.setText("Block to be deleted: ");
        label.setBounds(150,25,200,25);
        Box.add(label);

            list.setBounds(150,125,200,50);
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

            Deletes.setBounds(150,400,200,50);
            Deletes.setText("Delete Block");
            //   Deletes.setFont(main.font);
            Box.add(Deletes);
            Deletes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(list.getSelectedItem());
                    Block223Controller block223Controller = new Block223Controller();
                    block223Controller.getCurrentDesignableGame().delete();

                    //TODO ADD REFRESH
                }
            });
            blockselected.setEditable(false);
            blockselected.setVisible(true);
            blockselected.setBounds(150,50,200,50);
            blockselected.setBackground(Color.white);
            Box.add(blockselected);


            Box.setSize(500,600);
            Box.setLayout(null);
            Box.setVisible(true);
    }
}
