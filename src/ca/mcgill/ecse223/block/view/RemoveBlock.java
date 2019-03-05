package ca.mcgill.ecse223.block.view;

import java.awt.Color;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
public class RemoveBlock {


    private String error = null;
    private JLabel errorMessage;

    private JTextField levelField;
    private JTextField oldGridHorizontalField;
    private JTextField oldGridVerticalField;

    private JLabel moveBlockLabel;

    private JLabel levelNumber;
    private JLabel oldGridHorizontalValue;
    private JLabel oldGridVerticalValue;

    private JButton removeButton;
    private JFrame frame;
    public void RemoveBlock(){
        frame = new JFrame();

        errorMessage = new JLabel();
        errorMessage.setForeground(Color.RED);
        errorMessage.setBounds(0, 250, 400, 200);

        moveBlockLabel = new JLabel();
        moveBlockLabel.setText("Remove the block from a level's grid");
        moveBlockLabel.setBounds(80, 0, 450, 50);

        levelNumber = new JLabel();
        levelNumber.setText("Specify the level: ");
        levelNumber.setBounds(0, 60,200,30);

        levelField = new JTextField();
        levelField.setBounds(200, 60, 200, 30);

        oldGridHorizontalValue = new JLabel();
        oldGridHorizontalValue.setText("Current horizontal position: ");
        oldGridHorizontalValue.setBounds(0, 115, 200, 30);

        oldGridHorizontalField = new JTextField();
        oldGridHorizontalField.setBounds(200, 115, 200, 30);

        oldGridVerticalValue = new JLabel();
        oldGridVerticalValue.setText("Current vertical position: ");
        oldGridVerticalValue.setBounds(0, 165, 200, 30);

        oldGridVerticalField = new JTextField();
        oldGridVerticalField.setBounds(200, 165, 200, 30);


        removeButton = new JButton();
        removeButton.setBounds(135,220,200,50);
        removeButton.setText("Remove");

        frame.setSize(550, 350);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.PINK);

        frame.add(errorMessage);

        frame.add(levelField);

        frame.add(moveBlockLabel);

        frame.add(levelNumber);
        frame.add(oldGridHorizontalField);
        frame.add(oldGridHorizontalValue);
        frame.add(oldGridVerticalField);
        frame.add(oldGridVerticalValue);
        frame.add(removeButton);

        removeButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                int level = Integer.parseInt(levelField.getText());
                int oldGridHorizontalPosition = Integer.parseInt(oldGridHorizontalField.getText());
                int oldGridVerticalPosition = Integer.parseInt(oldGridVerticalField.getText());

                try {
                    Block223Controller.removeBlock(level, oldGridHorizontalPosition, oldGridVerticalPosition);
                    frame.dispose();
                } catch (Exception er) {
                    er.printStackTrace();
                }

            }
        });
    }
}