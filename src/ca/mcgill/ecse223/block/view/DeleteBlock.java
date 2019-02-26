package ca.mcgill.ecse223.block.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBlock extends  JFrame{
    public DeleteBlock(){

    }
    private JButton Deletes = new JButton();
    private JFrame Box = new JFrame();
    //probably need list for the blocks with id
    public void BlockDeletion(){
    //TODO need to get blocks in the game
    //TODO need a way to display them on the screen
    //TODO need a way to click on it and delete it

        Deletes.setBounds(20,30,200,50);
        Deletes.setText("Delete Block");
        //   Deletes.setFont(main.font);
        Box.add(Deletes);
        Deletes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean delete= true;
                System.out.println(delete);
            }
        });
        Box.setSize(800,800);
        Box.setLayout(null);
        Box.setVisible(true);
    }
}

